package com.khit.board.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.khit.board.entity.Member;

public class SecurityUser extends User{
	private static final long serialVersionUID = 1L;
	
	private Member member;
	
	public SecurityUser(Member member) {
		//암호화 안된 상태는 "{noop}" + member.getPassword()을 사용함
		super(member.getMemberId(), member.getPassword(), 
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	public Member getMember() {
		return member;
	}
	
}
