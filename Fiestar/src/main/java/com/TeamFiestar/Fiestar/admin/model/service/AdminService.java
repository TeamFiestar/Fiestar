package com.TeamFiestar.Fiestar.admin.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.dto.SiteNotice;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
//import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

public interface AdminService {

	Map<String, Object> selectMember(Member member, int cp);

	

	Map<String, Object> searchMember(Map<String, Object> paramMap, int cp);

	Map<String, Object> deleteMember(Member member, int cp);

	Map<String, Object> searchDeleteMember(Map<String, Object> paramMap, int cp);

	

//	int restoration(int memberNo);

	Map<String, Object> subscribeMember(Member member, int cp, int artistGroupNo );

	Map<String, Object> searchSubscribe(Map<String, Object> paramMap, int cp, int memberNo);

//	List<Board> selectSubscribeBoard(int loginMemberNo);



	int artistGroupRegi(MultipartFile backImg, MultipartFile profile, MultipartFile image,
			String artistGroupTitle, int adminNo, ArtistGroup1 artistGroup) throws IllegalStateException, IOException;

	ArtistGroup1 artistGroupUpdate(int memberNo);

	int GroupUpdate(MultipartFile backImg, MultipartFile profile, MultipartFile image, String artistGroupTitle,
			int adminNo, ArtistGroup1 artistGroup) throws IllegalStateException, IOException;



	Map<String, Object> siteNoticeList(SiteNotice notice, int cp);

	int siteNoticeAdd(SiteNotice notice);

	Map<String, Object> selectReportList(Report report, int cp);

	Map<String, Object> selectPurchaseList(int cp);



	String regi(int memberNo);

}
