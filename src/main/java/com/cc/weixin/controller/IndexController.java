package com.cc.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {

	@ResponseBody
	@RequestMapping("/query")
	public String index(){
		return "hello world";
	}
}
