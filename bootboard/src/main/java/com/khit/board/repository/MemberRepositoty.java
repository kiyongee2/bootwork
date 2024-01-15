package com.khit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Member;

// JPARepository를 상속받아야함(@Repository 생략 가능함)
public interface MemberRepositoty extends JpaRepository<Member, Long>{
  //제공된 메서드 - save(), findAll(), findById(), deleteById()
}
