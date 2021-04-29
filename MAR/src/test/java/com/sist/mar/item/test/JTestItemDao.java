package com.sist.mar.item.test;

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


import com.sist.mar.item.dao.ItemDaoImpl;
import com.sist.mar.item.domain.Item;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class JTestItemDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestItemDao.class);
	
	@Autowired
	ApplicationContext  context;
	
	@Autowired
	ItemDaoImpl dao;
	
	Item item01;
	Item item02;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@Before=");
		LOG.debug("=======================");	

		item01 = new Item(22, "상품", 1000, "국내산", "1kg", "별도표기", "설명", 0, 0, 0, 10, "sinangsong@gmail.com", "", "",0,"","");
		item02 = new Item(23, "상품2", 3000, "국내산", "2kg", "별도표기", "설명", 0, 0, 0, 10, "sinangsong@gmail.com", "", "",0,"","");
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@After=");
		LOG.debug("=======================");
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Test
	public void getAllList() throws Exception{
		List<Item> list  =(List<Item>) dao.getAllList();
		
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Test
	public void getRelatedList() throws Exception{
		List<Item> list = (List<Item>) dao.getRelatedList(item01);
	}
	
	@Test
	public void doSelectOne() throws Exception{
		
		Item vsItem01 = (Item) dao.doSelectOne(item02);
		checkItem(vsItem01, item02);
	}
	

	
	@Test
	@Ignore
	public void doUpdate() throws Exception{
		
		item01.setName(item01.getName()+"수정");
		item01.setPrice(item01.getPrice()+100);
		item01.setProduction(item01.getProduction()+"수정");
		item01.setDetail(item01.getDetail()+"수정");
		item01.setDiscount(10);
		item01.setFinalPrice(1000);
		item01.setSales(10);
		item01.setCategoryNo(item01.getCategoryNo()+100);
		
		int flag =dao.doUpdate(item01);
		assertThat(flag, is(1));
	}
	
	@Test
	@Ignore
	public void doDelete() throws Exception{
		
		int flag =dao.doDelete(item01);
		assertThat(flag, is(1));
	}
	
	@Test
	@Ignore
	public void doInsert() throws Exception{
		
		int flag =dao.doInsert(item02);
		assertThat(flag, is(1));
	}
	
	private void checkItem(Item vsItem, Item item01) {
		
		assertThat(vsItem.getItemNo(), is(item01.getItemNo()));
		assertThat(vsItem.getName(), is(item01.getName()));
		assertThat(vsItem.getPrice(), is(item01.getPrice()));
		assertThat(vsItem.getProduction(), is(item01.getProduction()));
		assertThat(vsItem.getWeight(), is(item01.getWeight()));
		assertThat(vsItem.getExpired(), is(item01.getExpired()));
		assertThat(vsItem.getDetail(), is(item01.getDetail()));
		assertThat(vsItem.getDiscount(), is(item01.getDiscount()));
		assertThat(vsItem.getWeight(), is(item01.getWeight()));
		assertThat(vsItem.getFinalPrice(), is(item01.getFinalPrice()));
		assertThat(vsItem.getSales(), is(item01.getSales()));
		assertThat(vsItem.getCategoryNo(), is(item01.getCategoryNo()));
		assertThat(vsItem.getRegId(), is(item01.getRegId()));
		
		
	}

	@Test
	public void bean() {
		LOG.debug("context:"+context);
		LOG.debug("dao:"+dao);
		
		assertThat(context, is(notNullValue()));
		assertThat(dao, is(notNullValue()));
	}

}
