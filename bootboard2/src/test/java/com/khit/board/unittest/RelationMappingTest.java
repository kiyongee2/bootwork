package com.khit.board.unittest;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.board.entity.Board;
import com.khit.board.entity.Member;
import com.khit.board.repository.BoardRepository;
import com.khit.board.repository.MemberRepository;

@SpringBootTest
public class RelationMappingTest {
	//sql이 제공 - MemberRepository - save(), findById(), deleteById()
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void testInsertData() {
		//일반 회원
		Member member1 = new Member();
		member1.setMemberId("member1");
		member1.setPassword("member111");
		member1.setName("뽀로로");
		member1.setRole("USER");
		
		memberRepository.save(member1);
		
		//관리자
		Member member2 = new Member();
		member2.setMemberId("member2");
		member2.setPassword("member222");
		member2.setName("아기상어");
		member2.setRole("ADMIN");
		
		memberRepository.save(member2);
		
		//회원이 등록한 글
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setTitle("뽀로로가 등록한 글제목 " + i);
			board.setContent("뽀로로가 등록한 글 내용 " + i);
			board.setMember(member1);
			board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			
			boardRepository.save(board);
		}
		
		//관리자가 등록한 글
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setTitle("아기상어가 등록한 글제목 " + i);
			board.setContent("아기상어가 등록한 글 내용 " + i);
			board.setMember(member2);
			board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			
			boardRepository.save(board);
		}
	}
}
