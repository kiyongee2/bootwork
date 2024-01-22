package com.khit.board.unittest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.board.entity.Member;
import com.khit.board.repository.MemberRepository;

@SpringBootTest
public class RelationMappingTest {
	//sql이 제공 - MemberRepository - save(), findById(), deleteById()
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void testInsertData() {
		//일반 회원
		/*Member member1 = new Member();
		member1.setMemberId("member1");
		member1.setPassword("member111");
		member1.setName("뽀로로");
		member1.setRole("USER");
		
		memberRepository.save(member1);*/
		
		//관리자
		Member member2 = new Member();
		member2.setMemberId("member2");
		member2.setPassword("member222");
		member2.setName("아기상어");
		member2.setRole("ADMIN");
		
		memberRepository.save(member2);
	}
}
