package com.TeamFiestar.Fiestar.mypage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.admin.controller.AdminAjaxController;
import com.TeamFiestar.Fiestar.admin.model.dto.SiteNotice;
import com.TeamFiestar.Fiestar.admin.model.service.AdminAjaxService;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.mypage.service.MyPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes({ "loginMember", "artistGroupTitle", "artistGroupNo"})
@RequestMapping("myPage")
@RequiredArgsConstructor
public class MyPageController {

	private final MyPageService service;

	@GetMapping("myPage-Modify")
	public String mypageModify() {
		return "myPage/myPage-Modify";
	}

	@GetMapping("myPage-Withdrawal")
	public String mypageWithdrawal() {
		return "myPage/myPage-Withdrawal";
	}

	// 내가 작성한 게시글 조회
	@GetMapping("myPage")
	public String myFeed(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		Map<String, Object> map = service.selectMyFeedList(loginMember, cp);

		model.addAttribute("map", map);

		return "myPage/myPage";
	}

	// 내가 작성한 게시글 삭제
	@PostMapping("deleteBoard")
	public String deleteBoard(@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra, @RequestParam(value = "boardNo") int boardNo) {

		int result = service.deleteBoard(loginMember.getMemberNo(), boardNo);

		String message = null;
		if (result > 0) {
			message = "게시글이 성공적으로 삭제 되었습니다.";
		} else {
			message = "게시글 삭제를 실패하였습니다.";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:myPage";
	}

	// 내가 작성한 댓글 조회
	@GetMapping("myPage-comment")
	public String myPageComment(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		Map<String, Object> map = service.MyCommentList(loginMember, cp);

		model.addAttribute("map", map);

		return "myPage/myPage-comment";
	}
	
	
	// 내가 작성한 댓글 삭제
	@PostMapping("delComment")
	public String delComment(@SessionAttribute("loginMember") Member loginMember,
			@RequestParam("commentType") String commentType,
			RedirectAttributes ra, @RequestParam("commentNo") int commentNo) {
		
		int result = service.delComment(loginMember.getMemberNo(), commentNo, commentType);
		
		String message = null;
		if(result > 0) {
			message = "댓글을 삭제하였습니다.";
		} else {
			message = "댓글 삭제를 실패하였습니다.";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:myPage-comment";
	}

	// 내가 구독한 아티스트 조회
	@GetMapping("myPage-artist")
	public String myArtist(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		Map<String, Object> map = service.myArtistList(loginMember, cp);

		model.addAttribute("map", map);

		return "myPage/myPage-artist";

	}


	// 회원 탈퇴
	@PostMapping("withdrawal")
	public String withDrawal(SessionStatus status, @SessionAttribute("loginMember") Member loginMember,
			RedirectAttributes ra, @RequestParam(value = "memberPw") String memberPw) {

		int result = service.withDrawal(memberPw, loginMember.getMemberNo());

		String message = null;
		String path = null;

		if (result > 0) { // 탈퇴 O
			message = "탈퇴 되었습니다";
			path = "redirect:/";

			status.setComplete();

		} else {
			message = "비밀번호가 일치하지 않습니다";
			path = "redirect:myPage-Withdrawal";
		}

		ra.addFlashAttribute("message", message);

		return path;
	}

	// 프로필 이미지 수정
	@PostMapping("profile")
	public String profile(@RequestParam("memberProfile") MultipartFile memberProfile,
			@SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra)
			throws IllegalStateException, IOException {

		int result = service.profile(memberProfile, loginMember);

		String message = null;

		if (result > 0) {
			message = "프로필 이미지가 변경되었습니다.";
		} else {
			message = "프로필 이미지 변경을 실패했습니다.";
		}

		ra.addFlashAttribute("message", message);	

		return "redirect:myPage-Modify";
	}
	
	// 프로필 정보 수정
	@PostMapping("info")
	public String info( @SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra,
			@RequestParam("backImage") MultipartFile memberBackImage,
			@RequestParam("memberNickname") String memberNickname,
			@RequestParam("memberPw") String memberPw,
			Member updateMember,
			@RequestParam("memberAddress") String[] MemberAddress) throws IllegalStateException, IOException{
		
		updateMember.setMemberNo(loginMember.getMemberNo());
		
		int result = service.info(loginMember, memberBackImage, updateMember, MemberAddress);
		
		String message = null;
		if(result > 0) {
			message = "회원 정보 수정되었습니다";
			loginMember.setMemberNickname(updateMember.getMemberNickname());
			loginMember.setMemberAddress(updateMember.getMemberAddress());
		}
		else {
			message = "회원 정보 수정 실패했습니다.";
			}
		
		ra.addFlashAttribute("message", message);
		
		
		return "redirect:myPage-Modify";
	}
	
	// 공지사항 조회
	@GetMapping("myPage-Noctice")
	public String siteNotice(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp, Model model) {
		
		Map<String, Object> map = service.siteNotice(cp);

		model.addAttribute("map", map);
		
		return "myPage/myPage-Noctice";
	}
	
	@GetMapping("ajaxNotice")
	@ResponseBody
	public SiteNotice selectNotice(@RequestParam("siteNoticeNo") int siteNoticeNo) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("siteNoticeNo", siteNoticeNo);
		
		return service.selectNotice(map);
	}
	
	// 구매 내역 조회
	@GetMapping
	
	
	
	
	
}
