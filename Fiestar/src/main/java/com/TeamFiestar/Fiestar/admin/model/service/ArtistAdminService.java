package com.TeamFiestar.Fiestar.admin.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;

public interface ArtistAdminService {

	List<ArtistNotice> ArtistNoticeList(Map<String, Object> map, int cp);

	int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice);

	List<Report> selectReportList(String artistGroupTitle, Report report, int cp);
	
	/**상품 등록
	 * @param artistGroupTitle 
	 * @param paramMap
	 * @return
	 */
	int insertGoods(Product product, List<MultipartFile> images, String artistGroupTitle)throws IllegalStateException, IOException;

}
