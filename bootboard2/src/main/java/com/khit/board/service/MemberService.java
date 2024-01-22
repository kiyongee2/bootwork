package com.khit.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.board.entity.Member;
import com.khit.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;

	public Member login(Member member) {
		// db에서 아이디 조회
		Optional<Member> findMember = 
				memberRepository.findByMemberId(member.getMemberId());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}
}
