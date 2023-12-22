package com.TeamFiestar.Fiestar.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.dto.ProductImage;
import com.TeamFiestar.Fiestar.shop.model.dto.ProductOption;

@Mapper
public interface ArtistAdminMapper {

	int selectArtistGroupNo(Object object);

	List<ArtistNotice> selectNoticeList(Map<String, Object> map, RowBounds rowBounds);

	int artistNoticeAdd(ArtistNotice notice);

	List<Report> selectReportList(Map<String, Object> map, RowBounds rowBounds);

	int reportListCount(Map<String, Object> map);

	int noticeListCount(Map<String, Object> map);

	List<Purchase> selectPurchaseList(Purchase searchPurchase, RowBounds rowBounds);

	int orderListCount(int artistGroupNo);

	
	//상품 등록
	int insertGoods(Product product);
	
	//이미지 업로드
	int insertImage(ProductImage img);

	//옵션 등록
	int insertOption(List<ProductOption> productOptionList);
	
	//상품 수정
	int updateGoods(Product product);
	
	//옵션 수정
	int updateOption(List<ProductOption> productOptionList);

	//이미지 수정
	int updateImage(ProductImage img);

	//상품 삭제
	int deleteGoods(int productNo);

	int selectAdminNo(String artistGroupTitle);



}
