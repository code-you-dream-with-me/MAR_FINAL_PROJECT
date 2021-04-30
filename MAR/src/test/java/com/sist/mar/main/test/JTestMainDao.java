package com.sist.mar.main.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.sql.SQLException;

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
import org.springframework.test.context.web.WebAppConfiguration;

import com.sist.mar.main.dao.MainDaoImpl;
import com.sist.mar.main.domain.CateSearchVO;


//NAME_ASCENDING: Sorts the test methods by the method name a~z순으로 메서드 이름별로 정렬
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//수행순서 정하기
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestMainDao {
	
	final static Logger LOG = LoggerFactory.getLogger(JTestMainDao.class);

	@Autowired//객체를 한번만 읽도록한다.
	ApplicationContext context;//테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨테스트에 의해 자동으로 주입된다.
	
	@Autowired
	private MainDaoImpl dao;
	
	CateSearchVO search;

	@Before
	public void setUp() throws Exception {
		LOG.debug("====================================");
		LOG.debug("@Before");
		LOG.debug("====================================");
		
		//int categoryNo, String listDiv, String searchWord, int pageSize, int pageNum
		search = new CateSearchVO(0,"0","",10,1);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("====================================");
		LOG.debug("@After");
		LOG.debug("====================================");
	}
	

	@Test
	public void doAllRetrieve() throws SQLException {
		dao.doRetrieve(search);
		dao.doRecipeRetrieve(search);
	}

	@Test
	@Ignore
	public void test() {
		LOG.debug("=context="+context);
		LOG.debug("=dao="+dao);
		
		assertThat(context,is(notNullValue()));
		assertThat(dao,is(notNullValue()));
	}

}
