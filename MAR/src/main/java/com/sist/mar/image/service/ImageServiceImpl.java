package com.sist.mar.image.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.image.dao.ImageDaoImpl;
import com.sist.mar.image.domain.ImageVO;

@Service
public class ImageServiceImpl {

//	▼ 변수 ===============================================================

	final Logger LOG = LoggerFactory.getLogger(ImageServiceImpl.class);
	
	@Autowired
	private ImageDaoImpl imageDao;
	
	
//	▼ 생성자 ==============================================================	

	public ImageServiceImpl() {}
	
	
//	▼ 메소드 ===============================================================	

	public int upRegisterImages(List<ImageVO> imageList, String fromTb) throws Exception {
		int flag = 0;

		if (imageList.size() > 0) {

			if (fromTb.equals("1")) {
				int itemSeqCurr = imageDao.doInquireItemSeqCurr();
				
				for (ImageVO vo : imageList) {
					vo.setFromTb(Integer.parseInt(fromTb));
					vo.setFromNo(itemSeqCurr);
					flag = imageDao.doInsert(vo);
				}

			} else if (fromTb.equals("2")) {
				int recipeSeqCurr = imageDao.doInquireRecipeSeqCurr();

				for (ImageVO vo : imageList) {
					vo.setFromTb(Integer.parseInt(fromTb));
					vo.setFromNo(recipeSeqCurr);
					flag = imageDao.doInsert(vo);
				}
			}

		}

		return flag;
	}
	
	public int upDeleteImages(List<ImageVO> imageList) throws Exception {
		int flag = 0; 
	
		if(imageList.size() > 0) {
			for(ImageVO vo : imageList) {
				flag = imageDao.doDeleteOne(vo);
			}
		}
		return flag;
	}
	
	public int upUpdateImages(int fromTb, int fromNo, List<ImageVO> imageListDel, List<ImageVO> imageListNew) throws Exception {
		int flag = 0;
		
		if(imageListDel.size() > 0) {
			for(ImageVO vo : imageListDel) {
				flag = imageDao.doDeleteOne(vo);
			}
		}
		
		if(imageListNew.size() > 0) {
			for(ImageVO vo : imageListNew) {
				vo.setFromTb(fromTb);
				vo.setFromNo(fromNo);
				flag = imageDao.doInsert(vo);
			}
		}
		return flag;
	}
	
	public List<ImageVO> doRetrieveImages(ImageVO image) throws Exception {
		return imageDao.doRetrieve(image);
	}
	
	
}
