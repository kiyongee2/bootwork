package com.khit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sample2Controller {
	//문자를 전달
	@GetMapping("/sample2")
	//@ResponseBody
	public @ResponseBody String test() {
		return "감사합니다.";
	}
}
