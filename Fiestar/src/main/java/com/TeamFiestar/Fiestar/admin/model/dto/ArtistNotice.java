package com.TeamFiestar.Fiestar.admin.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArtistNotice {
	
	private int artistGroupNoticeNo;
	private String artistGroupNoticeTitle;
	private String artistGroupNoticeContent;
	private String artistGroupNoticeDelFl;
	private String artistGroupNoticeEnrollDate;
	private int artistGroupNo;
	
	private int key;
	private String noticeSearch;
	
	private String artistGroupTitle;

}
