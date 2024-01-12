package com.khit.study.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //매개변수 있는 생성자
@NoArgsConstructor  //기본 생성자
@Builder
@Entity
@Data
public class Board {
	@Id  //기본키(설정안하면 오류)
	@GeneratedValue(strategy=GenerationType.IDENTITY) //자동 순번
	private int id;
	
	@Column(length=400, nullable=false)
	private String title;
	
	@Column(length=30, nullable=false)
	private String writer;
	
	@Column(length=4000, nullable=false)
	private String content;
	
	@CreationTimestamp  //현재 날짜와 시간 자동 생성
	private Timestamp createdDate;
}
