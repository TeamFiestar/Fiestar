package com.TeamFiestar.Fiestar.shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
	private int productNo;
	private int artistGroupNo;
	private String productName;
	private String productImage;
	private String productProduction;
	private int productPrice;
	private String artistGroupTitle;
	
}
