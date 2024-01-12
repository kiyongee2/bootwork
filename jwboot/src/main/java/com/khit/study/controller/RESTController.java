package com.khit.study.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khit.study.entity.BoardVO;
import com.khit.study.service.BoardRESTService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//문자열을 반환하는 어노테이션
//위치: 매서드에 위치함 - @ResponseBody, @ResponseEntity와 비슷함

//@AllArgsConstructor // 생성자 주입
@RestController  //객체를 문자열로 변환하는 클래스임
public class RESTController {
	
	@Autowired
	private BoardRESTService boardService;
	
	@GetMapping("/greeting")
	public String sayHello(String name) {
		return "hello~ " + name;  //문자열
	}
	
	//객체 데이터를 브라우저에 보내줌
	@GetMapping("/board/detail")
	public BoardVO getBoard() {
		BoardVO board = boardService.getBoard();
		return board;
	}
	
	@GetMapping("/board/list")
	public List<BoardVO> getBoardList(){
		List<BoardVO> boardList = boardService.getBoardList();
		return boardList;
	}
}
