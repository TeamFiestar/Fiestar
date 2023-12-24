package com.TeamFiestar.Fiestar.admin.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.common.utility.Util;
import com.TeamFiestar.Fiestar.mypage.dto.Pagination;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.dto.ProductImage;
import com.TeamFiestar.Fiestar.shop.model.dto.ProductOption;
import com.TeamFiestar.Fiestar.shop.model.excption.shopException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
public class ArtistAdminServiceImpl implements ArtistAdminService{
	
	private final ArtistAdminMapper mapper;
	
	
	@Value("${my.shopThumbnail.webpath}")
	private String thumbnailPath;  //웹 이미지 요청 경로
	@Value("${my.shopThumbnail.location}")
	private String thumbnailfolderPath;  //서버 저장 폴더 경로
	
	@Value("${my.shopContent.webpath}")
	private String contentPath;  //웹 이미지 요청 경로
	@Value("${my.shopContent.location}")
	private String contentfolderPath;  //서버 저장 폴더 경로
	
	

	
	
	// 아티스트 공지사항 조회
	@Override
	public Map<String, Object> ArtistNoticeList(Map<String, Object> map, int cp) {
	
		int artistGroupNo = mapper.selectArtistGroupNo(map.get("artistGroupTitle"));
	
		map.put("artistGroupNo", artistGroupNo);
		
		int listCount = mapper.noticeListCount(map);
		
		/* Pagination */
		Pagination pagination = new Pagination(cp, listCount, 10, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		/* Pagination */

		List<ArtistNotice> noticeList = mapper.selectNoticeList(map, rowBounds);
		
		map.put("noticeList", noticeList);
		map.put("pagination", pagination);
		
		
		return map;
	}
	
	// 아티스트 공지사항 삽입
	@Override
	public int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice) {
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
		notice.setArtistGroupNo(artistGroupNo);
		return mapper.artistNoticeAdd(notice);
	}
	
	// 아티스트 신고 조회
	@Override
	public Map<String, Object> selectReportList(String artistGroupTitle, Report report, int cp) {
		Map<String, Object> map = new HashMap<>();   
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);   
		
		map.put("artistGroupNo",artistGroupNo);
		map.put("report",report);
		int listCount = mapper.reportListCount(map);
		
		/* Pagination */
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		/* Pagination */
		
		List<Report> reportList = mapper.selectReportList(map, rowBounds);
		
		map.put("reportList", reportList);
		map.put("pagination", pagination);
		
		
		return map;
	}
	
	
	// 구매 목록 조회
	@Override
	public Map<String, Object> selectPurchaseList(String artistGroupTitle, Purchase searchPurchase, int cp) {
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
		
		int listCount = mapper.orderListCount(artistGroupNo);
		
		searchPurchase.setArtistGroupNo(artistGroupNo);
		
		/* Pagination */
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);   
		/* Pagination */
		
		List<Purchase> purchaseList = mapper.selectPurchaseList(searchPurchase, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("purchaseList", purchaseList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	
	//상품 등록
	@Override
	public int insertGoods(Product product, MultipartFile contentImg, MultipartFile thumbnailImg) throws IllegalStateException, IOException {


		int artistGroupNo = mapper.selectArtistGroupNo(product.getArtistGroupTitle());
		product.setArtistGroupNo(artistGroupNo);
		
		
		int result = mapper.insertGoods(product);
		
		if(result == 0) {
			return 0; 
		}
		
		int productNo = product.getProductNo();
		
		if(product.getProductOptionList() == null) {
			return 1;
		}else {
			for (ProductOption productOption2 : product.getProductOptionList()) {
				productOption2.setProductNo(productNo);
			}
			
			int result2 = mapper.insertOption(product.getProductOptionList());
			
			if(result2 == 0) {
				return 0; 
			}
		}
		
		
		ProductImage img = new ProductImage();
		
		
		img.setProductNo(productNo); 
		
		
		img.setProductImageContent(contentPath);
		img.setProductImageThumbnail(thumbnailPath);
	

		img.setProductImageRename(Util.fileRename(contentImg.getOriginalFilename()));
		img.setProductImageThumbnailRename(Util.fileRename(thumbnailImg.getOriginalFilename()));
		
		 
		img.setContentUploadFile(contentImg); 
		img.setThumbnailUploadFile(thumbnailImg); 
		
		result = mapper.insertImage(img);
		
		
		img.getContentUploadFile().transferTo(new File(contentfolderPath + img.getProductImageRename()));
		img.getThumbnailUploadFile().transferTo(new File(thumbnailfolderPath + img.getProductImageThumbnailRename()));
		
		return productNo;
	}
	
	
	//상품 수정
	@Override
	public int updateGoods(Product product, MultipartFile contentImg, MultipartFile thumbnailImg)throws IllegalStateException, IOException {
		
		int artistGroupNo = mapper.selectArtistGroupNo(product.getArtistGroupTitle());
		product.setArtistGroupNo(artistGroupNo);
		
		int result = mapper.updateGoods(product);
		
		if(result == 0) {
			return 0; 
		}
		
		int productNo = product.getProductNo();
		
		
		
		ProductImage img = new ProductImage();
		
		img.setProductNo(productNo); 
		
		img.setProductImageContent(contentPath);
		img.setProductImageThumbnail(thumbnailPath);
	
		img.setProductImageRename(Util.fileRename(contentImg.getOriginalFilename()));
		img.setProductImageThumbnailRename(Util.fileRename(thumbnailImg.getOriginalFilename()));
		
		img.setContentUploadFile(contentImg); 
		img.setThumbnailUploadFile(thumbnailImg);   
		
		result = mapper.updateImage(img);
		
		img.getContentUploadFile().transferTo(new File(contentfolderPath + img.getProductImageRename()));
		img.getThumbnailUploadFile().transferTo(new File(thumbnailfolderPath + img.getProductImageThumbnailRename()));
		
		return result;
	}
	
	//상품 삭제
	@Override
	public int deleteGoods(int productNo) {
		return mapper.deleteGoods(productNo);
	}
	
	
	@Override
	public int selectAdminNo(String artistGroupTitle) {
		
		return mapper.selectAdminNo(artistGroupTitle);
	}
	
	

	
	
	
			
			
			
			
		
	

}
