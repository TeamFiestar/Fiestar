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
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.common.utility.Util;
import com.TeamFiestar.Fiestar.mypage.dto.Pagination;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.dto.ProductImage;
import com.TeamFiestar.Fiestar.shop.model.excption.shopException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
public class ArtistAdminServiceImpl implements ArtistAdminService{
	
	private final ArtistAdminMapper mapper;
	
	@Value("${my.shop.location}")
	private String folderPath;  //서버 저장 폴더 경로
	
	@Value("${my.shop.webpath}")
	private String webPath;  //웹 이미지 요청 경로
	
	
	@Override
		public List<ArtistNotice> ArtistNoticeList(Map<String, Object> map, int cp) {
		
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
			
			
			return noticeList;
		}
	
	@Override
	public int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice) {
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
		notice.setArtistGroupNo(artistGroupNo);
		return mapper.artistNoticeAdd(notice);
	}
	
	@Override
	public List<Report> selectReportList(String artistGroupTitle, Report report, int cp) {
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
		
		return mapper.selectReportList(map, rowBounds);
	}
	
	
	//상품 등록
	@Override
	public int insertGoods(Product product,String artistGroupTitle, MultipartFile contentImg, MultipartFile thumbnailImg) throws IllegalStateException, IOException {


		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
		product.setArtistGroupNo(artistGroupNo);
		int result = mapper.insertGoods(product);
		if(result == 0) {
			return 0; 
		}
		
		
		//조회한 상품번호 시퀀스값 저장
		int productNo = product.getProductNo();
		
		List<ProductImage> imageList = new ArrayList<>();
		
		ProductImage img = new ProductImage();
		
		img.setProductNo(productNo); 
		
		
		img.setProductImageContent(webPath);
		img.setProductImageThumbnail(webPath);
	
		//변경된 파일명
		img.setProductImageRename(Util.fileRename(contentImg.getOriginalFilename()));
		img.setProductImageThumbnailRename(Util.fileRename(thumbnailImg.getOriginalFilename()));
		
		//실제 업로드된 파일을 img에 세팅
		img.setUploadFile(contentImg);
		img.setUploadFile(thumbnailImg);
		
		result = mapper.insertImage(img);
		
		//업로드된 이미지를 서버(folderPath)에 저장
		img.getUploadFile().transferTo(new File(folderPath + img.getProductImageRename()));
		
		return productNo;
	}
			
			
			
			
		
	

}
