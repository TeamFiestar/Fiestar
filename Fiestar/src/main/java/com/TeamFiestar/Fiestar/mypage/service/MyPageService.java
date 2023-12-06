<<<<<<< HEAD
//package com.TeamFiestar.Fiestar.mypage.service;
//
//import java.io.IOException;
//import java.util.Map;
//
//import org.springframework.web.multipart.MultipartFile;
//
//import com.TeamFiestar.Fiestar.member.model.DTO.Member;
//
//public interface MyPageService {
//
//	// 프로필 이미지 바꾸기
//	int profile(Member loginMember, MultipartFile memberProfile) throws IllegalStateException, IOException;
//
//
//
//}
=======
package com.TeamFiestar.Fiestar.mypage.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.member.model.dto.Member;


public interface MyPageService {

	// 프로필 이미지 바꾸기
	int profile(Member loginMember, MultipartFile memberProfile) throws IllegalStateException, IOException;



}
>>>>>>> origin/main
