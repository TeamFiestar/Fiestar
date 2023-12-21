package com.TeamFiestar.Fiestar.cart.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderedList {
//	
//	private int productNo;
//	private String productName;
//	private int artistGroupNo;
//	private String productPrice;
//	private int productQuantity;
//	private int productOptionNo;
//	private String productOptionName;
//	private String productImageNo;
//	private String productImageThumbnail;
//	
//	
//	
//	private String memberNo;
//	
//	private String productImg;
//
//	private	int productCount;
//	
//	
//	private int cartNo;
//	private String eachPrice;
//	
//
//	
//	private String memberAddress;
//	private String memberNickname;
//	private String amountPrice;
//	
//	PRODUCT_NO
//	
//	
//	
//	SELECT PL.PRODUCT_NO, PRODUCT_NAME , ARTIST_GROUP_NO, PL.PRODUCT_PRICE, PRODUCT_QUANTITY, PL.PRODUCT_OPTION_NO,PRODUCT_OPTION_NAME,
//	
//	(SELECT PRODUCT_IMAGE_THUMBNAIL || PRODUCT_IMAGE_RENAME 
//	FROM "PRODUCT_IMAGE" IMAGE 
//	WHERE IMAGE.PRODUCT_NO = P.PRODUCT_NO
//	AND PRODUCT_IMAGE_ORDER=0) PRODUCT_IMAGE_THUMBNAIL
//	FROM PURCHASE_LIST PL
//	JOIN PRODUCT P ON (PL.PRODUCT_NO = P.PRODUCT_NO)
//	LEFT JOIN PRODUCT_OPTION O ON (PL.PRODUCT_NO = O.PRODUCT_NO)
//WHERE PURCHASE_NO = ${purchaseNo}
//	
//	
	private int productNo;
	private String productName; 
	private int artistGroupNo;
	
	private int productQuantity;
	private int productOptionNo;
	private String productOptionName;
	private String productImageThumbnail;
	private int purchaseNo;
	private int eachPrice;
	
	
	
	
	
	

}
