package com.khit.board.exception;

//사용자 정의 예외 클래스
public class CustomException extends RuntimeException{

	public CustomException(String message) {
		super(message);
	}

}
