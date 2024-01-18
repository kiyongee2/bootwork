package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class QueryMethodTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	//테스트 데이터 생성(200개)
	/*@BeforeEach
	public void dataCreate() {
		for(int i=1; i<=200; i++) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			board.setContent("테스트 내용 " + i);
			board.setWriter("테스터");
			board.setCreatedDate(now);
			
			boardRepository.save(board);
		}
	}*/
	
	/*@Test
	public void testFindByTitle() {
		//findByTitle() 호출
		List<Board> boardList = 
				boardRepository.findByTitle("테스트 제목 100");
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}*/
	
	/*@Test
	public void testFindByTitleContaining() {
		//findByTitle() 호출
		List<Board> boardList = 
				boardRepository.findByTitleContaining("테스트 제목 10");
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}*/
	
	/*@Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList =
		boardRepository.findByTitleContainingOrContentContaining("10", "17");
		
		boardList.forEach(board -> log.info(board.toString()));
	}*/
	
	/*@Test
	public void testFindByTitleContainingOrderByIdDesc() {
		List<Board> boardList =
				boardRepository.findByTitleContainingOrderByIdDesc("10");
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}*/
	
	/*@Test
	public void testFindByTitleContaining() {
		//0 => 1페이지
		//Pageable paging = PageRequest.of(0, 10);
		//Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
		Pageable paging = PageRequest.of(1, 10, Sort.Direction.DESC, "id");
		log.info("page: " + paging.getPageNumber()); //페이지 번호
		log.info("size: " + paging.getPageSize()); //페이지당 글 개수
		
		List<Board> boardList =
				boardRepository.findByTitleContaining("제목", paging);
		
		boardList.forEach(board -> log.info(board.toString()));
	}*/
	
	@Test
	public void testJpaPaging() {
		Pageable paging = PageRequest.of(1, 10, Sort.Direction.DESC, "id");
		
		Page<Board> pageInfo = 
				boardRepository.findByTitleContaining("제목", paging);
		
		//number(페이지 번호), totalPages, totalElements, content
		log.info("페이지번호=" + pageInfo.getNumber());
		log.info("페이지당 게시글 수=" + pageInfo.getSize());
		log.info("게시글 총개수=" + pageInfo.getTotalElements());
		log.info("게시글 총페이지수=" + pageInfo.getTotalPages());
	
		List<Board> boardList = pageInfo.getContent(); //게시글 목록 내용
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}
	
}
