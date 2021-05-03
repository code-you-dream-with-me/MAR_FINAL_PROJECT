package com.sist.mar.wishitem.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mar.cmn.DTO;
import com.sist.mar.cmn.Search;
import com.sist.mar.wishitem.domain.Wishitem;

@Repository
public class WishitemDao {

	final static Logger LOG = LoggerFactory.getLogger(WishitemDao.class);
	
	final String NAMESPACE = "com.sist.mar.wishitem";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public WishitemDao() {}

	
//=============================================================================
	/**
	 * 늘사는것목록 페이지 - 삭제버튼 
	 * param = wish_no
	 * return flag(성공=1/실패=0)
	 */
	public int doDelete(String param) throws SQLException {
		String statemet = NAMESPACE + ".doDelete";
		return this.sqlSessionTemplate.delete(statemet, param);
	}

//=============================================================================	
	/**
	 * 상품상세 페이지 - 늘사는것담기버튼
	 * param = member_id, item_no 
	 * return flag(성공=1/실패=0)
	 */
	public int doInsert(DTO dto) throws SQLException {
		Wishitem wishitem = (Wishitem) dto;
		String statement = this.NAMESPACE + ".doInsert";
		return this.sqlSessionTemplate.insert(statement, wishitem);
	}

//=============================================================================
	/**
	 * 늘사는것 -> 팝업창(장바구니로 이동하기 전 수량 선택)
	 * param = wish_no
	 * return Wishitem(wish전부 + item 가격,이름,할인,할인가격 + image 저장명,경로)
	 */
	public DTO doSelectOne(String param) throws SQLException {
		String statement = this.NAMESPACE + ".doSelectOne";
		return this.sqlSessionTemplate.selectOne(statement, param);
	}

//=============================================================================		
	/**
	 * 늘사는것목록 +)페이징
	 * param = searchWord(member_id), pageSize(페이지크기), pageNum(페이지번호)
	 * return list(wish전부 + item 가격,이름,할인,할인가격 + image 저장명,경로)
	 */
	public List<?> doRetrieve(DTO dto) throws SQLException {
		Search search = (Search) dto;
		String statement = this.NAMESPACE + ".doRetrieve";
		return this.sqlSessionTemplate.selectList(statement, search);
	}

//=============================================================================	
	/**
	 * wishitem에 이미 들어있는 상품 체크
	 * param = Wishitem(item_no, member_id)
	 * return int
	 * */
	public int wishitemCheck(DTO dto) throws SQLException {
		Wishitem wishitem = (Wishitem) dto;
		String statement = this.NAMESPACE + ".wishitemCheck";
		return this.sqlSessionTemplate.selectOne(statement, wishitem);
	}

//=============================================================================	


}
