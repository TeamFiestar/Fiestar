package com.TeamFiestar.Fiestar.admin.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Report {
	
	private int reportNo;
	private int reporterNo;
	private String reporterNickname;
	private String reporterEmail;
	private int reportTargetNo;
	private String reportProceed;
	private String reportContent;
	private int reportContentNo;
	private String memberEmail;
	private String reportType;
	private String memberNickname;
	
	private String reportTargetEmail;
	private String reportTargetNickname;
	private int artistGroupNo;
	
	private int key;
	private String reportSearch;

}
