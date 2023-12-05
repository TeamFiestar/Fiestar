package com.TeamFiestar.Fiestar.media.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Media {

	private int key;
	private int mediaNo;
	private String mediaTitle;
	private String mediaAddress;
	private String mediaDescription;
	private String mediaEnrollDate;
	private int mediaCount;
	private int artistGroupNo;
	private String mediaTime;
	
}
