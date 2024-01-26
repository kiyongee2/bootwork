package com.khit.board.service;

import org.springframework.stereotype.Service;

import com.khit.board.entity.Board;
import com.khit.board.entity.Reply;
import com.khit.board.repository.BoardRepository;
import com.khit.board.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;

	public void insertReply(Integer boardId, Reply reply) {
		//해당 게시글을 가져와서
		Board board = boardRepository.findById(boardId).get();
		//board 객체를 reply에 객체에 저장하고
		reply.setBoard(board);
		//댓글 저장
		replyRepository.save(reply);
	}

	public void deleteById(Integer replyId) {
		replyRepository.deleteById(replyId);
	}
}
