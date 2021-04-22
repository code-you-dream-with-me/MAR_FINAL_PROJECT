package com.sist.mar.wishiem.test;

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

import com.sist.mar.cmn.Search;
import com.sist.mar.wishitem.dao.WishitemDao;
import com.sist.mar.wishitem.domain.Wishitem;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@RunWith(SpringJUnit4ClassRunner.class)   
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestWishitemDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestWishitemDao.class);

	@Autowired 
	ApplicationContext context; 
	
	@Autowired
	private WishitemDao dao;
	
	Wishitem wish01;
	Wishitem wish02;
	Wishitem wish03;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("= setUp =");
		wish01 = new Wishitem(1, "test01", 1);
		wish02 = new Wishitem(2, "test02", 2);
		wish03 = new Wishitem(3, "test03", 3);
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
	   	dao.doDelete(wish01.getWishNo() + "");
	   	dao.doDelete(wish02.getWishNo() + "");
	   	dao.doDelete(wish03.getWishNo() + "");
		
	   	//단건 등록
	   	int flag = dao.doInsert(wish01);
	   	assertThat(flag, is(1));
	   	flag    += dao.doInsert(wish02);
	   	assertThat(flag, is(2));
	   	flag    += dao.doInsert(wish03);
	   	assertThat(flag, is(3)); 
	   	
	   	//단건 조회
	   	Wishitem outVO = (Wishitem) dao.doSelectOne(wish01.getWishNo() + "");
		LOG.debug("==================");
	   	LOG.debug("= outVO =" + outVO);
	   	LOG.debug("==================");
	   	
	   	//목록 조회
		Search search = new Search("", "test01", 10, 1);
		List<Wishitem> list = (List<Wishitem>) dao.doRetrieve(search);
		LOG.debug("==================");
		for(Wishitem vo : list) {
			LOG.debug("= vo =" + vo);
		}
		LOG.debug("==================");
	}

	@Test
	@Ignore
	public void beans() throws Exception {
		LOG.debug("= context =" + context);
		LOG.debug("= dao =" + dao);
	}

}
