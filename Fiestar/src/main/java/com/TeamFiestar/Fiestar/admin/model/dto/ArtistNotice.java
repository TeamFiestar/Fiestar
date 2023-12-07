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
	private String artistGroupTitle;
	private String artistGroupContent;
	private String artistGroupDelFl;
	private String artistGroupNoticeEnrollDate;
	private int artistGroupNo;

}
