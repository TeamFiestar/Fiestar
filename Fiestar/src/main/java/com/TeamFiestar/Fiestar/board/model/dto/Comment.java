package com.TeamFiestar.Fiestar.board.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Comment {
	private int boardCommentNo;
	private String boardCommentContent;
	private String boardCommentEnrollDate;
	private String boardCommentDelFl;
	private int boardNo;
	private int memberNo;
	private int boardParentCommentNo;
	
	private String memberNickname;
	private String memberProfile;
	private String memberAuthority;
	
	private int boardCommentLike;
	private int likeCountComment;
	private int likeClickComment;
	

}
