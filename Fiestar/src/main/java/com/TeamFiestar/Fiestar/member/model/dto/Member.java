package com.TeamFiestar.Fiestar.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // 기본 생성자
@Getter
@Setter
@ToString
public class Member {	
	   private int memberNo;
	   private String memberEmail;
	   private String memberPw;
	   private String memberNickname;
	   private String memberAddress;
	   private String memberGender;
	   private int memberAuthority; 
	   private String memberProfile;
	   private String memberEnrollDate;
	   private String memberDelFl;
	   private String memberBackImage;
	   
}
