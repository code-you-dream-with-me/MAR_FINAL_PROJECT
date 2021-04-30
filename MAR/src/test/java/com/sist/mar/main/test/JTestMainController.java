package com.sist.mar.main.test;

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
import com.sist.mar.main.domain.CateSearchVO;
import com.sist.mar.main.domain.MainRecipeVO;
import com.sist.mar.main.domain.MainVO;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)//수행순서 정하기
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestMainController {
	
	final Logger LOG = LoggerFactory.getLogger(JTestMainController.class);
	@Autowired
	WebApplicationContext webApplicationContext;
	//브라우저를 대신할 Mock
	MockMvc mockMvc;
	//검색 전역변수
	CateSearchVO search;
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("#Controller Test Before>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		search = new CateSearchVO(10,"30","",10,1);
		
		//mockMvc객체 만들기
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("#Controller Test After>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	@Test
	@Ignore
	public void doRetrieve() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/main/do_retrieve.do")
				.param("categoryNo", String.valueOf(search.getCategoryNo()))//CateSearchVO에 있는 변수이름과 같게
				.param("listDiv", search.getListDiv())
				.param("searchWord", search.getSearchWord())
				.param("pageSize", String.valueOf(search.getPageSize()))
				.param("pageNum", String.valueOf(search.getPageNum()));

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))//application/json;charset=UTF-8
				.andExpect(status().isOk());//isOk()대신 is2xxSuccessful()로 사용해도 된다 (상태값 200은 서버가 제대로 작동한다는 의미니까)
		//출력 결과 요약
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		//json -> List
		Gson gson = new Gson();
		List<MainVO> list = gson.fromJson(result, new TypeToken<List<MainVO>>() {}.getType());
		
		for(MainVO vo : list) {
			LOG.debug("vo: "+vo);
		}
	}
	
	@Test
	public void doRecipeRetrieve() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/main/do_recipe_retrieve.do")
				.param("categoryNo", String.valueOf(search.getCategoryNo()))//CateSearchVO에 있는 변수이름과 같게
				.param("listDiv", search.getListDiv())
				.param("searchWord", search.getSearchWord())
				.param("pageSize", String.valueOf(search.getPageSize()))
				.param("pageNum", String.valueOf(search.getPageNum()));

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))//application/json;charset=UTF-8
				.andExpect(status().isOk());//isOk()대신 is2xxSuccessful()로 사용해도 된다 (상태값 200은 서버가 제대로 작동한다는 의미니까)
		//출력 결과 요약
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		//json -> List
		Gson gson = new Gson();
		List<MainRecipeVO> list = gson.fromJson(result, new TypeToken<List<MainRecipeVO>>() {}.getType());
		
		for(MainRecipeVO vo : list) {
			LOG.debug("vo: "+vo);
		}
	}

	@Test
	@Ignore
	public void test() {
		LOG.debug("webApplicationContext: "+webApplicationContext);
		LOG.debug("mockMvc: "+mockMvc);
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}
