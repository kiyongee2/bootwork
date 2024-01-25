package com.khit.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //데이터 전달이 역할인 어노테이션임
public class SampleController {

	@GetMapping("/sample")
	public String test() {
		return "안녕하세요";   //main -> main.html
	}
	
	//GET - SELECT
	@GetMapping("/khit")
	public String httpGet() {
		return "GET 요청 처리";
	}
	
	//POST - INSERT
	@PostMapping("/khit")
	public String httpPost() {
		return "POST 요청 처리";
	}
	
	//PUT - UPDATE
	@PutMapping("/khit")
	public String httpPut() {
		return "Put 요청 처리";
	}
	
	//DELETE - DELETE
	@DeleteMapping("/khit")
	public String httpDelete() {
		return "Delete 요청 처리";
	}
}








