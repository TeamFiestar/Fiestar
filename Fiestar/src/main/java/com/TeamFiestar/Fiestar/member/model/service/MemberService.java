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
	
	
	/** 이메일 중복 체크
	 * @param email
	 * @return
	 */
	int checkEamil(String email);

	/** 닉네임 중복 체크
	 * @param nickname
	 * @return
	 */
	int checkNickname(String nickname);
	
	
	int checkSubscribeGroupTitle(String artistGroupTitle, int memberNo);
	int checkSubscribeGroupNo(int artistGroupNo, int memberNo);

}
