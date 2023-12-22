package com.TeamFiestar.Fiestar.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.dto.SiteNotice;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
//import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface AdminMapper {

//	List<Member> selectMember();

	int countList(Member member);

	List<Member> selectMember(RowBounds rowBounds);

	int countMember(Map<String, Object> paramMap);

	List<Member> searchMember(RowBounds rowBounds, Map<String, Object> paramMap);

	int countDeleteMember(Member member);

	List<Member> deleteMember(RowBounds rowBounds);

	int countDeleteList(Map<String, Object> paramMap);

	List<Member> searchDelete(RowBounds rowBounds, Map<String, Object> paramMap);

	

//	int restoration(int memberNo);

	int countSubscribe(int artistGroupNo);
	List<Member> subscribeMember(RowBounds rowBounds, int artistGroupNo);

	
	int countSearchSubscribe(Map<String, Object> map1);
	List<Member> searchSubscribe(Map<String, Object> map1, RowBounds rowBounds);


//	List<Board> selectSubscribeBoard(Map<String, Object> map);

	String subArtistTitle(int artistGroupNo);

	int artistGroupRegi(ArtistGroup1 artistGroup);

	int test(int adminNo);

	ArtistGroup1 artistGroupUpdate(int memberNo);

	int GroupUpdate(ArtistGroup1 artistGroup);


	
	
	List<SiteNotice> selectNoticeList(SiteNotice notice, RowBounds rowBounds);

	int siteNoticeAdd(SiteNotice notice);

	List<Report> selectReportList(Map<String, Object> map, RowBounds rowBounds);

	int reportListCount(Map<String, Object> map);

	int noticeListCount(SiteNotice notice);

	List<Purchase> selectPurchaseList( RowBounds rowBounds);

	int orderListCount();

	int checkGroupUpdate(int memberNo);

	String regi(int memberNo);

	


	

}
