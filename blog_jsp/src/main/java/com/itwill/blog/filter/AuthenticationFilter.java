package com.itwill.blog.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter extends HttpFilter {
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticationFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		// 자식 클래스 선언
		HttpServletRequest req = (HttpServletRequest) request; // 다형성
		HttpServletResponse resp = (HttpServletResponse) response;
		
		// 컨택스트 패스 확인
		String reqUrl = req.getRequestURL().toString(); // 스트링타입 버퍼로 toString으로 변환
		log.debug("request URL = {}",reqUrl); // URL 패스 경로
		
		
		String reqUri = req.getRequestURI(); 
		log.debug("request URI = {}",reqUri); // URI 패스 경로
		
		String contextPath = req.getContextPath();
		log.debug("request ContextPath = {}",contextPath); // ContextPath 패스 경로
		
		String qs = req.getQueryString(); // QueryString 패스 경로
		log.debug("request QueryString = {}",qs);
		
		// 로그인 이후에 이동할 페이지(타겟)
		String target = null;
		if(qs != null) { //질의 문자열이 있는 경우
			target = URLEncoder.encode(reqUrl + "?" + qs, "UTF-8");
		} else {
			target = URLEncoder.encode(reqUrl, "UTF-8"); // 없을 경우 자기 경로
		}
		log.debug("target = {}",target);
		
		// 세션에 로그인(signedInUser) 속성이 있는지 체크:
		HttpSession session = req.getSession();
		Object signedInUser = session.getAttribute("signedInUser");
		if(signedInUser != null) { // 로그인 상태
			log.debug("로그인 사용자 = {} ", signedInUser);
			
			chain.doFilter(request, response);
		} else { // 로그아웃 상태.
			log.debug("사용자 로그아웃");
			
			// 로그아웃 후 이동할 페이지 URL 생성
			String url = req.getContextPath() + "/user/signin?target=" + target;
			resp.sendRedirect(url);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
