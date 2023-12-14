package com.TeamFiestar.Fiestar.admin.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.admin.model.dto.AdminPagination;
import com.TeamFiestar.Fiestar.admin.model.mapper.AdminMapper;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.common.utility.Util;
//import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final AdminMapper mapper;
	
	@Value("${artist.image.webpath}")
	private String imagepath;
	@Value("${artist.profile.webpath}")
	private String profilepath;
	@Value("${artist.backImg.webpath}")
	private String backpath;
	
	@Value("${artist.image.location}")
	private String imagefolder;
	@Value("${artist.profile.location}")
	private String profilefolder;
	@Value("${artist.backImg.location}")
	private String backfolder;
	
	
	
	@Override
	public Map<String, Object> selectMember(Member member, int cp) {
		int countList = mapper.countList(member);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> memberList = mapper.selectMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberList", memberList);
		map.put("pagination", pagination);
		return map;
	}
	
	
	@Override
	public Map<String, Object> searchMember(Map<String, Object> paramMap, int cp) {
		int countList = mapper.countMember(paramMap);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> memberList = mapper.searchMember(rowBounds, paramMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberList", memberList);
		map.put("pagination", pagination);
		return map;
	}
	
	@Override
	public Map<String, Object> deleteMember(Member member, int cp) {
		int countList = mapper.countDeleteMember(member);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> deleteList = mapper.deleteMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("deleteList", deleteList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	@Override
	public Map<String, Object> searchDeleteMember(Map<String, Object> paramMap, int cp) {
		int countList = mapper.countDeleteList(paramMap);
	
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> deleteList = mapper.searchDelete(rowBounds, paramMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("deleteList", deleteList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	
	
	
	@Override
	public Map<String, Object> subscribeMember(Member member, int cp, int memberNo) {
		
		int countList = mapper.countSubscribe(memberNo);
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> subscribeList = mapper.subscribeMember(rowBounds, memberNo);
		String artistGroupTitle = mapper.subArtistTitle(memberNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("subscribeList", subscribeList);
		map.put("pagination", pagination);
		map.put("artistGroupTitle", artistGroupTitle);
		
		return map;
	}
	
	
	@Override
	public Map<String, Object> searchSubscribe(Map<String, Object> paramMap, int cp, int memberNo) {
		paramMap.put("memberNo", memberNo);
		
		int countList = mapper.countSearchSubscribe(paramMap);
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> subscribeList = mapper.searchSubscribe(paramMap, rowBounds);
		Map<String, Object> map = new HashMap<>();
		map.put("subscribeList", subscribeList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	@Override
	public List<Board> selectSubscribeBoard(int loginMemberNo) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", loginMemberNo);
//		map.put("artistGroupNo", artistGroupNo);
		return mapper.selectSubscribeBoard(map);
	}
	
	
	@Override
	public int artistGroupRegi(MultipartFile backImg, MultipartFile profile, MultipartFile image,
			String artistGroupTitle, int adminNo, ArtistGroup1 artistGroup) throws IllegalStateException, IOException {
		
		int test = mapper.test(adminNo);
		if(test > 0) return 0;
		else {
		artistGroup.setArtistGroupAdminNo(adminNo);
		artistGroup.setArtistGroupTitle(artistGroupTitle);
		
		String backupProfile = artistGroup.getArtistGroupProfile();
		String backupBack = artistGroup.getArtistGroupBackimg();
		String backupImg = artistGroup.getArtistGroupImage();
		
		String backRename = null;
		String profileRename = null;
		String imageRename = null;
		if(backImg.getSize()>0 && profile.getSize()>0 && image.getSize()>0) {
			backRename = Util.fileRename(backImg.getOriginalFilename());
			profileRename = Util.fileRename(profile.getOriginalFilename());
			imageRename = Util.fileRename(image.getOriginalFilename());
			
			artistGroup.setArtistGroupProfile(profilepath + profileRename);
			artistGroup.setArtistGroupBackimg(backpath + backRename);
			artistGroup.setArtistGroupImage(imagepath + imageRename);
		}
		int result = mapper.artistGroupRegi(artistGroup);
		
		if(result>0) {
			if(backImg.getSize()>0 && profile.getSize()>0 && image.getSize()>0) {
				backImg.transferTo(new File(backfolder + backRename));
				profile.transferTo(new File(profilefolder + profileRename));
				image.transferTo(new File(imagefolder + imageRename));
			}
		}else {
			artistGroup.setArtistGroupProfile(backupProfile);
			artistGroup.setArtistGroupBackimg(backupBack);
			artistGroup.setArtistGroupImage(backupImg);
		}
		return result;
		}
	
	}
	
	
	@Override
	public ArtistGroup1 artistGroupUpdate(int memberNo) {
		return mapper.artistGroupUpdate(memberNo);
	}
	
	@Override
	public int GroupUpdate(MultipartFile backImg, MultipartFile profile, MultipartFile image, String artistGroupTitle,
			int adminNo, ArtistGroup1 artistGroup) throws IllegalStateException, IOException {
		int test = mapper.test(adminNo);
		if(test == 0) return 0;
		else {
			artistGroup.setArtistGroupAdminNo(adminNo);
			artistGroup.setArtistGroupTitle(artistGroupTitle);
			
			String backupProfile = artistGroup.getArtistGroupProfile();
			String backupBack = artistGroup.getArtistGroupBackimg();
			String backupImg = artistGroup.getArtistGroupImage();
			
			String backRename = null;
			String profileRename = null;
			String imageRename = null;
			if(backImg.getSize()>0 && profile.getSize()>0 && image.getSize()>0) {
				backRename = Util.fileRename(backImg.getOriginalFilename());
				profileRename = Util.fileRename(profile.getOriginalFilename());
				imageRename = Util.fileRename(image.getOriginalFilename());
				
				artistGroup.setArtistGroupProfile(profilepath + profileRename);
				artistGroup.setArtistGroupBackimg(backpath + backRename);
				artistGroup.setArtistGroupImage(imagepath + imageRename);
			}
			int result = mapper.GroupUpdate(artistGroup);
			
			if(result>0) {
				if(backImg.getSize()>0 && profile.getSize()>0 && image.getSize()>0) {
					backImg.transferTo(new File(backfolder + backRename));
					profile.transferTo(new File(profilefolder + profileRename));
					image.transferTo(new File(imagefolder + imageRename));
				}
			}else {
				artistGroup.setArtistGroupProfile(backupProfile);
				artistGroup.setArtistGroupBackimg(backupBack);
				artistGroup.setArtistGroupImage(backupImg);
			}
			return result;
		}
	}
	
//	@Override
//	public int restoration(int memberNo) {
//		return mapper.restoration(memberNo);
//	}
	
}
