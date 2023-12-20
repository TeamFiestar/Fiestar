package com.TeamFiestar.Fiestar.artist.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.mapper.AdminMapper;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.artist.model.dto.Artist;
import com.TeamFiestar.Fiestar.artist.model.mapper.ArtistMapper;
import com.TeamFiestar.Fiestar.common.utility.Util;
import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.notice.dto.ArtistGroupNotice;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService{
	private final ArtistMapper mapper;
	private final ArtistAdminMapper artistAdminMapper;
	private final AdminMapper adminMapper;
	
	@Value("${artist.mainimg.webpath}")
	private String mainimgpath;
	@Value("${artist.mainimg.location}")
	private String mainimgfolder;
	
	@Value("${artist.logoimg.webpath}")
	private String logoimgpath;
	@Value("${artist.logoimg.location}")
	private String logoimgfolder;
	
	@Value("${artist.profileimg.webpath}")
	private String profileimgpath;
	@Value("${artist.profileimg.location}")
	private String profileimgfolder;
	
	@Override
	public Map<String, Object> artistMember(String artistGroupTitle) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		
		Map<String, Object> map = new HashMap<>();
		List<ArtistGroup1> artistGroup = mapper.artistGroup(artistGroupTitle);
		List<Artist> artistList = mapper.artist(artistGroupNo);
		RowBounds rowBounds = new RowBounds(0,6);
		List<Media> artistGroupMedia = mapper.artistGroupMedia(rowBounds, artistGroupNo);
		RowBounds rowBounds2 = new RowBounds(0,8);
		List<Product> artistGroupProduct = mapper.artistGroupProduct(rowBounds2,artistGroupNo);
		RowBounds rowBounds3 = new RowBounds(0,3);
		List<ArtistNotice> artistGroupNotice = mapper.artistGroupNotice(rowBounds3, artistGroupNo);
		map.put("artistGroup", artistGroup);
		map.put("artistList", artistList);
		map.put("artistGroupMedia", artistGroupMedia);
		map.put("artistGroupProduct", artistGroupProduct);
		map.put("artistGroupNotice", artistGroupNotice);
		
		return map;
	}
	
	@Override
	public Map<String, Object> loginArtistMember(int memberNo, String artistGroupTitle) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		
		Map<String, Object> checkMap = new HashMap<>();
		checkMap.put("artistGroupNo", artistGroupNo);
		checkMap.put("memberNo", memberNo);
		int result = mapper.checkSubscribe(checkMap);
		
		Map<String, Object> map = new HashMap<>();
		List<ArtistGroup1> artistGroup = mapper.artistGroup(artistGroupTitle);
		List<Artist> artistList = mapper.artist(artistGroupNo);
		RowBounds rowBounds = new RowBounds(0,6);
		List<Media> artistGroupMedia = mapper.artistGroupMedia(rowBounds, artistGroupNo);
		RowBounds rowBounds2 = new RowBounds(0,8);
		List<Product> artistGroupProduct = mapper.artistGroupProduct(rowBounds2,artistGroupNo);
		RowBounds rowBounds3 = new RowBounds(0,3);
		List<ArtistNotice> artistGroupNotice = mapper.artistGroupNotice(rowBounds3, artistGroupNo);
		map.put("artistGroup", artistGroup);
		map.put("artistList", artistList);
		map.put("artistGroupMedia", artistGroupMedia);
		map.put("artistGroupProduct", artistGroupProduct);
		map.put("artistGroupNotice", artistGroupNotice);
		map.put("result", result);
		
		return map;
	}
	
	
	@Override
	public int subscribe(int memberNo, String artistGroupTitle) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		
		Map<String, Object> checkMap = new HashMap<>();
		checkMap.put("artistGroupNo", artistGroupNo);
		checkMap.put("memberNo", memberNo);
		int result = mapper.checkSubscribe(checkMap);
		
		if(result > 0) return 0;
		else {
			Map<String, Object> map = new HashMap<>();
			map.put("memberNo", memberNo);
			map.put("artistGroupNo", artistGroupNo);
			return mapper.subscribe(map);
		}
	}
	
	@Override
	public Map<String, Object> artist(int memberNo) {
		int artistGroupNo = mapper.selectArtistGroupNo(memberNo);

		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("artistGroupNo", artistGroupNo);
		
		int result = mapper.update(map);
		int artistProfile = mapper.artistProfile(artistGroupNo);
		if(artistProfile != 0) {
			if(result == 1) {
				ArtistGroup1 artistGroup = mapper.artistUpdate(memberNo);
				List<Artist> artistList = mapper.artistList(artistGroupNo);
				map.put("artistGroup", artistGroup);
				map.put("artistList", artistList);
				return map;
			}
		}else {
			return map;
		}
		return map;
	}
	
