package com.sist.mar.item.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.sist.mar.cmn.Message;
import com.sist.mar.item.domain.Item;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class JTestItemController {

	final Logger LOG = LoggerFactory.getLogger(JTestItemController.class);
	

	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;
	
	Item item01;
	Item item02;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("setUp()");
		
		item01 = new Item(22, "상품", 1000, "국내산", "1kg", "별도표기", "설명", 0, 0, 0, 10, "sinangsong@gmail.com", "", "",0,"","");
		item02 = new Item(23, "상품2", 3000, "국내산", "2kg", "별도표기", "설명", 0, 0, 0, 10, "sinangsong@gmail.com", "", "",0,"","");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("tearDown()");
	}
	
//	@Test
	@SuppressWarnings("deprecation")
	public void getAllList() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/item/get_all.do");
		
		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
	
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();	
		
		Gson gson = new Gson();	
		
		List<Item> list = gson.fromJson(result, new TypeToken<List<Item>>() {}.getType());
	
		
		for(Item vo : list) {
			LOG.debug("vo:"+vo);
		}
	}
	
	
	@Test
	@SuppressWarnings("deprecation")
	public void getRelatedList() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/item/get_relatedlist.do");
		
		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
	
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();	
		
		Gson gson = new Gson();	
		
		List<Item> list = gson.fromJson(result, new TypeToken<List<Item>>() {}.getType());
	
		
		for(Item vo : list) {
			LOG.debug("vo:"+vo);
		}
	}
	
//	@Test
	@SuppressWarnings("deprecation")
	public void doSelectOne() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/item/do_selectone.do")
					.param("itemNo", item02.getItemNo()+"");

		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());	
		
		
		String result = resultActions.andDo(print())
		.andReturn().getResponse().getContentAsString();	
		
		LOG.debug("---------------------------------");
		LOG.debug("result"+result);
		LOG.debug("---------------------------------");
		
		Gson gson = new Gson();
		Item outVO = gson.fromJson(result, Item.class);
		
		LOG.debug("---------------------------------");
		LOG.debug("outVO"+outVO);
		LOG.debug("---------------------------------");
		
		checkItem(outVO, item02);
		
		
		
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
	@Ignore
	@SuppressWarnings("deprecation")
	public void doUpdate() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/item/do_update.do")
				.param("itemNo", item01.getItemNo()+"")
				.param("name", item01.getName())
				.param("price", item01.getPrice()+"")
				.param("production", item01.getProduction())
				.param("weight", item01.getWeight())
				.param("expired", item01.getExpired())
				.param("detail", item01.getDetail())
				.param("discount", item01.getDiscount()+"")
				.param("finalPrice", item01.getFinalPrice()+"")
				.param("sales", item01.getSales()+"")
				.param("categoryNo", item01.getCategoryNo()+"");

		ResultActions resultActions =  mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk());
		
		
		
		String result = resultActions.andDo(print())
		.andReturn().getResponse().getContentAsString();	
		
		LOG.debug("---------------------------------");
		LOG.debug("result"+result);
		LOG.debug("---------------------------------");
		
		Gson gson = new Gson();
		Message getMessage =  gson.fromJson(result, Message.class);
		
		
		LOG.debug("---------------------------------");
		LOG.debug("getMessage"+getMessage);
		LOG.debug("---------------------------------");
		
		String resultMsg = "";
		resultMsg = item01.getName()+"\n수정 성공";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
		
		//return Integer.parseInt(getMessage.getMsgId());
		
	}
	
	
	@Test
	@Ignore
	@SuppressWarnings("deprecation")
	public void doDelete() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/item/do_delete.do")
												.param("itemNo", item01.getItemNo()+"");
		
		
		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();	
		
		LOG.debug("---------------------------------");
		LOG.debug("result"+result);
		LOG.debug("---------------------------------");
	
	
		Gson gson = new Gson();
		Message getMessage =  gson.fromJson(result, Message.class);
		

		LOG.debug("---------------------------------");
		LOG.debug("getMessage"+getMessage);
		LOG.debug("---------------------------------");
		
		String resultMsg = "";
		resultMsg = item01.getItemNo()+"\n삭제 성공";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
		
										
	}
	
	
	@Test
	@Ignore
	@SuppressWarnings("deprecation")
	public void doInsert() throws Exception{
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/item/do_insert.do")
				.param("name", item01.getName())
				.param("price", item01.getPrice()+"")
				.param("production", item01.getProduction())
				.param("weight", item01.getWeight())
				.param("expired", item01.getExpired())
				.param("detail", item01.getDetail())
				.param("discount", item01.getDiscount()+"")
				.param("finalPrice", item01.getFinalPrice()+"")
				.param("sales", item01.getSales()+"")
				.param("categoryNo", item01.getCategoryNo()+"")
				.param("regId", item01.getRegId());
		
		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		
		
		String result = resultActions.andDo(print())
				.andReturn().getResponse().getContentAsString();	
		
		LOG.debug("---------------------------------");
		LOG.debug("result"+result);
		LOG.debug("---------------------------------");
	
		Gson gson = new Gson();
		Message getMessage =  gson.fromJson(result, Message.class);
		

		LOG.debug("---------------------------------");
		LOG.debug("getMessage"+getMessage);
		LOG.debug("---------------------------------");
		
		String resultMsg = "";
		resultMsg = item01.getName()+"\n등록 성공";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
		
		
	}

	@Test
	public void beans() {
		LOG.debug("webApplicationContext"+webApplicationContext);
		LOG.debug("mockMvc"+mockMvc);
		
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}
