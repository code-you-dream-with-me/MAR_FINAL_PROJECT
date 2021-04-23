package com.sist.mar.answer.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sist.mar.answer.dao.AnswerDaoImpl;
import com.sist.mar.answer.domain.Answer;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class JTestAnswerDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestAnswerDao.class);
	
	@Autowired
	ApplicationContext  context;
	
	@Autowired
	AnswerDaoImpl dao;
	
	Answer answer01;
	Answer answer02;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@Before=");
		LOG.debug("=======================");	

		answer01 = new Answer(2, 1, "sinangsong@gmail.com", "제목", "내용", "");
		answer02 = new Answer(3, 2, "sinangsong@gmail.com", "제목", "내용", "");
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@After=");
		LOG.debug("=======================");
	}
	
//	@Test
	public void getAllList() throws Exception{
		List<Answer> list  =(List<Answer>) dao.getAllList();
		
	}

	@Test
//	@Ignore
	public void doSelectOne() throws Exception{
		
		Answer vsAnswer01 = (Answer) dao.doSelectOne(answer01);
		checkAnswer(vsAnswer01, answer01);
	}
	

	@Test
	@Ignore
	public void doDelete() throws Exception{
		
		int flag =dao.doDelete(answer01);
		assertThat(flag, is(1));
	}
	
	@Test
	@Ignore
	public void doInsert() throws Exception{
		
		int flag =dao.doInsert(answer02);
		assertThat(flag, is(1));
	}
	
	private void checkAnswer(Answer vsAnswer, Answer answer01) {
		
		assertThat(vsAnswer.getAnswerNo(), is(answer01.getAnswerNo()));
		assertThat(vsAnswer.getQuestionNo(), is(answer01.getQuestionNo()));
		assertThat(vsAnswer.getaUser(), is(answer01.getaUser()));
		assertThat(vsAnswer.getTitle(), is(answer01.getTitle()));
		assertThat(vsAnswer.getContents(), is(answer01.getContents()));

	}

	@Test
	public void bean() {
		LOG.debug("context:"+context);
		LOG.debug("dao:"+dao);
		
		assertThat(context, is(notNullValue()));
		assertThat(dao, is(notNullValue()));
	}

}
