package com.TeamFiestar.Fiestar.admin.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Purchase {

	private int purchaseNo;
	private int purchasePrice;
	private int purchaseStatus;
	private int memberNo;
	private int artistGroupNo;
	private int productSum;
	private int key;
	
	private String purchaseDate;
	private String memberNickname;
	private String memberAddress;
	private String orderSearch;
}
