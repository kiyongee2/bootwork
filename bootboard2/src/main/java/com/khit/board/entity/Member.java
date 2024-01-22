package com.khit.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;  //회원번호
	
	@Column(name = "member_id", unique = true)
	private String memberId;  //아이디
	
	@Column(nullable = false)
	private String password;  //비밀번호
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column
	private String role;   //권한
}
