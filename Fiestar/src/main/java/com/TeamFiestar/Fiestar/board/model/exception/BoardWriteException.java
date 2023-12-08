package com.TeamFiestar.Fiestar.board.model.exception;

public class BoardWriteException extends RuntimeException {
	
	// 생성자
		public BoardWriteException() {
			super("게시글 작성 중 예외 발생");
			
		}
		
		public BoardWriteException(String message) {
			super(message);
		}

}
