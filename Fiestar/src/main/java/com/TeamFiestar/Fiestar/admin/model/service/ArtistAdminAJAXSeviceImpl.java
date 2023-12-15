package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminAJAXMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistAdminAJAXSeviceImpl implements ArtistAdminAJAXService{
	
	private final ArtistAdminAJAXMapper mapper;
	
	@Override
	public ArtistNotice selectArtistNotice(Map<String, Object> map) {
		return mapper.selectArtistNotice(map);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return mapper.deleteNotice(noticeNo);
	}
	
	@Override
	public int updateNotice(ArtistNotice inputNotice) {
		return mapper.updateNotice(inputNotice);
	}
	
	@Override
	public int updatePurchase(Purchase purchase) {
		return mapper.updatePurchase(purchase);
	}
	
	@Override
	public Purchase selectPurchaseDetails(int purchaseNo) {
		return mapper.selectPurchaseDetails(purchaseNo);
	}

}
