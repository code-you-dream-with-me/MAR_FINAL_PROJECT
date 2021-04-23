package com.sist.mar.question.test;

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
import com.sist.mar.cmn.Search;
import com.sist.mar.question.domain.QuestionVO;

//메소드 수행 순서 : method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//웹에서 applicationConfiguraion 쓸때 필요
@WebAppConfiguration

//스프링 테스트 컨텍스트 프레임의 junit 기능 확장
@RunWith(SpringJUnit4ClassRunner.class) 

//기존의 ApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml")가 한 일을 이걸로 마무리 + @Autowired로 연결시킴
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
									, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestQuestionController {

//	▼ 변수 ===============================================================		
	
	final Logger LOG = LoggerFactory.getLogger(JTestQuestionController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;

	QuestionVO question01;
	QuestionVO question02;
	QuestionVO question03;
	
	// 검색
	Search search;
	
	@Before
	public void setUp() throws Exception {
		
		question01 = new QuestionVO(42, 1 , "aaa1@gmail.com" , "제목10", "내용10" , "");
		question02 = new QuestionVO(25, 1 , "aaa1@gmail.com" , "제목20", "내용20" , "");
		question03 = new QuestionVO(26, 2 , "bbb1@gmail.com" , "제목30", "내용30" , "");

		// 첫번째 searchDiv (10 : 일반사용자, 20 : 관리자)
		search = new Search("10", "aaa1@gmail.com", 10, 1);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	
	}

	
//	▼ 테스트 ===============================================================
	
	@Test
	public void doInsert() throws Exception {
		
		// url호출, param전달
		MockHttpServletRequestBuilder creatMessage = MockMvcRequestBuilders.get("/question/do_insert.do")	
														.param("questionNo", String.valueOf(question01.getQuestionNo()))
														.param("orderNo", String.valueOf(question01.getOrderNo()))
														.param("qUser", question01.getqUser())
														.param("title", question01.getTitle())
														.param("contents", question01.getContents());
		/*
		 * 스트링 부트의 경우 2.2.0부터 APPLICATION_JSON_UTF8_VALUE는 포함X
		 * MediaType.APPLICATION_JSON
		 */
		ResultActions resultActions = mockMvc.perform(creatMessage)
									  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
									  .andExpect(status().isOk());
		
		/*
		(이후 루트)			 		
		 1.Json 사용할 경우 ->  andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))	)
						 ->  andExpect(status().isOk()) 메서드						  
		*/
		
		// 출력결과 요약
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
	
		LOG.debug("---------------------------");
		LOG.debug("result : " + result);
		LOG.debug("---------------------------");
		
		Gson gson = new Gson();
		Message getMessage =  gson.fromJson(result, Message.class);
		
		LOG.debug("---------------------------");
		LOG.debug("getMessage : " + getMessage);
		LOG.debug("---------------------------");
		
		//return Integer.parseInt(getMessage.getMsgId());
		
	}
	
	
	@Test
	public void doDelete() throws Exception {
		
		// url호출, param전달
		MockHttpServletRequestBuilder creatMessage = MockMvcRequestBuilders.get("/question/do_delete.do")	
													.param("questionNo", String.valueOf(question01.getQuestionNo()));
												
		/*
		 * 스트링 부트의 경우 2.2.0부터 APPLICATION_JSON_UTF8_VALUE는 포함X
		 * MediaType.APPLICATION_JSON
		 */
		ResultActions resultActions = mockMvc.perform(creatMessage)
									  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
									  .andExpect(status().isOk());
		
		/*
		(이후 루트)			 		
		 1.Json 사용할 경우 ->  andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))	)
						 ->  andExpect(status().isOk()) 메서드						  
		*/
		
		// 출력결과 요약
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
	
		LOG.debug("---------------------------");
		LOG.debug("result : " + result);
		LOG.debug("---------------------------");
		
		Gson gson = new Gson();
		Message getMessage =  gson.fromJson(result, Message.class);
		
		LOG.debug("---------------------------");
		LOG.debug("getMessage : " + getMessage);
		LOG.debug("---------------------------");
		
		//return Integer.parseInt(getMessage.getMsgId());
		
	}
	
	
	@Test
	public void doUpdate() throws Exception {
		
		// url호출, param전달
		MockHttpServletRequestBuilder creatMessage = MockMvcRequestBuilders.get("/question/do_update.do")	
														.param("questionNo", String.valueOf(question01.getQuestionNo()))
														.param("orderNo", String.valueOf(question01.getOrderNo()))
														.param("qUser", question01.getqUser() + "")
														.param("title", question01.getTitle() + "수정con2")
														.param("contents", question01.getContents() + "수정con2");
												
		/*
		 * 스트링 부트의 경우 2.2.0부터 APPLICATION_JSON_UTF8_VALUE는 포함X
		 * MediaType.APPLICATION_JSON
		 */
		ResultActions resultActions = mockMvc.perform(creatMessage)
				  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				  .andExpect(status().isOk());
		
		/*
		(이후 루트)			 		
		 1.Json 사용할 경우 ->  andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))	)
						 ->  andExpect(status().isOk()) 메서드						  
		*/
		
		// 출력결과 요약
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
	
		LOG.debug("---------------------------");
		LOG.debug("result : " + result);
		LOG.debug("---------------------------");
		
		Gson gson = new Gson();
		Message getMessage =  gson.fromJson(result, Message.class);
		
		LOG.debug("---------------------------");
		LOG.debug("getMessage : " + getMessage);
		LOG.debug("---------------------------");
		
		//return Integer.parseInt(getMessage.getMsgId());
		
	}
	
	@Test
	public void doSelectOne() throws Exception{
		
		// url호출, param전달
		MockHttpServletRequestBuilder creatMessage = MockMvcRequestBuilders.get("/question/do_selectOne.do")
														.param("questionNo", String.valueOf(question01.getQuestionNo()));
		
		ResultActions resultActions = mockMvc.perform(creatMessage)
									  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
									  .andExpect(status().isOk());
		
		/*
		(이후 루트)			 		
		 1.Json 사용할 경우 ->  andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))	)
						 ->  andExpect(status().isOk()) 메서드						  
		*/
		
		// 출력결과 요약
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
	
		LOG.debug("---------------------------");
		LOG.debug("result : " + result);
		LOG.debug("---------------------------");
		
		Gson gson = new Gson();
		QuestionVO outVO =  gson.fromJson(result, QuestionVO.class);
		
		LOG.debug("---------------------------");
		LOG.debug("outVO : " + outVO);
		LOG.debug("---------------------------");
		
		checkUser(question01, outVO);
		
		// return outVO;
	}
	
	
	@Test
	public void doRetrieve() throws Exception {
		
		// url호출, param전달 (searchDiv가 10,20에 따라 관리자 설정이 변화)
		MockHttpServletRequestBuilder creatMessage = MockMvcRequestBuilders.get("/question/do_retrieve.do")	
													.param("searchDiv", search.getSearchDiv())
													.param("searchWord", search.getSearchWord())
													.param("pageSize", String.valueOf(search.getPageSize()))	
													.param("pageNum", String.valueOf(search.getPageNum()));
		
		/*
		 * 스트링 부트의 경우 2.2.0부터 APPLICATION_JSON_UTF8_VALUE는 포함X
		 * MediaType.APPLICATION_JSON
		 */
		ResultActions resultActions = mockMvc.perform(creatMessage)
									  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
									  .andExpect(status().isOk());
		
		/*
		(이후 루트)			 		
		 1.Json 사용할 경우 ->  andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))	)
						 ->  andExpect(status().isOk()) 메서드						  
		*/
		
		// 출력결과 요약
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		
		//gson -> List
		Gson gson = new Gson();
		
		List<QuestionVO> list = gson.fromJson(result, new TypeToken<List<QuestionVO>>() {}.getType());
		
		for(QuestionVO vo : list) {
			LOG.debug("vo : " + vo);
		}
		
	
	}
	
	private void checkUser(QuestionVO vsVo, QuestionVO question) {
		//비교
		assertThat(vsVo.getQuestionNo(), is(question.getQuestionNo()));
		assertThat(vsVo.getOrderNo(), is(question.getOrderNo()));
		assertThat(vsVo.getqUser(), is(question.getqUser()));
		
		//컬럼추가 :
		assertThat(vsVo.getTitle(), is(question.getTitle()));
		assertThat(vsVo.getContents(), is(question.getContents()));
	}
	
	@Test
	public void beans() {
		LOG.debug("webApplicationContext : " +webApplicationContext);
		LOG.debug("mockMvc : " +mockMvc);
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}
