package com.sist.mar.question.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sist.mar.cmn.Search;
import com.sist.mar.question.dao.QuestionDaoImpl;
import com.sist.mar.question.domain.QuestionVO;



//메소드 수행 순서 : method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//스프링 테스트 컨텍스트 프레임의 junit 기능 확장
@RunWith(SpringJUnit4ClassRunner.class) 

//기존의 ApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml")가 한 일을 이걸로 마무리 + @Autowired로 연결시킴
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
									, "file:src/main/webapp/WEB-INF/spring/appServlet/test-servlet-context.xml"})
public class JTestQuestionDao {

//	▼ 변수 ===============================================================
	final static Logger LOG = Logger.getLogger(JTestQuestionDao.class);
	
	@Autowired
	ApplicationContext context;	// 테스트 오브젝트가 만들어지고나면, 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	
	// 이걸 통해 UserDao라는 id를 찾는 것을 시작으로 기존 getConnection 과정이자 bean 파일을 통해 접속 정보를 얻는 과정을 시작 
	@Autowired
	private QuestionDaoImpl questionDao;
	
	QuestionVO question01;
	QuestionVO question02;
	QuestionVO question03;
	
	Search search;
	
	@Before
	public void setUp() throws Exception {
		
		LOG.debug("-------------------------");
		LOG.debug("=@Before=");
		LOG.debug("-------------------------");
		
		LOG.debug("=context=" + context);
		
		question01 = new QuestionVO(24, 1 , "aaa1@gmail.com" , "제목10", "내용10" , "");
		question02 = new QuestionVO(25, 1 , "aaa1@gmail.com" , "제목20", "내용20" , "");
		question03 = new QuestionVO(26, 2 , "bbb1@gmail.com" , "제목30", "내용30" , "");
		
		search = new Search("10", "aaa1@gmail.com", 10, 1);
	}

	@After
	public void tearDown() throws Exception {
		
		LOG.debug("-------------------------");
		LOG.debug("=@After=");
		LOG.debug("-------------------------");
		
	}

//	▼ 테스트 ===============================================================
	
	@Test
	public void checkAnswer() {
		
		LOG.debug("*************************");
		LOG.debug("=@checkAnswer=");
		LOG.debug("*************************");
		
		questionDao.answerCheck(question01);
		questionDao.answerCheck(question02);
	}
	
	
	@Test
	@Ignore
	public void listAndRead() throws SQLException {
		// 1. 후기 전체 목록조회
		// 2. 후기 단건 조회
		
		LOG.debug("*************************");
		LOG.debug("=@listAndDetail=");
		LOG.debug("*************************");
		
		// 1.
		questionDao.doRetrieve(search);
		
		// 2.
		questionDao.doSelectOne(question01);
		questionDao.doSelectOne(question02);
		questionDao.doSelectOne(question03);
		
		
	}
	
	
	@Test
	@Ignore
	public void crudTest() throws SQLException {
		
		// 1. 기존데이터 3건 삭제
		// 2. 신규데이터 3건 입력
		// 3. 데이터 수정하기 (UPDATE)
		
		LOG.debug("*************************");
		LOG.debug("=@crudTest=");
		LOG.debug("*************************");
		
		// 1. 
		questionDao.doDelete(question01);
		questionDao.doDelete(question02);
		questionDao.doDelete(question03);
		
		//2.
		int flagInsert  =  questionDao.doInsert(question01);
		assertThat(flagInsert, is(1));
		
		flagInsert  +=  questionDao.doInsert(question02);
		assertThat(flagInsert, is(2));
		
		flagInsert  +=  questionDao.doInsert(question03);
		assertThat(flagInsert, is(3));

		// 3.
		question01.setTitle(question01.getTitle() + "수정함");
		question01.setContents(question01.getContents() + "수정함");
		
		question02.setTitle(question02.getTitle() + "수정함");
		question02.setContents(question02.getContents() + "수정함");
		
		question03.setTitle(question03.getTitle() + "수정함");
		question03.setContents(question03.getContents() + "수정함");
		
		LOG.debug("question01 : " + question01);
		LOG.debug("question02 : " + question02);
		LOG.debug("question03 : " + question03);
		
		int flagUpdate = questionDao.doUpdate(question01);
		assertThat(flagUpdate, is(1));
		
		flagUpdate += questionDao.doUpdate(question02);
		assertThat(flagUpdate, is(2));
		
		flagUpdate += questionDao.doUpdate(question03);
		assertThat(flagUpdate, is(3));
		
		
	}

}
