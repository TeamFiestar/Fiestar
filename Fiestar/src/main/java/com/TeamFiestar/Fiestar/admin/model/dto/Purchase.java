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
	private String purchaseStatus;
	private String purchaseDate;
	private int memberNo;
	private int artistGroupNo;
	
	private int productSum;
	
}
