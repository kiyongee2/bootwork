package com.khit.study.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class BoardVO {
	private int id;
	private String title;
	private String writer;
	private String content;
	private Date createdDate;
}
