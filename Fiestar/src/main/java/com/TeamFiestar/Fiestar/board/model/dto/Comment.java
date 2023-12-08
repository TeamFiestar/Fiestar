package com.TeamFiestar.Fiestar.board.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Comment {
	private int commentNo;
	private String commentContent;
	private String commentEnrollDate;
	private String parentTitle;
	private String commentDelFl;
	private int boardNo;
	private int memberNo;
	private int parentNo;
	
	private String memberNickname;
	private String memberProfile;
	

}
