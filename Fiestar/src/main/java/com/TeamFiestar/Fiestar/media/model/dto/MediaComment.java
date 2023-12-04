package com.TeamFiestar.Fiestar.media.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MediaComment {
	
	private int mediaCommentNo;
	private String mediaCommentContent;
	private String mediaCommentEnrollDate;
	private String mediaCommentDelFl;
	private int mediaNo;
	private int memberNo;
	private int mediaParentCommentNo;

}
