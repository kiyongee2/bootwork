package com.khit.board.dto;

import com.khit.board.entity.Member;
import com.khit.board.entity.Role;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberDTO {
	
	private Integer id;
	
	private String memberId;
	
	private String password;
	
	private String name;
	
	private Role role;
	
	//entity(model<db>에 저장됨) -> dto(view로 보기)로 변환
	//목록보기, 상세보기
	public static MemberDTO toSaveDTO(Member member) {
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId())
				.memberId(member.getMemberId())
				.password(member.getPassword())
				.name(member.getName())
				.role(member.getRole())
				.build();
		
		return memberDTO;
	}
}
