package com.TeamFiestar.Fiestar.member.model.DTO;

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
	   private String profileImg;
	   private String enrollDate;
	   private String memberDelFl;
	   private int authority; 
	   
}
