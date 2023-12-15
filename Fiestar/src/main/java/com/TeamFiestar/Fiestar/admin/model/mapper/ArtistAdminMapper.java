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

	int insertImage(ProductImage img);

}
