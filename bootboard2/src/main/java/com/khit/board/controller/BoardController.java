package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khit.board.entity.Board;
import com.khit.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	//게시글 전체 목록
	@GetMapping("/list")
	public String getList(Model model) {
		List<Board> boardList = boardService.findAll();
		model.addAttribute("boardList", boardList);
		return "/board/list";  //list.html
	}
	
	//게시글 상세 보기
	@GetMapping("/{id}")
	public String getBoard(@PathVariable Integer id,
			Model model) {
		Board board = boardService.findById(id);
		model.addAttribute("board", board);
		return "/board/detail";  //detail.html
	}
}
