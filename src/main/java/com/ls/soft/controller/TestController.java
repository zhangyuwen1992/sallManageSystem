package com.ls.soft.controller;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.soft.dao.UserInfoDAO;
import com.ls.soft.entity.UserInfo;

@Controller
@RequestMapping("test")
public class TestController {
	@Inject
	private UserInfoDAO userDao;

	@RequestMapping(value = "/test1", produces = { "application/json;charset=UTF-8" })
	public ModelAndView testController() {
		ModelAndView m = new ModelAndView("index");
		m.addObject("time", new Date());
		UserInfo userinfo = new UserInfo();
		userinfo = userDao.getUserById(1);
		System.out.println(userinfo);
		System.out.println(123);

		return m;
	}

	@RequestMapping(value = "/test2", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String testController2() {
		System.out.println(123);

		return "abc";
	}

}
