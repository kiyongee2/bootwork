package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	//게시글 생성
	@Test
	public void insertBoard() {
		/*Board board = new Board();
		board.setTitle("가입 인사");
		board.setWriter("김신입");
		board.setContent("안녕하세요.");
		board.setCreatedDate(new Timestamp(System.currentTimeMillis()));*/
		
		/*Board board = Board.builder()
				.title("test")
				.writer("tester")
				.content("좋은 하루 되세요~")
				.createdDate(new Timestamp(System.currentTimeMillis()))
				.build();*/
		
		//db에 저장
		//boardRepository.save(board);
	}
	
	@Test
	public void getBoardList() {
		//db의 게시글 목록 가져오기
		List<Board> boardList = boardRepository.findAll();
		
		//boardList 출력
		/*for(Board board : boardList)
			log.info(board.toString());*/
		
		//람다식
		boardList.forEach(board -> log.info(board.toString()));	
	}
	
	//1건 상세보기
	@Test
	public void getBoard() {
		//findById()와 get()을 사용
		Board board = boardRepository.findById(2).get();
		log.info(board.toString());
	}
	
	//수정하기
	@Test
	public void updateBoard() {
		//수정하려는 게시글을 가져와서(findById) 수정 처리(save)
		Board board = boardRepository.findById(1).get();
		board.setTitle("제목 수정");
		board.setContent("내용 수정입니다.");
		
		boardRepository.save(board);
	}
	
	//삭제하기
	@Test
	public void deleteBoard() {
		//3번 게시글 삭제
		boardRepository.deleteById(3);
	}
}
