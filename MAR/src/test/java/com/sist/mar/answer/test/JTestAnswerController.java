package com.sist.mar.answer.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
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
import com.sist.mar.answer.domain.Answer;
import com.sist.mar.cmn.Message;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class JTestAnswerController {

	final Logger LOG = LoggerFactory.getLogger(JTestAnswerController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;
	
	Answer answer01;
	Answer answer02;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("setUp()");
		
		answer01 = new Answer(42, 1, "sinangsong@gmail.com", "제목", "내용", "");
		answer02 = new Answer(3, 2, "sinangsong@gmail.com", "제목", "내용", "");
	
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void tearDown() throws Exception {
		LOG.debug("tearDown()");
	}
	
	
	@Test
	@SuppressWarnings("deprecation")
	public void getAllList() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/answer/get_all.do");
		
		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
	
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();	
		
		Gson gson = new Gson();	
		
		List<Answer> list = gson.fromJson(result, new TypeToken<List<Answer>>() {}.getType());
	
		
		for(Answer vo : list) {
			LOG.debug("vo:"+vo);
		}
	}
	
	
	
	@Test
	@SuppressWarnings("deprecation")
	public void doSelectOne() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/answer/do_selectone.do")
				.param("questionNo", answer02.getQuestionNo()+"");

		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());	
		
		
		String result = resultActions.andDo(print())
		.andReturn().getResponse().getContentAsString();	
		
		LOG.debug("---------------------------------");
		LOG.debug("result"+result);
		LOG.debug("---------------------------------");
		
		Gson gson = new Gson();
		Answer outVO = gson.fromJson(result, Answer.class);
		
		LOG.debug("---------------------------------");
		LOG.debug("outVO"+outVO);
		LOG.debug("---------------------------------");
		
		checkAnswer(outVO, answer02);
	
	}
	
	private void checkAnswer(Answer vsAnswer, Answer answer01) {
		
		assertThat(vsAnswer.getAnswerNo(), is(answer01.getAnswerNo()));
		assertThat(vsAnswer.getQuestionNo(), is(answer01.getQuestionNo()));
		assertThat(vsAnswer.getaUser(), is(answer01.getaUser()));
		assertThat(vsAnswer.getTitle(), is(answer01.getTitle()));
		assertThat(vsAnswer.getContents(), is(answer01.getContents()));

	}
	
	
	
	@Test
	@Ignore
	@SuppressWarnings("deprecation")
	public void doDelete() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/answer/do_delete.do")
												.param("answerNo", answer01.getAnswerNo()+"");
		
		
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
		resultMsg = answer01.getaUser()+"님 답변 \n삭제 성공";
		
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
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/answer/do_insert.do")
				.param("questionNo", answer01.getQuestionNo()+"")
				.param("aUser", answer01.getaUser() )
				.param("title", answer01.getTitle())
				.param("contents", answer01.getContents());
		
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
		resultMsg = answer01.getaUser()+"님 답변 \n등록 성공";
		
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
