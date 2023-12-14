package com.TeamFiestar.Fiestar.admin.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;

public interface ArtistAdminService {

	Map<String, Object> ArtistNoticeList(Map<String, Object> map, int cp);

	int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice);

	/**상품 등록
	 * @param artistGroupTitle 
	 * @param paramMap
	 * @return
	 */
	int insertGoods(Product product,String artistGroupTitle, MultipartFile contentImg, MultipartFile thumbnailImg)throws IllegalStateException, IOException;
	Map<String, Object> selectReportList(String artistGroupTitle, Report report, int cp);

	Map<String, Object> selectPurchaseList(String artistGroupTitle, int cp);

}
