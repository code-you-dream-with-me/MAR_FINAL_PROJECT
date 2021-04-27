package com.sist.mar.question.domain;

import com.sist.mar.cmn.DTO;

public class QuestionVO extends DTO{

	private int questionNo;		// 후기글 번호(PK, 시퀀스 적용)
	private int orderNo;		// 주문번호 (원칙적으로 FK)
	private String qUser;		// 회원 이메일(ID)
	private String title;		// 제목
	private String contents;	// 내용
	private String regDt;		// 등록일
	private int answerCheck;	// 답변 체크
	
	public QuestionVO() {
		
	}

	public QuestionVO(int questionNo, int orderNo, String qUser, String title, String contents, String regDt) {
		super();
		this.questionNo = questionNo;
		this.orderNo = orderNo;
		this.qUser = qUser;
		this.title = title;
		this.contents = contents;
		this.regDt = regDt;
	}


	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getqUser() {
		return qUser;
	}

	public void setqUser(String qUser) {
		this.qUser = qUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public int getAnswerCheck() {
		return answerCheck;
	}

	public void setAnswerCheck(int answerCheck) {
		this.answerCheck = answerCheck;
	}

	@Override
	public String toString() {
		return "QuestionVO [questionNo=" + questionNo + ", orderNo=" + orderNo + ", qUser=" + qUser + ", title=" + title
				+ ", contents=" + contents + ", regDt=" + regDt + ", answerCheck=" + answerCheck + ", toString()="
				+ super.toString() + "]";
	}
	

	
	
	
}
