package com.khit.board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.board.config.SecurityUser;
import com.khit.board.entity.Reply;
import com.khit.board.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReplyController {
	
	private final ReplyService replyService;
	
	@PostMapping("/reply/{boardId}")
	public @ResponseBody String insertReply(
			@PathVariable Integer boardId,
			@RequestBody Reply reply,
			@AuthenticationPrincipal SecurityUser principal) {
		reply.setMember(principal.getMember());  //로그인한 회원
		replyService.insertReply(boardId, reply);
		return "댓글 등록 성공!";
	}
	
	//댓글 삭제
	@DeleteMapping("/reply/{replyId}")
	public @ResponseBody String deleteReply(
			@PathVariable Integer replyId) {
		replyService.deleteById(replyId);
		return "댓글 삭제 완료!";
	}
}








