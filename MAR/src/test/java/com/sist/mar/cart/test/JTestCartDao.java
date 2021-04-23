package com.sist.mar.cart.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
import com.sist.mar.cart.domain.Member;


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
		//param값(cart_no, 장바구니번호)를 받아 삭제
		//테이블 내 삭제버튼으로 단건 삭제
	   	dao.doDelete(cart01.getCartNo() + "");
	   	dao.doDelete(cart02.getCartNo() + "");
	   	dao.doDelete(cart03.getCartNo() + "");
		
	   	//단건 등록
	   	//테스트를 위해 시퀀스 적용 전
	   	//팝업창에서 등록
	   	//cart(cart_no 시퀀스예정, member_id, item_no, quantity)를 받아 등록
	   	int flag = dao.doInsert(cart01);
	   	assertThat(flag, is(1));
	   	flag    += dao.doInsert(cart02);
	   	assertThat(flag, is(2));
	   	flag    += dao.doInsert(cart03);
	   	assertThat(flag, is(3)); 
	   
	   	//수량변경
		//cart(cart_no, quantity)를 받아 삭제
		//테이블 상단 수량변경저장버튼을 통해 수행 (테이블 내 데이터 전부 update)
	   	cart01.setQuantity(100);
	   	dao.doUpdate(cart01);
	   	
	   	//목록 조회
	    //param값(member_id, 회원아이디)를 받아 검색
		List<Cart> list = (List<Cart>) dao.doRetrieve(cart01.getMemberId());
		LOG.debug("==================");
		for(Cart vo : list) {
			LOG.debug("= vo =" + vo);
		}
		LOG.debug("==================");
		 
		//주문용 회원정보 //memberVO들어와야 테스트 가능(임시vo만들어놓음)
		//param값(member_id, 회원아이디)를 받아 검색
		Member outVO = (Member) dao.doOrder(cart01.getMemberId());
		LOG.debug("==================");
	   	LOG.debug("= outVO =" + outVO);
	   	LOG.debug("==================");
	}

	@Test
	public void beans() {
		LOG.debug("= context =" + context);
		LOG.debug("= dao =" + dao);
		
		assertThat(this.context, is(notNullValue()));
		assertThat(this.dao, is(notNullValue()));
	}

}
