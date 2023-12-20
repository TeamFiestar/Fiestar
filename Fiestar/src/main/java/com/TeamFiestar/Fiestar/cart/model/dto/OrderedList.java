package com.TeamFiestar.Fiestar.cart.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderedList {
	
	private int productNo;
	private String productName;
	private int artistGroupNo;
	private String productPrice;
	private int productQuantity;
	private int productOptionNo;
	private String productOptionName;
	private String productImageNo;
	private String productImageThumbnail;
	
	
	
	private String memberNo;
	
	private String productImg;

	private	int productCount;
	
	
	private int cartNo;
	private String eachPrice;
	

	
	private String memberAddress;
	private String memberNickname;
	private String amountPrice;
	

}
