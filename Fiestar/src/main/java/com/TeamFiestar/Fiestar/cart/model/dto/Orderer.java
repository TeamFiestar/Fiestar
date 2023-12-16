package com.TeamFiestar.Fiestar.cart.model.dto;

import java.util.List; 


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Orderer {
	

	private String purchaseStatus;
	private String purchaseDate;
	private int purchaseNo;
	private int MemberNo;
	private String ordererPhoneNumber;
	private String ordererAddress;
	private String ordererName;
	private String purchasePrice;
	
	
	
	private List<Cart> checkout;
	
	private String productName;
	private String productImg;
	private int productPrice;
	private	int productCount;
	private int productNo;
	private String productOptionName;
	private int cartNo;
	 
}
