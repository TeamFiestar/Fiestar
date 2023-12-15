package com.TeamFiestar.Fiestar.admin.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SiteNotice {
	
	private int siteNoticeNo;
	private String siteNoticeTitle;
	private String siteNoticeContent;
	private String siteNoticeDelFl;
	private String siteNoticeEnrollDate;
	
	private int key;
	private String noticeSearch;

}
