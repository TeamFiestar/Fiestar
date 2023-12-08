package com.TeamFiestar.Fiestar.member.model.service;

import com.TeamFiestar.Fiestar.member.model.dto.Member;

public interface MemberService {

	/** 로그인
	 * @param inputMember
	 * @return
	 */
	Member login(Member inputMember);

	/** 회원가입
	 * @param inputMember
	 * @return
	 */
	int signup(Member inputMember);

	Member quickLogin(String memberEmail);

}
