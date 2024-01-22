package com.khit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
