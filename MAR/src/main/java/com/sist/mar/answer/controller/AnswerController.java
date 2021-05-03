package com.sist.mar.answer.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.mar.answer.domain.Answer;
import com.sist.mar.answer.service.AnswerService;
import com.sist.mar.cmn.Message;
import com.sist.mar.item.domain.Item;

@Controller
public class AnswerController {

	final Logger LOG = LoggerFactory.getLogger(AnswerController.class);

	@Autowired
	AnswerService answerService;

	public AnswerController() {
	}

	@RequestMapping(value = "answer/answer_view.do", method = RequestMethod.GET)
	public String view01(Model model,@RequestParam(value = "questionNo", required = false)String questionNo) throws Exception {
		LOG.debug("=================");
		LOG.debug("=answer view=");
		LOG.debug("=================");
		
		model.addAttribute("questionNo", questionNo);
		return "answer/answer";
	}
	
	@RequestMapping(value = "answer/answer_moview.do",  method = RequestMethod.GET)
	public String view02(Model model, @RequestParam(value = "answerNo", required = false)String answerNo) throws Exception {
		LOG.debug("=================");
		LOG.debug("=answer view=");
		LOG.debug("=================");
		
		model.addAttribute("answerNo", answerNo);
		return "answer/answer_mod";
	}


	/**
	 * 답변 전체 목록 조회
	 * 
	 * @param dto
	 * @return JSON(Item)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "answer/get_all.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAllList() throws SQLException {
		List<Answer> list = (List<Answer>) answerService.getAllList();

		for (Answer vo : list) {
			LOG.debug(vo.toString());
		}

		// list to json
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);

		LOG.debug("jsonList:" + jsonList);

		return jsonList;

	}

	/**
	 * 문의 별 답변 조회
	 * 
	 * @param dto
	 * @return JSON(Item)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value = "answer/do_selectone.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Answer answer) throws SQLException {
		List<Answer> list = (List<Answer>) answerService.doSelectOne(answer);

		for (Answer vo : list) {
			LOG.debug(vo.toString());
		}

		// list to json
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);

		LOG.debug("jsonList:" + jsonList);

		return jsonList;
	}
	
   
	@RequestMapping(value = "answer/do_selectans.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String doSelectAnswer(Answer answer) throws SQLException {
		LOG.debug("param:"+answer);
		
		Answer outVO = (Answer) answerService.doSelectAnswer(answer);
		
		LOG.debug("outVO:"+outVO);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);
		LOG.debug("jsonStr:"+jsonStr);
		
		return jsonStr;
	}
	
	
	/**
	 * 답변 수정
	 * @param dto
	 * @return JSON(1:성공,0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */     
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "answer/do_update.do",method = RequestMethod.POST
			,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody       
	public String doUpdate(Answer answer) throws SQLException {
		LOG.debug("param:"+answer);
		
		int flag = answerService.doUpdate(answer);
		String resultMsg = "";
		if(1 == flag) {
			resultMsg = "답변이 수정되었습니다.";
		}else {
			resultMsg = "답변 수정에 실패했습니다";
		}
		
		Message message=new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson  gson=new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr:"+jsonStr);
		
		return jsonStr;
	}

	/**
	 * 답변 삭제
	 * 
	 * @param user
	 * @return JSON(1:성공. 0:실패)
	 * @throws SQLException
	 */
	@RequestMapping(value = "answer/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Answer answer) throws SQLException {
		LOG.debug("param:" + answer);

		int flag = answerService.doDelete(answer);
		String resultMsg = "";

		if (1 == flag) {
			resultMsg = "답변을 삭제하였습니다.";
		} else {
			resultMsg = "답변 삭제에 실패하셨습니다.";
		}

		Message message = new Message();
		message.setMsgId(flag + "");
		message.setMsgContents(resultMsg);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr:" + jsonStr);

		return jsonStr;
	}

	/**
	 * 답변 등록
	 * 
	 * @param dto
	 * @return JSON(1:성공. 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value = "answer/do_insert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Answer answer) throws SQLException {
		LOG.debug("param:" + answer);

		int flag = answerService.doInsert(answer);
		String resultMsg = "";

		if (1 == flag) {
			resultMsg = answer.getaUser() + "님 답변이 등록되었습니다.";
		} else {
			resultMsg = "답변 등록에 실패하셨습니다.";
		}

		Message message = new Message();
		message.setMsgId(flag + "");
		message.setMsgContents(resultMsg);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("jsonStr:" + jsonStr);

		return jsonStr;
	}

}
