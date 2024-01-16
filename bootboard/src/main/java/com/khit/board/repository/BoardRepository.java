package com.khit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
   //기본 제공 메서드 - save(), findAll(), findById(), deleteById()
}
