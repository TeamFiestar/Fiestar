package com.TeamFiestar.Fiestar.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // 기본 생성자
@Getter
@Setter
@ToString
public class PurchaseList {
	private int PurcharseList;
	private int PurcharseNo;
	private int PurcharseQuantity;
	private int PurcharsePrice;
	private int productNo;
	
	private String PurchaseStatus;
	private String PurchaseDate;
	private String ProductName;
	private String ProductImage;
	
	
}
