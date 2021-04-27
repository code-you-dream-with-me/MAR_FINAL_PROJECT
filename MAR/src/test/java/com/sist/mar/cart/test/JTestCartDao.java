package com.sist.mar.cart.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import com.sist.mar.cart.dao.CartDao;
import com.sist.mar.cart.domain.Cart;
import com.sist.mar.member.domain.MemberVO;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@RunWith(SpringJUnit4ClassRunner.class)   
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestCartDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestCartDao.class);

	@Autowired
	ApplicationContext context; 
	
	@Autowired 
	private CartDao dao;
	
	Cart cart01;
	Cart cart02;
	Cart cart03;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("= setUp =");
		cart01 = new Cart(1, "test01", 1, 1);
		cart02 = new Cart(2, "test02", 2, 2);
		cart03 = new Cart(3, "test03", 3, 3);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("= tearDown =");
	}
	
	@Test
//	@Ignore
	public void addAndGet() throws Exception {
		LOG.debug("= addAndGet =");
	
	   	//단건 삭제
	   	dao.doDelete(cart01.getCartNo() + "");
	   	dao.doDelete(cart02.getCartNo() + "");
	   	dao.doDelete(cart03.getCartNo() + "");
	
	   	//cart Check
	   	int flag = dao.cartCheck(cart01);
	   	LOG.debug("= flag =" + flag);
	   	
	   	//단건 등록
	   	flag  = dao.doInsert(cart01);
	   	assertThat(flag, is(1));
	   	flag += dao.doInsert(cart02);
	   	assertThat(flag, is(2));
	   	flag += dao.doInsert(cart03);
	   	assertThat(flag, is(3)); 
	   
	   	//수량변경
	   	cart01.setQuantity(100);
	   	dao.doUpdate(cart01);
	   	
	   	//목록 조회
		List<Cart> list = (List<Cart>) dao.doRetrieve(cart01.getMemberId());
		LOG.debug("==================");
		for(Cart vo : list) {
			LOG.debug("= vo =" + vo);
		}
		LOG.debug("==================");
		 
		//주문용 회원정보 //memberVO들어와야 테스트 가능
		MemberVO outVO = (MemberVO) dao.doOrder(cart01.getMemberId());
		LOG.debug("==================");
	   	LOG.debug("= outVO =" + outVO);
	   	LOG.debug("==================");
	}

	@Test
	@Ignore
	public void beans() {
		LOG.debug("= context =" + context);
		LOG.debug("= dao =" + dao);
	}

}
