<<<<<<< HEAD
//package com.TeamFiestar.Fiestar.mypage.service;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.TeamFiestar.Fiestar.common.utility.Util;
//import com.TeamFiestar.Fiestar.member.model.DTO.Member;
//import com.TeamFiestar.Fiestar.mypage.mapper.MyPageMapper;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class MyPageServiceImpl implements MyPageService {
//
//	private final MyPageMapper mapper;
//
//	@Value("${my.member.webpath}")
//	private String webpath;
//
//	@Value("${my.member.location}")
//	private String folderPath;
//
//	// 프로필 이미지 바꾸기
//	@Override
//	public int profile(Member loginMember, MultipartFile memberProfile) throws IllegalStateException, IOException {
//
//		String backup = loginMember.getMemberProfile();
//
//		String rename = null;
//
//		if (memberProfile.getSize() > 0) {
//
//			rename = Util.fileRename(memberProfile.getOriginalFilename());
//
//			loginMember.setMemberProfile(webpath + rename);
//		} else {
//			
//			loginMember.setMemberProfile(null);
//		}
//
//		int result = mapper.profile(loginMember);
//		
//		if(result > 0) {
//			if(memberProfile.getSize() >0) {
//				memberProfile.transferTo(new File(folderPath + rename));
//			} else {
//				loginMember.setMemberProfile(backup);
//			}
//		}
//
//		
//		return result;
//		
//		
//	}
//
//}
=======
package com.TeamFiestar.Fiestar.mypage.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.common.utility.Util;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.mypage.mapper.MyPageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper mapper;

	@Value("${my.member.webpath}")
	private String webpath;

	@Value("${my.member.location}")
	private String folderPath;

	// 프로필 이미지 바꾸기
	@Override
	public int profile(Member loginMember, MultipartFile memberProfile) throws IllegalStateException, IOException {

		String backup = loginMember.getMemberProfile();

		String rename = null;

		if (memberProfile.getSize() > 0) {

			rename = Util.fileRename(memberProfile.getOriginalFilename());

			loginMember.setMemberProfile(webpath + rename);
		} else {
			
			loginMember.setMemberProfile(null);
		}

		int result = mapper.profile(loginMember);
		
		if(result > 0) {
			if(memberProfile.getSize() >0) {
				memberProfile.transferTo(new File(folderPath + rename));
			} else {
				loginMember.setMemberProfile(backup);
			}
		}

		
		return result;
		
		
	}

}
>>>>>>> origin/main
