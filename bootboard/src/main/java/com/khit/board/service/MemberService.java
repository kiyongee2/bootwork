package com.khit.board.service;

import org.springframework.stereotype.Service;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.repository.MemberRepositoty;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor //생성자 주입 방식 - final을 꼭 붙임
@Service
public class MemberService {

	private final MemberRepositoty memberRepositoty;

	public void save(MemberDTO memberDTO) {
		//db 안으로 저장(entity를 저장해야함)
		//dto를 entity로 변환 메서드 필요
		Member member = Member.toSaveEntity(memberDTO);

		memberRepositoty.save(member);
	}
	
}
