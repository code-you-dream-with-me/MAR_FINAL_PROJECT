package com.sist.mar.order.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cart.dao.CartDao;
import com.sist.mar.cart.domain.Cart;
import com.sist.mar.cmn.DTO;
import com.sist.mar.order.dao.OrderDao;
import com.sist.mar.order.domain.Ordering;
import com.sist.mar.order.domain.Orderitem;

@Service
public class OrderServiceImpl implements OrderService {

	final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	public OrderDao orderDao;
	
	@Autowired
	public CartDao cartDao;
	
	//기본 생성자
	public OrderServiceImpl() {}
	
	// 주문 --------------------------------------------------------
	@Override
	public int doInsertOrdering(DTO dto) throws SQLException {
		return this.orderDao.doInsertOrdering(dto);
	}
	
	@Override
	public int doSelectOneOrderNo(String param) throws SQLException {
		return this.orderDao.doSelectOneOrderNo(param);
	}
	
	@Override
	public int doInsertOrderitem(String memberId, int orderNo) throws SQLException {
		//등록 성공 개수 체크를 위한 flag
		int vsflag = 0;
		
		//장바구니 내역 조회
		List<Cart> list = (List<Cart>) cartDao.doRetrieve(memberId);
		
		//등록에 필요한 값을 orderitem에 넣어주기(orderNo는 공통값)
		Orderitem orderitem = new Orderitem();
		orderitem.setOrderNo(orderNo);
		for(Cart cart : list) {
			orderitem.setItemNo(cart.getItemNo());
			orderitem.setQuantity(cart.getQuantity());
			vsflag += orderDao.doInsertOrderitem(orderitem);
		}
		
		//성공여부 //flag = 2(등록,삭제 성공)/1(등록 성공, 삭제 실패)/0(실패)
		int flag = 0;
		if(0 != vsflag) { //vsflag != 0 (등록성공)
			flag = 1;
			vsflag = orderDao.doDeleteCart(memberId);
			if(0 != vsflag) { //vsflag != 0 (삭제성공)
				flag = 2;
			}
		}
		
		return flag;
	}
	//-----------------------------------------------------------



}
