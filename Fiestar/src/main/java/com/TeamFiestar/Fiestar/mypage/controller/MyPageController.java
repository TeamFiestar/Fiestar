package com.TeamFiestar.Fiestar.mypage.controller;


import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	
	@GetMapping("myPage/myPage-Modify")
	public String mypageModify() {
		return "myPage/myPage-Modify";
	}
	
	@GetMapping("myPage/myPage-Withdrawal")
	public String mypageWithdrawal() {
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
	@PostMapping("myPage/profile")
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
	
	
	
	// 내가 작성한 게시글 조회
	@GetMapping("myPage/myPage")
	public String myFeed(
			@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> map = service.selectMyFeedList(loginMember, cp);
		
		model.addAttribute("map", map);
		
		return "myPage/myPage";
	}
	
	// 내가 작성한 게시글 삭제
//	@PostMapping("myPage/deleteBoard")
//	public String deleteBoard(@SessionAttribute("loginMember") Member loginMember,
//			RedirectAttributes ra, String boardNo) {
//		
//		int result = service.deleteBoard(loginMember.getMemberNo(), boardNo);
//		
//		String message = null;
//		
//		if(result > 0) {
//			message = "게시글이 성공적으로 삭제되었습니다.";
//		} else {
//			message = "게시글 삭제를 실패하였습니다.";
//		}
//		
//		ra.addFlashAttribute("message", message);
//		
//		return "redirect:myPage/myPage";
//	}
	

	// 내가 작성한 댓글 조회
	@GetMapping("myPage/myPage-comment")
	public String myPageComment(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> map = service.MyCommentList(loginMember, cp);
		
		model.addAttribute("map", map);
		
		return "myPage/myPage-comment";
	}
	

	
	// 내가 구독한 아티스트 조회
	@GetMapping("myPage/myPage-artist")
	public String myArtist(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> map = service.myArtistList(loginMember, cp);
		
		model.addAttribute("map", map);
		
		return "myPage/myPage-artist";
		
	}
	
	// 구매목록 조회
	@GetMapping("myPage/myPage-Purchase")
	public String mypagePurchase(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> map = service.myPurchaseList(loginMember, cp);
		
		model.addAttribute("map", map);
		
		return "myPage/myPage-Purchase";
	}


}
