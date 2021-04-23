package com.sist.mar.admin.test;

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
import com.sist.mar.admin.controller.AdminController;
import com.sist.mar.cmn.Search;
import com.sist.mar.recipe.domain.RecipeVO;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//웹 테스트를 위해 필요한 어노테이션
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class JTestAdminController {

//	▼ 변수 ===============================================================

	final Logger LOG = LoggerFactory.getLogger(JTestAdminController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	AdminController adminController;
	
	// 브라우저를 대신한 Mock
	MockMvc mockMvc;
	
	Search searchNull;
	Search searchRegDtAsc;
	Search searchRegDtDesc;
	Search searchReadCntAsc;
	Search searchReadCntDesc;
	
//	▼ 테스트 ===============================================================
	
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		searchNull = new Search(null, null, 0, 0);
		searchRegDtAsc = new Search("regDt", "desc", 0, 0);
		searchRegDtDesc = new Search("regDt", "asc", 0, 0);
		searchReadCntAsc = new Search("readCnt", "desc", 0, 0);
		searchReadCntDesc = new Search("readCnt", "asc", 0, 0);
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void RecipeCycle() throws Exception {
		
		Search searchVO = new Search();
		
		searchVO = searchNull;         //기본값. 등록일 내림차순
//		searchVO = searchRegDtAsc;     //등록일 오름차순
//		searchVO = searchRegDtDesc;    //등록일 내림차순
//		searchVO = searchReadCntAsc;   //조회수 오름차순
//		searchVO = searchReadCntDesc;  //조회수 내림차순
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/admin/do_retrieve_recipe.do")
				.param("searchDiv", searchVO.getSearchDiv())
				.param("searchWord", searchVO.getSearchWord());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		Gson gson = new Gson();
		
		List<RecipeVO> recipeList = gson.fromJson(result, new TypeToken<List<RecipeVO>>() {}.getType());

		for(RecipeVO vo : recipeList) {
			LOG.debug(vo.toString());
		}
		
		
	}
	
	
	public void view() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/admin/admin_view.do");
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().isOk())
		;	
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString()
		;
		
		LOG.debug("result: "+result);
		
	}
	
	

	@Test
	@Ignore
	public void test() {
		LOG.debug("webApplicationContext:" + webApplicationContext);
		LOG.debug("mockMvc:" + mockMvc);
	}

}
