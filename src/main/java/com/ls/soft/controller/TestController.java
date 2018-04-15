package com.ls.soft.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("test")
public class TestController {

	@RequestMapping(value = "/test1", produces = { "application/json;charset=UTF-8" })
	public ModelAndView testController(){
		ModelAndView m = new ModelAndView("index");
		System.out.println(123);
		
		return m;
	}
	
}
