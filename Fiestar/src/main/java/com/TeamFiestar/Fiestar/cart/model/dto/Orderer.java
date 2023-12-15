package com.TeamFiestar.Fiestar.cart.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Orderer {
	
	private int MemberNo;
	private String ordererPhoneNumber;
	private String ordererAddress;
	private String ordererName;
	private int CartNo;
	private int totalPrice;
	
}
