package com.sist.mar.payment;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sist.mar.order.test.JTestOrderDao;
import com.sist.mar.payment.dao.PaymentDao;
import com.sist.mar.payment.domain.Payment;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@RunWith(SpringJUnit4ClassRunner.class)   
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestPaymentDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestPaymentDao.class);

	@Autowired 
	ApplicationContext context; 
	
	@Autowired
	private PaymentDao dao;
	
	Payment pay01;
	Payment pay02;
	Payment pay03;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("= setUp =");
		
		pay01 = new Payment("1", 1, "1");
		pay02 = new Payment("2", 2, "1");
		pay03 = new Payment("3", 3, "1");
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("= tearDown =");
	}

	@Test
	public void addAndGet() throws Exception {
		LOG.debug("= addAndGet =");
		
	   	int flag = dao.doInsert(pay01);
	   	assertThat(flag, is(1));
	   	flag    += dao.doInsert(pay02);
	   	assertThat(flag, is(2));
	   	flag    += dao.doInsert(pay03);
	   	assertThat(flag, is(3)); 
	   	
	}
	
	@Test
	public void beans() {
		LOG.debug("= context =" + context);
		LOG.debug("= dao =" + dao);
		
		assertThat(this.context, is(notNullValue()));
		assertThat(this.dao, is(notNullValue()));
	}

}