//	@Override
//	public int update(String artistGroupTitle, int memberNo) {
//		return mapper.update(artistGroupTitle,memberNo);
//	}


	@Override
	public int artistUpdate(String artistGroupTitle, MultipartFile artistGroupMain, MultipartFile artistGroupLogo,
			String artistGroupIntroduce, List<MultipartFile> artistProfileImg, 
			List<String> name, List<String> email, ArtistGroup1 artistGroup, int adminNo) throws IllegalStateException, IOException {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		int test = adminMapper.test(adminNo);
		if(test != 1) return 0;
		else{
			artistGroup.setArtistGroupNo(artistGroupNo);
			artistGroup.setArtistGroupAdminNo(adminNo);
			artistGroup.setArtistGroupIntroduction(artistGroupIntroduce);
			artistGroup.setArtistGroupTitle(artistGroupTitle);
			
			String backupMainimg = artistGroup.getArtistGroupMainimg();
			String backupLogo = artistGroup.getArtistGroupLogoimg();
			
			String mainRename = null;
			String logoRename = null;
			if(artistGroupMain.getSize()>0 && artistGroupLogo.getSize()>0) {
				mainRename = Util.fileRename(artistGroupMain.getOriginalFilename());
				logoRename = Util.fileRename(artistGroupLogo.getOriginalFilename());
				
				artistGroup.setArtistGroupLogoimg(logoimgpath + logoRename);
				artistGroup.setArtistGroupMainimg(mainimgpath + mainRename);
			}
			List<Artist> artistList = new ArrayList<>();
			
			for(int i = 0; i<artistProfileImg.size(); i++) {
				if(artistProfileImg.get(i).getSize() > 0) {
					Artist artist1 = new Artist();
					artist1.setArtistName(name.get(i));
					artist1.setArtistEmail(email.get(i));
					artist1.setArtistGroupNo(artistGroupNo);
					
					String profileRename = Util.fileRename(artistProfileImg.get(i).getOriginalFilename());
					artist1.setArtistRename(profileRename);
					artist1.setArtistProfile(profileimgpath + profileRename);
					
					int memberNo = mapper.profileMemberNo(email.get(i));
					artist1.setMemberNo(memberNo);
					artistList.add(artist1);
					
					
				}
			}
			int result2 = mapper.ProfileUpdate(artistList);
			int result = mapper.artistProfileUpdate(artistGroup);
			
			
				if(result > 0) {
					if(result2 > 0) {
						for(int i = 0; i<artistProfileImg.size(); i++) {
							if(artistProfileImg.get(i).getSize()>0) {
								artistProfileImg.get(i).transferTo(new File(profileimgfolder + artistList.get(i).getArtistRename()));
							}
						}
					}
					if(artistGroupMain.getSize()>0 && artistGroupLogo.getSize()>0) {
						
						artistGroupMain.transferTo(new File(mainimgfolder + mainRename));
						artistGroupLogo.transferTo(new File(logoimgfolder + logoRename));
					}
				}else {				
					artistGroup.setArtistGroupMainimg(backupMainimg);
					artistGroup.setArtistGroupLogoimg(backupLogo);
				}
			return result;
		}
		
	}
	
	
	@Override
	public Map<String, Object> artistNotice(String artistGroupTitle) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		List<ArtistNotice> artistNoticeList = mapper.artistNotice(artistGroupNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("artistNoticeList", artistNoticeList);
		
		return map;
	}
	
	@Override
	public List<ArtistGroupNotice> artistNoticeDetail(String artistGroupTitle, int artistGroupNoticeNo) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		Map<String, Object> map = new HashMap<>();
		map.put("artistGroupNo", artistGroupNo);
		map.put("artistGroupNoticeNo", artistGroupNoticeNo);
		List<ArtistGroupNotice> artistGroupNoticeList = mapper.artistNoticeDetail(map);
		
		return artistGroupNoticeList;
	}
	
	
}