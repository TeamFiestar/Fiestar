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
	private int purcharseListNo;
	private int purcharseNo;
	private int productQuantity;
	private int purcharsePrice;
	private int productNo;
	
	private int productOptionNo;
	private int productPrice;
	private String productName;
	private String productImageThumbnailRename;
	private String productOptionName;
	
}
