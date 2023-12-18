package com.TeamFiestar.Fiestar.email.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamFiestar.Fiestar.email.model.service.EmailService;

import lombok.RequiredArgsConstructor;


@RestController // @Controller + @ResponseBody
@RequestMapping("email")
@RequiredArgsConstructor // 초기화 되지 않은 final 필드에 bean DI 주입
public class EmailController {
	
	private final EmailService service;
	
	/** 회원 가입 인증번호 생성 + 이메일 발송 + insert 또는 update
	 * @return
	 */
	@PostMapping("signup")
	public int signup(@RequestBody String email) {
		return service.sendEmail("signup" ,email);
	}
	
	@PostMapping("checkAuthKey")
	public int checkAuthKey(@RequestBody Map<String, Object> paramMap) {
		
		
		return service.checkAuthKey(paramMap);
	}
	
	

}
