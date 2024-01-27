package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khit.board.config.SecurityUser;
import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.entity.Role;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final PasswordEncoder pwEncoder;
	

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

	public void save(MemberDTO memberDTO) {
		//1. 비밀번호 암호화
		//2. 권한 설정
		String encPW = pwEncoder.encode(memberDTO.getPassword());
		memberDTO.setPassword(encPW);
		memberDTO.setRole(Role.MEMBER);
		
		//dto -> entity 변환 메서드
		Member member = Member.toSaveEntity(memberDTO);
		memberRepository.save(member);
	}

	public List<MemberDTO> findAll() {
		//db에서 memberList를 가져옴
		List<Member> memberList = memberRepository.findAll();
		
		//비어있는 memberDTOList를 생성
		List<MemberDTO> memberDTOList = new ArrayList<>();
		
		//memberDTOList에 memberDTO를 저장함
		for(Member member : memberList) {
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			memberDTOList.add(memberDTO);
		}
		return memberDTOList;
	}

	public MemberDTO findById(Integer id) {
		//db에서 member 엔티티를 꺼내옴
		Optional<Member> findMember = memberRepository.findById(id);
		if(findMember.isPresent()) { //회원 정보가 있으면 
			//entity -> dto 변환
			MemberDTO memberDTO = MemberDTO.toSaveDTO(findMember.get());
			return memberDTO; //정보를 가져와서 반환
		}else {
			throw new BootBoardException("페이지를 찾을 수 없습니다.");
		}
	}

	public void deleteById(Integer id) {
		memberRepository.deleteById(id);
	}

	public MemberDTO findByMemberId(SecurityUser principal) {
		Optional<Member> member = memberRepository.findByMemberId(principal.getUsername());
		//변환
		MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
		return memberDTO;
	}

	public void update(MemberDTO memberDTO) {
		//암호화, 권한 설정
		String encPW = pwEncoder.encode(memberDTO.getPassword());
		memberDTO.setPassword(encPW);
		memberDTO.setRole(Role.MEMBER);
		
		//변환시 엔티티 메서드를 toSaveUpdate()로 바꿔줌
		Member member = Member.toSaveUpdate(memberDTO);
		
		memberRepository.save(member);
	}
}
