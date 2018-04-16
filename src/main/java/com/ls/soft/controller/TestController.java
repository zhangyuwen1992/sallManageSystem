package com.ls.soft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping(value = "/test1", produces = { "application/json;charset=UTF-8" })
	public ModelAndView testController(){
		ModelAndView m = new ModelAndView("index");
		System.out.println(123);
		
		return m;
	}
	
	@RequestMapping(value = "/test2", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String testController2(){
		System.out.println(123);
		
		return "abc";
	}
	
	
}
