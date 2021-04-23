package com.sist.mar.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	final Logger LOG = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value= "/main/main_view.do")
	public String main() {
		LOG.debug("******************************************************");
		LOG.debug("* 메인 화면 load *");
		LOG.debug("******************************************************");
		
		return "main/main";
	}

}
