package com.khit.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Member;

//JpaRepository에게 상속 받음
public interface MemberRepository extends JpaRepository<Member, Integer>{
	//select * from member where member_id = ?;
	Optional<Member> findByMemberId(String string);

}
