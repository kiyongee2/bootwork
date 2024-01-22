package com.khit.board.unittest;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.board.entity.Board;
import com.khit.board.entity.Member;
import com.khit.board.repository.BoardRepository;
import com.khit.board.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RelationMappingTest {
	//sql이 제공 - MemberRepository - save(), findById(), deleteById()
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	/*@Test
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
	}*/
	
	//전체 조회(회원목록, 글목록)
	/*@Test
	public void testSelectAll() {
		//회원 목록
		List<Member> memberList = memberRepository.findAll();
		
		for(Member member : memberList) {
			log.info(member.toString());
		}
		
		//게시글 목록
		List<Board> boardList = boardRepository.findAll();
		
		boardList.forEach(board -> log.info(board.toString()));
	}*/
	
	//게시글 상세 조회
	/*@Test
	public void testSelectOne() {
		//4번 게시글 조회 - findById(4)
		Board board = boardRepository.findById(4).get();
		log.info(board.getId() + "번 게시글 정보");
		log.info("제목: " + board.getTitle());
		log.info("내용: " + board.getContent());
		log.info("작성자: " + board.getMember().getName());
		log.info("작성자 권한: " + board.getMember().getRole());
	}*/
	
	//특정 회원이 게시한 글 조회
	@Test
	public void testSelect() {
		//뽀로로가 쓴 게시글 조회
		Optional<Member> member = memberRepository.findByMemberId("member1");
		
		log.info(member.get().getName() + "가 작성한 게시글 목록");
		
		List<Board> boardList = member.get().getBoardList();
		for(Board board : boardList)
			log.info(board.toString());
	}
	
	//특정 회원 삭제
	@Test
	public void testDelete() {
		memberRepository.deleteById(2);
		
		//회원 목록
		List<Member> memberList = memberRepository.findAll();
		
		for(Member member : memberList) {
			log.info(member.toString());
		}
		
		//게시글 목록
		List<Board> boardList = boardRepository.findAll();
		
		boardList.forEach(board -> log.info(board.toString()));
	}
	
}










