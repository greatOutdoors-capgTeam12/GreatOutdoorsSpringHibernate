package com.capgemini.go.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

	@RequestMapping(value = "/hello")
	@ResponseBody
	public String helloWorld() {
		return "hello world";
	}
}
