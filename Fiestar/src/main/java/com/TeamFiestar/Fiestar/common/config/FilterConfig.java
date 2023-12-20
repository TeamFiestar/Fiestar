package com.TeamFiestar.Fiestar.common.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.TeamFiestar.Fiestar.common.filter.AdminFilter;
import com.TeamFiestar.Fiestar.common.filter.LoginFilter;



public class FilterConfig {

	@Bean // 해당 메서드 bean등록
	public FilterRegistrationBean<LoginFilter> loginFilter(){
		// FilterRegistrationBean : 필터 등록 bean
		
		FilterRegistrationBean<LoginFilter> filter = new FilterRegistrationBean<>();
		
		filter.setFilter(new LoginFilter()); // 사용할 필터 객체 추가
//		String[] filteringUrl = {"/myPage/*", "/**/feed/*", "/*/artist/*", "/**/media/*", "/*/live/*", "/chatting/*", "/*/artist/*", "artistMember/*", "/shop/*"};
		String[] filteringUrl = {"/mypPage/*"};
		// Arrays.asList(배열) : 배열 -> List로 변환
		filter.setUrlPatterns(Arrays.asList(filteringUrl)); // 필터링할 주소 지정
		filter.setName("loginFilter"); // 필터 이름 지정
		filter.setOrder(1); // 필터 순서
		
		return filter;
	}
	
	@Bean
	public FilterRegistrationBean<AdminFilter> adminFilter(){
		
		FilterRegistrationBean<AdminFilter> filter = new FilterRegistrationBean<>();
		
		filter.setFilter(new AdminFilter());
		List<String> filteringUrl = new ArrayList<>(); // 리스트 이용해서 필터 적용 Url 추가
		filteringUrl.add("/admin/*");
		filter.setUrlPatterns(filteringUrl);
		
		filter.setName("adminFilter");
		filter.setOrder(2);
		return filter;
	}
	

}