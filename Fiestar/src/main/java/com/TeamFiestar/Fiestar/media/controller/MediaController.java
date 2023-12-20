package com.TeamFiestar.Fiestar.media.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.media.model.service.MediaService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MediaController {
	
	private final MediaService service;
	
	
	@GetMapping("{artistGroupTitle}/media/insert")
	public String mediaInsert(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		

		
		return "media/mediaInsert";
	}
	
	
	@GetMapping("{artistGroupTitle}/media/{mediaNo:[0-9]+}/update")
	public String updateMediaDetail(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("mediaNo") int mediaNo, Model model) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		Media media = service.updateMediaDetail(mediaNo);
		model.addAttribute("media",media);
		
		return "media/mediaUpdate";
	}
	
	// 미디어 리스트 조회
	@GetMapping("{artistGroupTitle}/media/list")
	public String mediaList(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp,
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@RequestParam(name="key", required = false, defaultValue = "1") int key,
			@RequestParam(name="mediaTitle", required = false, defaultValue = "") String mediaTitle
			,Model model) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("key", key);
		map.put("mediaTitle", mediaTitle);
		map.put("artistGroupTitle", artistGroupTitle);

		map = service.selectMediaList(map, cp);
		
		model.addAttribute("mediaList", map.get("mediaList"));
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("mediaTitle", mediaTitle);
		model.addAttribute("key", key);
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		return "media/mediaList";
	}
	
	// 미디어 상세 조회
	@GetMapping("{artistGroupTitle}/media/{mediaNo:[0-9]+}/detail")
	public String mediaDetail(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("mediaNo") int mediaNo, Model model,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra,
			HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		
		Map<String, Object> map = new HashMap<>();
		if (loginMember != null) {
			int memberNo = loginMember.getMemberNo();
			map.put("memberNo", memberNo);
		}
		
		map.put("mediaNo", mediaNo);
		
		Media mediaDetail = service.mediaDetail(map);
		
		// ----------------------------------------------------

        // 쿠키를 이용한 조회 수 증가 처리

        // 1) 비회원 또는 로그인한 회원의 글이 아닌 경우
        if (loginMember == null || // 비회원
              loginMember.getMemberNo() != mediaNo ) {

           // 2) 쿠키 얻어오기
           Cookie c = null;

           // 요청에 담겨있는 모든 쿠키 얻어오기
           Cookie[] cookies = req.getCookies();

           if (cookies != null) { // 쿠키가 존재할 경우

              // 쿠키 중 "readmediaNo"라는 쿠키를 찾아서 c에 대입
              for (Cookie cookie : cookies) {
                 if (cookie.getName().equals("readMediaNo")) {
                    c = cookie;
                    break;
                 }
              }
           }

           // 3) 기존 쿠키가 없거나(c == null)
           // 존재는 하나 현재 게시글 번호가
           // 쿠키에 저장되지 않은 경우 (오늘 해당 게시글 본적 없음)
           int result = 0;

           if (c == null) {
              // 쿠키가 존재 X -> 하나 새로 생성
              c = new Cookie("readMediaNo", "|" + mediaNo + "|");

              // 조회수 증가 서비스 호출
              result = service.updateReadCount(mediaNo);

           } else {

              // 현재 게시글 번호가 쿠키에 있는지 확인

              // Cookie.getValue() : 쿠키에 저장된 모든 값을 읽어옴
              // -> String으로 반환

              // String.indexOf("문자열")
              // : 찾는 문자열이 String이 몇번 인덱스에 존재하는지 반환
              // 단, 없으면 -1 반환

              if (c.getValue().indexOf("|" + mediaNo + "|") == -1) {
                 // 쿠키에 현재 게시글 번호가 없다면

                 // 기존 값에 게시글 번호 추가해서 다시 세팅
                 c.setValue(c.getValue() + "|" + mediaNo + "|");

                 // 조회수 증가 서비스 호출
                 result = service.updateReadCount(mediaNo);
              }
           } // 3) 종료

           // 4) 조회 수 증가 성공 시
           // 쿠키가 적용되는 경로, 수명(당일 23시 59분 59초) 지정

           if (result > 0) {
        	   mediaDetail.setMediaCount(mediaDetail.getMediaCount() + 1);
              // 조회된 media 조회 수와 DB 조회 수 동기화

              // 적용 경로 설정
              c.setPath("/"); // "/" 이하 경로 요청 시 쿠키 서버로 전달

              // 수명 지정
              Calendar cal = Calendar.getInstance(); // 싱글톤 패턴
              cal.add(cal.DATE, 1); // 24시간 후의 시간을 기록

              // 날짜 표기법 변경 객체 (DB의 TO_CHAR()와 비슷)
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

              // java.util.Date
              Date a = new Date(); // 현재 시간

              Date temp = new Date(cal.getTimeInMillis()); // 다음날 (24시간 후)
              // 2023-10-31 2:00:10

              Date b = sdf.parse(sdf.format(temp)); // 다음날 0시 0분 0초

              // 다음날 0시 0분 0초 - 현재 시간
              long diff = (b.getTime() - a.getTime()) / 1000;
              // -> 다음날 0시 0분 0초까지 남은 시간을 초단위로 반환

              c.setMaxAge((int) diff); // 수명 설정

              resp.addCookie(c); // 응답 객체를 이용해서
                             // 클라이언트에게 전달
           }
           
           
        }

        // ----------------------------------------------------
		
		
		
		
		model.addAttribute("mediaDetail",mediaDetail);
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		return "media/mediaDetail";
	}
	
	// 라이브 이동 
	@GetMapping("{artistGroupTitle}/media/live")
	public String mediaLive(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		return "media/mediaLive";
	}
	
	
	
	
	
	
	
	
	// 미디어 삽입
	@PostMapping("{artistGroupTitle}/media/insert")
	public String mediaInsert(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			Media inserMedia) {
		
		int result = service.insertMedia(inserMedia);
		
		return "redirect:list";
	}
	
	// 미디어 삭제
	@PostMapping("{artistGroupTitle}/media/{mediaNo:[0-9]+}/delete")
	public String mediaDelete(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("mediaNo") int mediaNo, Model model) {
		
		int result = service.deleteMedia(mediaNo);
		
		return "redirect:../list";
	}
	
	// 미디어 업데이트
	@PostMapping("{artistGroupTitle}/media/{mediaNo:[0-9]+}/update")
	public String mediaUpdate(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("mediaNo") int mediaNo, Model model,
			Media updateMedia) {
		
		int result = service.updateMedia(updateMedia);
		
		return "redirect:detail";
	}
	
	
}
