package com.TeamFiestar.Fiestar.board.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board {
	
	private int boardNo;
	private String boardContent;
	private String boardEnrollDate;
	private String boardEnrollDate2;
	private String boardEnrollDateDd;
	private String boardEnrollDateMon;
	
	private String BoardDelFl;
	private int memberNo;

	private int artistGroupNo;
	private int artistGroupAdminNo;
	private String artistGroupTitle;
	private String artistName;
	private String artistProfile;
	private String artistGroupProfile;
	private String artistGroupImage;
	private String artistGroupBackimg;
	private String artistGroupMainimg;
	private String artistGroupLogoimg;
	private String artistEmail;
	
	private String memberEmail;
	private String artistRename;
	
	
	// 상세 조회 시 
	private int readCount;
	private int boardCommentNo;
	private String boardCommentContent;
	private int commentCount;
	private int likeCount;
	private String memberNickname;
	private String memberProfile;
	private String memberAuthority;
	private int likeCheck;
	
	private List<BoardImg> imageList;
	
	private List<Comment> commentList;
	
	
	
	
	

}
