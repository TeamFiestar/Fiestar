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
	private int purcharseList;
	private int purcharseNo;
	private int purcharseQuantity;
	private int purcharsePrice;
	private int productNo;
	
	private String PurchaseStatus;
	private String PurchaseDate;
	private String ProductName;
	private String ProductImage;
	
	
}
