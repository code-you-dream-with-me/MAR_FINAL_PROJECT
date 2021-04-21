package com.sist.mar.answer.domain;

import com.sist.mar.cmn.DTO;

public class Answer extends DTO{
	
	private int answerNo;
	private int questionNo;
	private String aUser;
	private String title;
	private String contents;
	private String regDt;
	
	public Answer() {}

	public Answer(int answerNo, int questionNo, String aUser, String title, String contents, String regDt) {
		super();
		this.answerNo = answerNo;
		this.questionNo = questionNo;
		this.aUser = aUser;
		this.title = title;
		this.contents = contents;
		this.regDt = regDt;
	}

	public int getAnswerNo() {
		return answerNo;
	}

	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}

	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public String getaUser() {
		return aUser;
	}

	public void setaUser(String aUser) {
		this.aUser = aUser;
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

	@Override
	public String toString() {
		return "Answer [answerNo=" + answerNo + ", questionNo=" + questionNo + ", aUser=" + aUser + ", title=" + title
				+ ", contents=" + contents + ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}
	
	
}
