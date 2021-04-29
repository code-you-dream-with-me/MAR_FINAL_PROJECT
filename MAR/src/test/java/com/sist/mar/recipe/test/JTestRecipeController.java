package com.sist.mar.recipe.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
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
import com.sist.mar.image.domain.ImageVO;
import com.sist.mar.recipe.controller.RecipeController;
import com.sist.mar.recipe.domain.RecipeVO;
import com.sist.mar.recipe.domain.SimpleItemVO;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//웹 테스트를 위해 필요한 어노테이션
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class JTestRecipeController {

//	▼ 변수 ===============================================================
	
	final Logger LOG = LoggerFactory.getLogger(JTestRecipeController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	RecipeController recipeController;
	
	// 브라우저를 대신한 Mock
	MockMvc mockMvc;
	
	RecipeVO recipe01;
	RecipeVO recipe01New;
	RecipeVO recipeYakiudon;
	RecipeVO recipeYakiudonNew;
	RecipeVO recipetteokbokki;
	
	List<ImageVO> imageList;
	List<ImageVO> imageListNew;
	List<ImageVO> imageListYakiudon;
	List<ImageVO> imageListYakiudonDel;
	List<ImageVO> imageListYakiudonNew;
	List<ImageVO> imageListYakiudonDel2;
	List<ImageVO> imageListtteokbokki;
	
	RecipeVO recipe;
	RecipeVO recipeNew;
	List<ImageVO> images;
	List<ImageVO> imagesDel;
	List<ImageVO> imagesNew;
	List<ImageVO> imagesDel2;
	
	
//	▼ 테스트 ===============================================================

	@Before
	public void setUp() throws Exception {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		recipe01 = new RecipeVO(241, "123wodnr@naver.com", "레시피1", "레시피 컨텐츠1", 0, "재료1, 재료2, 재료3", "https://www.youtube.com/", null, null);
		recipe01New = new RecipeVO(202, "123wodnr@naver.com", "레시피1_new", "레시피 컨텐츠1_new", 5, "재료1_new, 재료2_new, 재료3_new", "https://www.youtube.com/new", null, null);

		recipeYakiudon = new RecipeVO(564, "123wodnr@naver.com", "볶음우동", "볶음우동 레시피", 0, "진간장, 식초, 황설탕, 물, 식용유, 양배추, 양파, 당근, 돼지고기, 대파, 우동면, 훈연멸치, 쪽파, 마요네즈", "https://www.youtube.com/embed/zRg4nxIv3j8", null, null);
		recipeYakiudonNew = new RecipeVO(506, "123wodnr@naver.com", "볶음우동_수정", "볶음우동 레시피_수정", 0, "진간장, 식초, 황설탕, 물, 식용유, 양배추, 양파, 당근, 돼지고기, 대파, 우동면, 훈연멸치, 쪽파, 마요네즈", "https://www.youtube.com/embed/zRg4nxIv3j8", null, null);
		
		recipetteokbokki = new RecipeVO(301, "123wodnr@naver.com", "떡볶이", "떡볶이 레시피", 0, "쌀떡, 밀가루떡, 사각어묵, 양배추, 대파, 물, 삶은달걀, 고추장, 진간장, 고운 고춧가루, 굵은 고춧가루, 황설탕, msg", "https://www.youtube.com/embed/t4Es8mwdYlE", null, null);
		
		imageList = Arrays.asList(
				new ImageVO(161, 1, "원본이름1", "저장이름1", "C:/20201123_eClass/04_SPRING/workspace/MAR", 135, "jpg", 0, 0, null),
				new ImageVO(162, 0, "원본이름2", "저장이름2", "C:/20201123_eClass/04_SPRING/workspace/MAR", 246, "jpg", 0, 0, null)
				);
		imageListNew = Arrays.asList(
				new ImageVO(0, 1, "원본이름3", "저장이름3", "C:/20201123_eClass/04_SPRING/workspace/MAR", 135, "jpg", 0, 0, null),
				new ImageVO(0, 0, "원본이름4", "저장이름4", "C:/20201123_eClass/04_SPRING/workspace/MAR", 246, "jpg", 0, 0, null)
				);
		imageListYakiudon = Arrays.asList(
				new ImageVO(371, 1, "볶음우동 메인이미지", "zRg4nxIv3j8볶음우동 메인이미지", "C:/20201123_eClass/04_SPRING/workspace/MAR", 135, "jpg", 0, 0, null),
				new ImageVO(372, 0, "볶음우동 부가이미지1", "zRg4nxIv3j8볶음우동 부가이미지1", "C:/20201123_eClass/04_SPRING/workspace/MAR", 246, "jpg", 0, 0, null),
				new ImageVO(373, 0, "볶음우동 부가이미지2", "zRg4nxIv3j8볶음우동 부가이미지2", "C:/20201123_eClass/04_SPRING/workspace/MAR", 357, "jpg", 0, 0, null)
				);
		imageListYakiudonDel = Arrays.asList(
				new ImageVO(372, 0, "볶음우동 부가이미지1", "zRg4nxIv3j8볶음우동 부가이미지1", "C:/20201123_eClass/04_SPRING/workspace/MAR", 246, "jpg", 0, 0, null),
				new ImageVO(373, 0, "볶음우동 부가이미지2", "zRg4nxIv3j8볶음우동 부가이미지2", "C:/20201123_eClass/04_SPRING/workspace/MAR", 357, "jpg", 0, 0, null)
				);
		imageListYakiudonNew = Arrays.asList(
				new ImageVO(0, 0, "볶음우동 부가이미지3", "zRg4nxIv3j8볶음우동 부가이미지1", "C:/20201123_eClass/04_SPRING/workspace/MAR", 246, "jpg", 0, 0, null),
				new ImageVO(0, 0, "볶음우동 부가이미지4", "zRg4nxIv3j8볶음우동 부가이미지2", "C:/20201123_eClass/04_SPRING/workspace/MAR", 357, "jpg", 0, 0, null)
				);
		imageListYakiudonDel2 = Arrays.asList(
				new ImageVO(371, 1, "볶음우동 메인이미지", "zRg4nxIv3j8볶음우동 메인이미지", "C:/20201123_eClass/04_SPRING/workspace/MAR", 135, "jpg", 0, 0, null),
				new ImageVO(372, 0, "볶음우동 부가이미지1", "zRg4nxIv3j8볶음우동 부가이미지1", "C:/20201123_eClass/04_SPRING/workspace/MAR", 246, "jpg", 0, 0, null),
				new ImageVO(373, 0, "볶음우동 부가이미지2", "zRg4nxIv3j8볶음우동 부가이미지2", "C:/20201123_eClass/04_SPRING/workspace/MAR", 357, "jpg", 0, 0, null)
				);
		imageListtteokbokki = Arrays.asList(
				new ImageVO(201, 0, "떡볶이 메인이미지", "t4Es8mwdYlE떡볶이 메인이미지", "C:/20201123_eClass/04_SPRING/workspace/MAR", 135, "jpg", 0, 0, null),
				new ImageVO(202, 0, "떡볶이 부가이미지1", "t4Es8mwdYlE떡볶이 부가이미지1", "C:/20201123_eClass/04_SPRING/workspace/MAR", 246, "jpg", 0, 0, null),
				new ImageVO(203, 0, "떡볶이 부가이미지2", "t4Es8mwdYlE떡볶이 부가이미지2", "C:/20201123_eClass/04_SPRING/workspace/MAR", 357, "jpg", 0, 0, null)
				);
		
		recipe = recipeYakiudon;
		recipeNew = recipeYakiudonNew;
		images = imageListYakiudon;
		imagesDel = imageListYakiudonDel;
		imagesNew = imageListYakiudonNew;
		imagesDel2 = imageListYakiudonDel2;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void cycle() throws Exception {
		
//		doInsert();
//		doUpdate();
//		doSelect();
//		doDelete();
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void doShowRelevantItem() throws Exception {
		
		recipe.setRecipeNo(563);
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/recipe/do_show_relevant_item.do")
				.param("recipeNo", Integer.toString(recipe.getRecipeNo()));
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String list = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("list: "+list);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		Gson gson = new Gson();
		List<SimpleItemVO> simpleItemList = gson.fromJson(list, new TypeToken<List<SimpleItemVO>>() {}.getType());
		
		for(SimpleItemVO vo : simpleItemList) {
			LOG.debug(vo.toString());
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void doInsert() throws Exception {
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/recipe/do_insert.do")
				.param("recipeNo", Integer.toString(recipe.getRecipeNo()))
				.param("regId", recipe.getRegId())
				.param("title", recipe.getTitle())
				.param("contents", recipe.getContents())
				.param("readCnt", Integer.toString(recipe.getReadCnt()))
				.param("ingredients", recipe.getIngredients())
				.param("urlAddr", recipe.getUrlAddr())
				.param("regDt", recipe.getRegDt())
				.param("modDt", recipe.getModDt());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("result: "+result);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(images.toArray());
		
		MockHttpServletRequestBuilder imageMessage = MockMvcRequestBuilders.get("/image/do_insert.do")
				.param("imageList", gsonString)
				.param("fromTb", "2");
		
		ResultActions imageActions = mockMvc.perform(imageMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String imageResult = imageActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("result: "+imageResult);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void doDelete() throws Exception {
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/recipe/do_delete.do")
				.param("recipeNo", Integer.toString(recipe.getRecipeNo()));
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("result: "+result);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(imagesDel2.toArray());
		
		MockHttpServletRequestBuilder imageMessage = MockMvcRequestBuilders.get("/image/do_delete.do")
				.param("imageList", gsonString);
		
		ResultActions imageActions = mockMvc.perform(imageMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String imageResult = imageActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("result: "+imageResult);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void doUpdate() throws Exception {
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/recipe/do_update.do")
				.param("recipeNo", Integer.toString(recipeNew.getRecipeNo()))
				.param("regId", recipeNew.getRegId())
				.param("title", recipeNew.getTitle())
				.param("contents", recipeNew.getContents())
				.param("readCnt", Integer.toString(recipeNew.getReadCnt()))
				.param("ingredients", recipeNew.getIngredients())
				.param("urlAddr", recipeNew.getUrlAddr())
				.param("regDt", recipeNew.getRegDt())
				.param("modDt", recipeNew.getModDt());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("result: "+result);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		int fromTb = 2;
		int fromNo = recipeNew.getRecipeNo();
		
		Gson gson = new Gson();
		String jsonStrDel = gson.toJson(imagesDel.toArray());
		String jsonStrNew = gson.toJson(imagesNew.toArray());
		
		MockHttpServletRequestBuilder imageMessage = MockMvcRequestBuilders.get("/image/do_update.do")
				.param("fromTb", Integer.toString(fromTb))
				.param("fromNo", Integer.toString(fromNo))
				.param("imageListDel", jsonStrDel)
				.param("imageListNew", jsonStrNew);
				
		ResultActions imageActions = mockMvc.perform(imageMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String imageResult = imageActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("result: "+imageResult);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}
	
	@SuppressWarnings("deprecation")
	public void doSelect() throws Exception {
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/recipe/do_select.do")
				.param("recipeNo", Integer.toString(recipe.getRecipeNo()));
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("result: "+result);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		Gson gson = new Gson();
		
		RecipeVO recipe = gson.fromJson(result, RecipeVO.class);
		LOG.debug(recipe.toString());
		
		
		MockHttpServletRequestBuilder imageMessage = MockMvcRequestBuilders.get("/image/do_retrieve.do")
				.param("fromTb", "2")
				.param("fromNo", Integer.toString(recipe.getRecipeNo()));
		
		ResultActions imageActions = mockMvc.perform(imageMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String imageResult = imageActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("result: "+imageResult);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		List<ImageVO> imageList = gson.fromJson(imageResult, new TypeToken<List<ImageVO>>() {}.getType());
		
		for(ImageVO vo : imageList) {
			LOG.debug(vo.toString());
		}
		
	}
	
	@Test
	@Ignore
	public void test() {
		LOG.debug("webApplicationContext:" + webApplicationContext);
		LOG.debug("mockMvc:" + mockMvc);
	}

}
