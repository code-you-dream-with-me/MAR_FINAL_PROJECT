package com.sist.mar.review.test;

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
import com.sist.mar.review.dao.ReviewDaoImpl;
import com.sist.mar.review.domain.ReviewVO;

//메소드 수행 순서 : method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//스프링 테스트 컨텍스트 프레임의 junit 기능 확장
@RunWith(SpringJUnit4ClassRunner.class) 

//기존의 ApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml")가 한 일을 이걸로 마무리 + @Autowired로 연결시킴
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
									, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestReviewDao {

//	▼ 변수 ===============================================================
	final static Logger LOG = Logger.getLogger(JTestReviewDao.class);
	
	@Autowired
	ApplicationContext context;	// 테스트 오브젝트가 만들어지고나면, 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	
	// 이걸 통해 UserDao라는 id를 찾는 것을 시작으로 기존 getConnection 과정이자 bean 파일을 통해 접속 정보를 얻는 과정을 시작 
	@Autowired
	private ReviewDaoImpl reviewDao;
	
	ReviewVO review01;
	ReviewVO review02;
	ReviewVO review03;
	
	ReviewVO review04;
	ReviewVO review05;
	ReviewVO review06;
	ReviewVO review07;
	
	Search search;
	
	@Before
	public void setUp() throws Exception {
		
		LOG.debug("-------------------------");
		LOG.debug("=@Before=");
		LOG.debug("-------------------------");
		
		LOG.debug("=context=" + context);
		
		review01 = new ReviewVO(101, "aaa1@gmail.com" , 10 , "제목10", "내용10" , 0 , "");
		review02 = new ReviewVO(102, "aaa1@gmail.com" , 10 , "제목70", "내용70" , 0 , "");
		review03 = new ReviewVO(83, "aaa2@gmail.com" , 20 , "제목20", "내용20" , 0 , "");
		review04 = new ReviewVO(84, "aaa3@gmail.com" , 30 , "제목30", "내용30" , 0 , "");
		   
		review05 = new ReviewVO(85, "bbb1@gmail.com" , 40 , "제목40", "내용40" , 0 , "");
		review06 = new ReviewVO(86, "bbb1@gmail.com" , 50 , "제목50", "내용50" , 0 , "");
		review07 = new ReviewVO(87, "ccc1@gmail.com" , 60 , "제목60", "내용60" , 0 , "");
		
		search = new Search("20", "aaa1@gmail.com", 10, 1);
		
	}

	@After
	public void tearDown() throws Exception {
		
		LOG.debug("-------------------------");
		LOG.debug("=@After=");
		LOG.debug("-------------------------");
		
	}
	
	
//	▼ 테스트 ===============================================================
	
	@Test
	public void listAndRead() throws SQLException {
		// 1. 후기 전체 목록조회
		// 2. 후기 단건 조회
		// 3. 후기 전체 세기
		
		LOG.debug("*************************");
		LOG.debug("=@listAndDetail=");
		LOG.debug("*************************");
		
		// 1.
		reviewDao.doRetrieve(search);
		//reviewDao.doRetrieveSelf(search);
		
		// 2.
		reviewDao.doSelectOne(review01);
		reviewDao.doSelectOne(review02);
		reviewDao.doSelectOne(review03);
		
		// 3.
		int flag = reviewDao.doReadCnt(review01);
		assertThat(flag, is(1));
		
		flag += reviewDao.doReadCnt(review02);
		assertThat(flag, is(2));
		
		flag += reviewDao.doReadCnt(review03);
		assertThat(flag, is(3));
		
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
		reviewDao.doDelete(review01);
		reviewDao.doDelete(review02);
		reviewDao.doDelete(review03);
		reviewDao.doDelete(review04);
		reviewDao.doDelete(review05);
		reviewDao.doDelete(review06);
		
		//2.
		int flagInsert  =  reviewDao.doInsert(review01);
		assertThat(flagInsert, is(1));
		
		flagInsert  +=  reviewDao.doInsert(review02);
		assertThat(flagInsert, is(2));
		
		flagInsert  +=  reviewDao.doInsert(review03);
		assertThat(flagInsert, is(3));
		
		flagInsert  +=  reviewDao.doInsert(review04);
		assertThat(flagInsert, is(4));
		
		flagInsert  +=  reviewDao.doInsert(review05);
		assertThat(flagInsert, is(5));
		
		flagInsert  +=  reviewDao.doInsert(review06);
		assertThat(flagInsert, is(6));

		// 3.
		review01.setTitle(review01.getTitle() + "수정함");
		review01.setContents(review01.getContents() + "수정함");
		
		review02.setTitle(review02.getTitle() + "수정함");
		review02.setContents(review02.getContents() + "수정함");
		
		review03.setTitle(review03.getTitle() + "수정함");
		review03.setContents(review03.getContents() + "수정함");
		
		LOG.debug("review01 : " + review01);
		LOG.debug("review02 : " + review02);
		LOG.debug("review03 : " + review03);
		
		int flagUpdate = reviewDao.doUpdate(review01);
		assertThat(flagUpdate, is(1));
		
		flagUpdate += reviewDao.doUpdate(review02);
		assertThat(flagUpdate, is(2));
		
		flagUpdate += reviewDao.doUpdate(review03);
		assertThat(flagUpdate, is(3));
		
		
	}

}
