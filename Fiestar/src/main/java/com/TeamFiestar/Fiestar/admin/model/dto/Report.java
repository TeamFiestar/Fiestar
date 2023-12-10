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
	private int reportTargetNo;
	private String reportProceed;
	private String reportUrl;
	private String reportContent;
	private String memberEmail;
	private String memberNickname;

}
