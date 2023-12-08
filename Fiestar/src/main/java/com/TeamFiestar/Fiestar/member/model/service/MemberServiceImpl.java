package com.TeamFiestar.Fiestar.member.model.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberMapper mapper;
	private final BCryptPasswordEncoder bcrypt;
	
	@Override
	public Member login(Member inputMember) {
		Member loginMember = mapper.login(inputMember);
		if (loginMember == null) {
			return null;
		}
		
		log.debug("complate");
		
		if(!bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw() ) ) {
			return null;
		}
		
		loginMember.setMemberPw(null);
		return loginMember;
	}
	
	
	@Override
	public int signup(Member inputMember) {
		
		inputMember.setMemberPw(bcrypt.encode(inputMember.getMemberPw() ) );
		
		return mapper.signup(inputMember);
	}
	
	@Override
	public Member quickLogin(String memberEmail) {
		return mapper.login(memberEmail);
	}
	
}
