package com.TeamFiestar.Fiestar.email.model.service;

import java.util.Map;

public interface EmailService {

	/** 이메일 발송
	 * @param htmlName
	 * @param email
	 * @return
	 */
	int sendEmail(String htmlName, String email);

	int checkAuthKey(Map<String, Object> paramMap);


}
