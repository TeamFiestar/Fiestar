package com.TeamFiestar.Fiestar.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // 기본 생성자
@Getter
@Setter
@ToString
public class ArtistSubscribe {

	private int artistGroupNo;
	private int memberNo;
	
	private String artistGroupTitle;
	private String artistGroupProfile;
	private String artistGroupImage;
}
