package com.khit.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.study.entity.Board;

//JPARepository를 상속해야 함
public interface BoardRepository extends JpaRepository<Board, Integer>{
	//쿼리 메서드 실습
	//제목으로 검색
	List<Board> findByTitle(String searchKeyword);
	
	//제목이 포함된 키워드 검색
	List<Board> findByTitleContaining(String searchKeyword);
	
	//제목 or 내용
	List<Board> findByTitleContainingOrContentContaining(String title, 
			String content);
}
