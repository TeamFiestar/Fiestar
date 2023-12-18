package com.TeamFiestar.Fiestar.shop.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
	private int productNo;
	private int artistGroupNo;
	private String productName;
	private String productImage;
	private String productProduction;
	private int productPrice;
	private String artistGroupTitle;
	
	private String productImageThumbnail;
	private String productImageThumbnailRename;
	
	private String productImageContent;
	private String productImageRename;

	
	private List<ProductImage> imageList;
	
	private List<ProductOption> productOptionList;

}
