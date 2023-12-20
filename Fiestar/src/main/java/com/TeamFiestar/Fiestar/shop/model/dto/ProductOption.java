package com.TeamFiestar.Fiestar.shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductOption {
	
	private int productOptionNo;
	private String productOptionName;
	private int productOptionPrice;
	private int productOptionOrder;
	private int productNo;
}
