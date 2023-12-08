package com.TeamFiestar.Fiestar.media.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MediaComment {
	
	private int mediaCommentNo;
	private String mediaCommentContent;
	private String mediaCommentEnrollDate;
	private String mediaCommentDelFl;
	private String memberNickname;
	private int mediaNo;
	private int memberNo;
	private int mediaParentCommentNo;
	
	private int mediaCommentLike;
	private int memberCommentLike;

}
