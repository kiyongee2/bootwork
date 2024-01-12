package com.khit.study.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.study.entity.Board;
import com.khit.study.repository.BoardRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {
	
	private BoardRepository boardRepository;

	public void save(Board board) {
		boardRepository.save(board);
	}

	public List<Board> findAll() {
		//정렬 - 오름차순
		//내림차순 - Sort 클래스 사용
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public Board findById(int id) {
		//1건 검색 - findById().get()
		return boardRepository.findById(id).get();
	}

	public void delete(int id) {
		//1건 삭제 - deleteById(id)
		boardRepository.deleteById(id);
	}

	public void update(Board board) {
		//수정일 직접 생성해줌
		board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		boardRepository.save(board);
	}
}
