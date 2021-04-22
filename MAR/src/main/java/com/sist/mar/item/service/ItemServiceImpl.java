package com.sist.mar.item.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cmn.DTO;
import com.sist.mar.item.dao.ItemDaoImpl;
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemDaoImpl itemDaoImpl;
	

	@Override
	public List<?> getAllList() throws SQLException {
		return itemDaoImpl.getAllList();
	}

	@Override
	public List<?> getRelatedList(DTO dto) throws SQLException {
		return itemDaoImpl.getRelatedList(dto);
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		return itemDaoImpl.doSelectOne(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		return itemDaoImpl.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		return itemDaoImpl.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		return itemDaoImpl.doInsert(dto);
	}

}
