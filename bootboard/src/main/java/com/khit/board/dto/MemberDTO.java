package com.khit.board.dto;

import lombok.Data;

@Data
public class MemberDTO {
	private Long id;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private int memberAge;
}
