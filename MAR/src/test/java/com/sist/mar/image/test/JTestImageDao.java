package com.sist.mar.image.test;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sist.mar.image.dao.ImageDaoImpl;
import com.sist.mar.image.domain.ImageVO;
import com.sist.mar.recipe.domain.RecipeVO;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                               "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class JTestImageDao {

//	▼ 변수 ===============================================================
	
	final static Logger LOG = Logger.getLogger(JTestImageDao.class);
	
	@Autowired
	ApplicationContext  context;//테스트 오브젝트가 만들어 지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.

	@Autowired
	ImageDaoImpl dao;
	
	ImageVO image01;
	RecipeVO recipe01;
	
	
	
//	▼ 테스트 ===============================================================
	
	@Before
	public void setUp() throws Exception {
		image01 = new ImageVO(6, 1, "원본이름1", "저장이름1", "C:/20201123_eClass/04_SPRING/workspace/MAR", 135, "jpg", 0, 0, null);
		recipe01 = new RecipeVO(49, "123wodnr@naver.com", "레시피1", "레시피 컨텐츠1", 0, "재료1, 재료2, 재료3", "https://www.youtube.com/", null, null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void doDeleteOne() throws SQLException {
		dao.doDeleteOne(image01);
	}
	
	@Test
	public void doRetrieve() throws SQLException {
		List<ImageVO> resultList = dao.doRetrieve(recipe01);
		for(ImageVO vo : resultList) {
			LOG.debug("vo: "+vo);
		}
	}
	
	@Test
	@Ignore
	public void beans() {
		LOG.debug("context: "+context);
		LOG.debug("dao: "+dao);
	}

}
