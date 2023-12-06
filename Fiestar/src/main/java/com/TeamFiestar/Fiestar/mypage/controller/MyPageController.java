package com.TeamFiestar.Fiestar.mypage.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.mypage.service.MyPageService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@SessionAttributes({ "loginMember" })
@RequiredArgsConstructor
public class MyPageController {

	private final MyPageService service;

	@GetMapping("myPage/myPage")
	public String myPage() {
		return "myPage/myPage";
	}

	@GetMapping("myPage/myPage-artist")
	public String myPageArtist() {
		return "myPage/myPage-artist";
	}

	@GetMapping("myPage/myPage-comment")
	public String myPageComment() {
		return "myPage/myPage-comment";
	}

	@GetMapping("myPage/myPage-Modify")
	public String myPageModify() {
		return "myPage/myPage-Modify";
	}

	@GetMapping("myPage/myPage-Purchase")
	public String myPagePurchase() {
		return "myPage/myPage-Purchase";
	}

	@GetMapping("myPage/myPage-Withdrawal")
	public String myPageWithdrawal() {
		return "myPage/myPage-Withdrawal";
	}

	// 회원 탈퇴
	@PostMapping("withdrawal")
	public String withdrawal(String memberPw, @SessionAttribute("loginMember") Member loginMember,
			RedirectAttributes ra, SessionStatus status) {

		int result = service.withdrawal(memberPw, loginMember.getMemberNo());

		String message = null;
		String path = null;

		if (result > 0) {
			message = "탈퇴 되었습니다.";
			path = "redirect:/";
		} else {
			message = "비밀번호가 일치하지 않습니다.";
			path = "redirect:myPage-Withdrawal";
		}

		return path;

	}

	// 프로필 변경
	@PostMapping("profile")
	public String profile(@SessionAttribute("loginMember") Member loginMember,
			@RequestParam("memberProfile") MultipartFile memberProfile, RedirectAttributes ra)
			throws IllegalStateException, IOException {

		int result = service.profile(loginMember, memberProfile);

		String message = null;

		if (result > 0) {
			message = "프로필 이미지가 변경 되었습니다.";
		} else {
			message = "프로필 이미지 변경을 실패하였습니다.";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:/myPage/myPage-Modify";

	}

	// 프로필 배경 이미지 변경
	@PostMapping("backImg")
	public String changeBackImg(@SessionAttribute("loginMember") Member loginMember,
			@RequestParam("memberBackImage") MultipartFile memberBackImage, RedirectAttributes ra) 
					throws IllegalStateException, IOException{

		int result = service.changeBackImg(loginMember, memberBackImage);

		String message = null;
		if (result > 0) {
			message = "프로필 배경이 변경 되었습니다.";
		} else {
			message = "프로필 배경 변경을 실패 했습니다.";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:/myPage/myPage-Modify";

	}
	
	@PostMapping("info")
	public String info(Member updateMember, String[] memberAddress,
			@SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra) {
		
		updateMember.setMemberNo(loginMember.getMemberNo());
		
		int result = service.info(updateMember, memberAddress);
		
		String message = null;
		
		if(result > 0) {
			message = "회원 정보가 수정 되었습니다.";
			loginMember.setMemberNickname(updateMember.getMemberNickname());
			loginMember.setMemberAddress(updateMember.getMemberAddress());
		} else {
			message = "회원 정보 수정을 실패했습니다. ";
		}
		
		ra.addFlashAttribute("message", message);

		return "redirect:/myPage/myPage-Modify";
	}

}
