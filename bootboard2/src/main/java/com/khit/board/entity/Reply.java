package com.khit.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "t_reply")
@Entity
public class Reply extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200, nullable = false)
	private String content;
	
	//회원 1명이 여러 개의 댓글을 쓸수 있음
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Member member;
	
	//1개의 게시글엔 여러 개의 댓글이 달림
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Board board;
	
	
}
