package com.TeamFiestar.Fiestar.cart.model.dto;

import lombok.Getter; 
import lombok.Setter;

@Getter
@Setter

public class Cart {
	
	private String memberNo;
	private String productName;
	private String productImg;
	private int productPrice;
	private	int productCount;
	private int productNo;
	private String productOptionName;
	private int cartNo;
	private String eachPrice;
	
	private String productImageThumbnail;
	private String productImageThumbnailRename;
	
	private String memberAddress;
	private String memberNickname;
	private String amountPrice;
	public void setPurchaseNo(int purchaseNo) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
