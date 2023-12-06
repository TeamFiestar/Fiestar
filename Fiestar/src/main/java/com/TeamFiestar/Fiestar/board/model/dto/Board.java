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
	private String BoardDelFl;
	private int memberNo;

	private int artistGroupNo;
	private int artistGroupAdminNo;
	private String artistGroupTitle;
	
	// 상세 조회 시 
	private int boardCommentNo;
	private String boardCommentContent;
	private int commentCount;
	private int likeCount;
	private String memberNickname;
	private String memberProfile;
	
	private List<BoardImg> imageList;
	
	private List<Comment> commentList;
	
	
	
	
	

}
