package com.sist.mar.recipe.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.sist.mar.recipe.dao.RecipeDaoImpl;
import com.sist.mar.recipe.domain.RecipeVO;
import com.sist.mar.recipe.domain.SimpleItemVO;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//웹 테스트를 위해 필요한 어노테이션
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class JTestRecipeDaoImpl {

//	▼ 변수 ===============================================================
	
	final Logger LOG = LoggerFactory.getLogger(JTestRecipeDaoImpl.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	RecipeDaoImpl dao;
	
	RecipeVO recipe;
	
	@Before
	public void setUp() throws Exception {
		recipe = new RecipeVO(564, "123wodnr@naver.com", "레시피1", "레시피 컨텐츠1", 0, "재료1, 재료2, 재료3", "https://www.youtube.com/", null, null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {
		LOG.debug(webApplicationContext.toString());
		LOG.debug(dao.toString());
		
		dao.doInsertRelevantItem(recipe);
		
		List<SimpleItemVO> list = dao.doRetrieveRelevantItem();
		LOG.debug(list.toString());
		
		
		for(SimpleItemVO vo : list) {
			LOG.debug(vo.toString());
		}
		
		dao.doDeleteRelevantItem();
		
		
	}

}
