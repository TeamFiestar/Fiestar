package com.TeamFiestar.Fiestar.shop.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductImage {

	
	private int productImageNo;   //상품 이미지 번호
	private String productImageContent; //상품 설명 이미지
	private String productImageRename; //변경된 이미지 파일 이름
	private String productImageOriginal; //원본 이미지 파일 이름
	private int productImageOrder; //이미지 파일 번호
	private int productNo; //상품 번호
	private String productImageThumbnail; //상품 썸네일 이미지
	private String productImageThumbnailRename; //변경된 이미지 파일 이름
	

	private MultipartFile uploadFile;
}
