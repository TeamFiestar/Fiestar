package com.TeamFiestar.Fiestar.shop.model.excption;

public class shopException extends RuntimeException {

	public shopException() {
		super("판매 상품 등록 중 예외발생");
	}
	
	public shopException(String message) {
		super(message);
	}
	
}
