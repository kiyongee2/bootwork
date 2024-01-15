package com.khit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khit.board.dto.MemberDTO;
import com.khit.board.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/member/join")
	public String joinForm() {
		return "/member/join";  //join.html
	}
	
	@PostMapping("/member/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		System.out.println("memberDTO:" + memberDTO);
		memberService.save(memberDTO);
		return "redirect:/"; //login.html
	}
	
}
