package com.TeamFiestar.Fiestar.mypage.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	private final BCryptPasswordEncoder bcrypt;

	@Value("${my.member.webpath}")
	private String webpath;

	@Value("${my.member.location}")
	private String folderPath;

	// 회원 탈퇴
	@Override
	public int withdrawal(String memberPw, int memberNo) {

		String encPw = mapper.selectMemberPw(memberNo);

		if (!bcrypt.matches(memberPw, encPw)) {
			return 0;
		}

		return mapper.withdrawal(memberNo);
	}

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

		if (result > 0) {
			if (memberProfile.getSize() > 0) {
				memberProfile.transferTo(new File(folderPath + rename));
			} else {
				loginMember.setMemberProfile(backup);
			}
		}
		return result;
	}

	// 프로필 배경 이미지 바꾸기
	@Override
	public int changeBackImg(Member loginMember, MultipartFile memberBackImage) throws IllegalStateException, IOException {
		
		String backup = loginMember.getMemberBackImage();	
		String rename = null;
		
		if (memberBackImage.getSize() > 0) {
			rename = Util.fileRename(memberBackImage.getOriginalFilename());
			loginMember.setMemberBackImage(webpath + rename);
		} else {
			loginMember.setMemberBackImage(null);
		}

		int result = mapper.changeBackImg(loginMember);

		if (result > 0) {
			if (memberBackImage.getSize() > 0) {
				memberBackImage.transferTo(new File(folderPath + rename));
			} else {
				loginMember.setMemberProfile(backup);
			}
		}
		
		return 0;
	}
	
	// 프로필 정보 바꾸기
	@Override
	public int info(Member updateMember, String[] memberAddress) {
	
		if(updateMember.getMemberAddress().equals(",,")) {
			updateMember.setMemberAddress(null);
		}else {
			String address = String.join("^^^", memberAddress);
			updateMember.setMemberAddress(address);
		}
		
		return mapper.info(updateMember);
	}

}
