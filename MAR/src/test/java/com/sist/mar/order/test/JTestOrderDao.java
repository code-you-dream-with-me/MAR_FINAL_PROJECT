package com.sist.mar.order.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import com.sist.mar.order.dao.OrderDao;
import com.sist.mar.order.domain.Ordering;
import com.sist.mar.order.domain.Orderitem;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@RunWith(SpringJUnit4ClassRunner.class)   
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestOrderDao {
	
	final static Logger LOG = LoggerFactory.getLogger(JTestOrderDao.class);

	@Autowired 
	ApplicationContext context; 
	
	@Autowired
	private OrderDao dao;
	
	Ordering order01;
	Ordering order02;
	Ordering order03;
	
	Orderitem item01;
	Orderitem item02;
	Orderitem item03;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("= setUp =");
		
		order01 = new Ordering("test01", 900, "테스트01", "1111-1111", "테스트11", "테스트해주세요01", "1");
		order02 = new Ordering("test02", 1600, "테스트02", "2222-2222", "테스트22", "테스트해주세요02", "2");
		order03 = new Ordering("test03", 2100, "테스트03", "3333-3333", "테스트33", "테스트해주세요03", "3");
		
		item01 = new Orderitem(111, 11, 1);
		item02 = new Orderitem(222, 22, 2);
		item03 = new Orderitem(333, 33, 3);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("= tearDown =");
	}

	@Test
//	@Ignore
	public void addAndGet() throws Exception {
		LOG.debug("= addAndGet =");
		
		int flag = dao.doInsertOrdering(order01);
		assertThat(flag, is(1));
		flag    += dao.doInsertOrdering(order02);
		assertThat(flag, is(2));
		flag    += dao.doInsertOrdering(order03);
		assertThat(flag, is(3));
		
		flag  = dao.doInsertOrderitem(item01);
		assertThat(flag, is(1));
		flag += dao.doInsertOrderitem(item02);
		assertThat(flag, is(2));
		flag += dao.doInsertOrderitem(item03);
		assertThat(flag, is(3));
		
		dao.doDeleteCart(order01.getMemberId());
		
	}
	
	@Test
	@Ignore
	public void beans() {
		LOG.debug("= context =" + context);
		LOG.debug("= dao =" + dao);
	}

}
