package com.TeamFiestar.Fiestar.shop.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductImage {

	
	private int productImage;   //상품 이미지 번호
	private String productImagePath; //상품 이미지 경로
	private String productImageRename; //변경된 이미지 파일 이름
	private String productImageOriginal; //원본 이미지 파일 이름
	private int productImageOrder; //이미지 파일 번호
	private int productNo; //상품 번호
	private String productProductionImage; //상품 설명 이미지
	
	private MultipartFile uploadFile;
}


