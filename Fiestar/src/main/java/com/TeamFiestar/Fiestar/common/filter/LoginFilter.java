package com.TeamFiestar.Fiestar.common.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter{
	
	
	private static final String EXCLUDED_URI = "/shop";
	private static final String EXCLUDED_URI2 = "/css";
	private static final String EXCLUDED_URI3 = "/js";
	private static final String EXCLUDED_URI4 = "/";
	private static final String EXCLUDED_URI5 = "/artistMember";
	private static final String EXCLUDED_URI6 = "/images";
	private static final String EXCLUDED_URI7 = "/member";
	private static final String EXCLUDED_URI8 = "/artistHomepageImg";
	private static final String EXCLUDED_URI9 = "/img";
	private static final String EXCLUDED_URI10 = "/loginError";
	private static final String EXCLUDED_URI11 = "/email";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
		
		
			log.info("로그인 필터 동작");
			
			// 요청, 응답 객체를 HTTP 통신에 사용할 수 있는 형태로 다운 캐스팅 진행
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse resp = (HttpServletResponse)response;

			String uri = req.getRequestURI();
			
			log.info(uri);
			
			
			// Session 객체 얻어오기(요청에서 get)
			HttpSession session = req.getSession();
			
			// 로그인이 되어있지 않은 경우
//			if(session.getAttribute("loginMember") == null) {
//				// /loginError 리다이렉트
//				resp.sendRedirect("/loginError");
//			}else {// 로그인 되어있는 경우
//				// 다음 필터 또는 dispatcherServlet으로 연결
//				chain.doFilter(request, response);
//			}
			
			boolean isLoggedIn = checkLoginStatus(req);
			
			// 요청한 URI 가져오기
	        String requestURI = req.getRequestURI();
	        
	        // 로그인이 안 되어 있고, 제외하고자 하는 URI와 일치하지 않으면 필터 적용
	        if (!isLoggedIn && (!requestURI.startsWith(EXCLUDED_URI) && 
	        		!requestURI.startsWith(EXCLUDED_URI2) && 
	        		!requestURI.startsWith(EXCLUDED_URI3) && 
	        		!requestURI.equals(EXCLUDED_URI4) &&
	        		!requestURI.startsWith(EXCLUDED_URI5) && 
	        		!requestURI.startsWith(EXCLUDED_URI6) &&
	        		!requestURI.startsWith(EXCLUDED_URI7) &&
	        		!requestURI.startsWith(EXCLUDED_URI8) &&
	        		!requestURI.startsWith(EXCLUDED_URI9) &&
	        		!requestURI.startsWith(EXCLUDED_URI10) &&
	        		!requestURI.startsWith(EXCLUDED_URI11)
	        				
	        				) ) {
	            // 로그인이 필요한 페이지로 리다이렉트 또는 처리하고자 하는 로직을 추가

	            // 여기에서 리다이렉트 등을 처리할 수 있습니다.
	            // response.sendRedirect("/login"); // 예제: 로그인 페이지로 리다이렉트

	            // 필터 체인 중지
				resp.sendRedirect("/loginError");
			
	            return;
	        }
	        chain.doFilter(request, response);
		}
	
	private boolean checkLoginStatus(HttpServletRequest request) {
		// 세션을 통해 로그인 상태 확인
        HttpSession session = request.getSession(false); // 세션이 없으면 null을 반환
        return session != null && session.getAttribute("loginMember") != null;
        
    }
}

