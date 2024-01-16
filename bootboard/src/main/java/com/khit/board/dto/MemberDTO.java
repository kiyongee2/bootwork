package com.khit.board.dto;

import com.khit.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDTO {
	private Long id;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private int memberAge;
	
	//entity를 dto로 변환하는 메서드
	public static MemberDTO toSaveDTO(Member member) {
		/*MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(member.getId());
		memberDTO.setMemberEmail(member.getMemberEmail());
		memberDTO.setMemberPassword(member.getMemberPassword());
		memberDTO.setMemberName(member.getMemberName());
		memberDTO.setMemberAge(member.getMemberAge());*/
		
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId())
				.memberEmail(member.getMemberEmail())
				.memberPassword(member.getMemberPassword())
				.memberName(member.getMemberName())
				.memberAge(member.getMemberAge())
				.build();
				
		return memberDTO;
	}
}
