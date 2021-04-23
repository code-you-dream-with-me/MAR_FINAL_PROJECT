package com.sist.mar.order.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
		
		item01 = new Orderitem(11, 111, 11, 1);
		item02 = new Orderitem(22, 222, 22, 2);
		item03 = new Orderitem(33, 333, 33, 3);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("= tearDown =");
	}

	@Test
	public void addAndGet() throws Exception {
		LOG.debug("= addAndGet =");
		
		//주문 등록(회원정보와 페이지 내 정보들로 주문 컬럼 입력)
		int flag = dao.doInsertOrdering(order01);
		assertThat(flag, is(1));
		flag    += dao.doInsertOrdering(order02);
		assertThat(flag, is(2));
		flag    += dao.doInsertOrdering(order03);
		assertThat(flag, is(3));
		
		//주문상품등록 (장바구니 -> 주문상품 상품하나하나씩등록)
		flag  = dao.doInsertOrderitem(item01);
		assertThat(flag, is(1));
		flag += dao.doInsertOrderitem(item02);
		assertThat(flag, is(2));
		flag += dao.doInsertOrderitem(item03);
		assertThat(flag, is(3));
		
		//member_id로 검색한 cart_no값들을 전부 삭제
		dao.doDeleteCart(order01.getMemberId());
		
	}
	
	@Test
	public void beans() {
		LOG.debug("= context =" + context);
		LOG.debug("= dao =" + dao);
		
		assertThat(this.context, is(notNullValue()));
		assertThat(this.dao, is(notNullValue()));
	}

}
