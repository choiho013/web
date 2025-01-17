package com.itwill.jsp2.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
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
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	/**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticationFilter() {}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}
		// 필터 객체를 소멸시킬 때(힙에서 삭제하기 전에) WAS가 호출하는 메서드.
		// 필터가 사용하던 리소스들을 해제.
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.debug("doFiter()");
		
		// 인증(Authentication)이 필요한 요청 주소들(새글, 상세보기, ...)에 대해서
		// 로그인 여부(세션에 signedInUser 속성이 있는 지)를 확인하고,
		// (1) 로그인되어 있으면, 컨트롤러로(서블릿)으로 요청을 전달(chain.doFilter()를 호출).
		// (2) 로그인되어 있지 않으면, 컨트롤러로 요청을 전달하지 않고 로그인 페이지로 이동(redirect).
		// -> 로그인을 담당하는 컨트롤러에서 로그인 성공 후에 최초 요청 페이지로 이동할 수 
		// 있도록 설정.
		
		// *************** 세션 메서드를 찾기위해 자식클래스로 캐스팅 ******************
		HttpServletRequest req = (HttpServletRequest) request; // casting
		HttpServletResponse resp = (HttpServletResponse) response;
		
		// URL(Uniform Resource Location)
		String reqUrl = req.getRequestURL().toString(); // 스트링 버퍼 타입이기 때문에 toString으로 변환
		log.debug("request URL = {}",reqUrl); // 프로토콜과 서버주소를 포함한 주소
		
		// URI(Uniform Resource Identifier)
		String reqUri = req.getRequestURI(); // 
		log.debug("request URI = {}",reqUri); // 프토토콜과 서버주소를 제외한 컨택스트 패스 포함 주소
		
		String contextPath = req.getContextPath(); // 컨택스트 패스만
		log.debug("context Path = {}",contextPath);
		
		String qs = req.getQueryString();
		log.debug("query string = {}", qs);
		
		// 로그인 이후에 이동할 페이지(타겟)
		String target = null;
		if(qs != null) { // 질의 문자열이 있는 경우
			target = URLEncoder.encode(reqUrl + "?" + qs, "UTF-8"); // java.net 클래스
			// Url에 구분하는 애들은 encode 해서 변환 해줘야한다
		} else { // 질의 문자열이 없는 경우
			target = URLEncoder.encode(reqUrl, "UTF-8");
			
		}
		log.debug("target = {}", target);
		// 세션에 signedInUser 속성이 있는 지 체크:
		HttpSession session = req.getSession();
		Object signedInUser = session.getAttribute("signedInUser");
		if(signedInUser != null) { // 로그인 상태
			log.debug("로그인 사용자: {}",signedInUser);
			
			// 필터 체인을 통해서(그 다음 필터 또는 서블릿으로) 요청을 전달.
			chain.doFilter(request, response);
			
			// doFilter를 호출하면 계속해서 진행한다. 호출하지 않으면 진행하지 않는다.
			
		} else { // 로그아웃 상태
			log.debug("로그아웃 상태 --> 로그인 페이지로 이동 --> 로그인 성공 후 target으로 이동");
			String url = req.getContextPath() + "/user/signin?target=" + target; 
			resp.sendRedirect(url);
			// 서블릿을 거치지않고 응답을 보내는게 redirect. 그래서 doFilter를 다시 요청할 경우 서버 에러.
			
			// 주소하고 쿼리스트링 구분하는게 물음표고 
			// 리퀘스트 파라미터 값 안에도 물음표가 있으니 컨택스트 패스와 용도가 달라서 encode해서 변환
			// 인코딩을 해줘야 url에 필요할 /, ? 아니면 리퀘스트 값을 뿐인지 구분하기 위해 반드시 
			// URLEncoder 를 써줘야함
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// WAS가 필터 객체를 생성한 직후에 호출하는 메서드.
		// 필터에 필요한 초기화 작업들.
	}

}
