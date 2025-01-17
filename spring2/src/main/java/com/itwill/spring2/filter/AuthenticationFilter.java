package com.itwill.spring2.filter;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@Slf4j 	// 로그 출력을 위한 lombok
public class AuthenticationFilter extends HttpFilter {
       
	// 시리얼라이저블 
	private static final long serialVersionUID = 1L; 
 
	/**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticationFilter() { 
    }

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
		// 필터객체가 소멸될 때(WAS가 종료 될 때, close가 해제될 때 리소스) 해야할 일 TODO:
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		// 필터를 지나서 서블릿으로 진행하기 전에 해야할 일.
		
		// 로그인한 사용자(권한이 있는 사용자)는 필터 체인을 진행.  (세션에 정보가 있는 사용자)
		// 로그인하지 않은 사용자(권한이 없는 사용자)는 로그인 페이지로 이동(redirect). (세션에 정보가 없는 사용자)
    	
    	HttpServletRequest httpReq = (HttpServletRequest) request; // 세션을 가져오는 메서드를 호출하기 위한 캐스팅
    	HttpServletResponse httpResp = (HttpServletResponse) response; // 필요한 메서드를 가져오기 위한 캐스팅. 
    	
    	// 세션을 가져옴.
    	HttpSession session = httpReq.getSession();
    	// 세션에서 로그인 사용자 속성(signedInUser attribute) 값을 읽음.
    	Object signedInUser = session.getAttribute("signedInUser"); // controller에 저장한 이름을 가져옴.
    	
    	if(signedInUser != null && !signedInUser.equals("")) { // 세션에 로그인 정보가 있는 경우.
    		log.debug("로그인 상태: username={}",signedInUser); 
    		
    		// 필터 체인의 다음 단계(다음 필터 또는 서블릿)으로 request를 전달.
    		chain.doFilter(request, response);   
    		return; // 리턴이 안적어주면 else로 갑니다.
    	} 
    	
    	log.debug("로그아웃 상태 ----> 로그인 페이지로 이동");
    	// 로그인 이후에 최초 요청 주소로 이동(redirect)하기 위해서
    	String url = httpReq.getRequestURL().toString();
    	log.debug("[request url] = {}", url);
    	
    	String qs = httpReq.getQueryString();
    	log.debug("[Query String] = {}",qs);
    	
    	// 쿼리 스트링이 있냐 없냐에 따라 뒤쪽에 붙는 문자열이 달라진다.
    	
    	String target = null; // 로그인 성공 후 이동할 주소.
    	if(qs == null) { // 새글은 null 상세보기는 id가 붙어있음.
    		target = URLEncoder.encode(url, "UTF-8"); // java.net  (url주소, 인코딩)
    	} else {
    		target = URLEncoder.encode(url + "?" + qs, "UTF-8");  //쿼리 스트링이 있는 경우 붙혀서 
    	}
    	log.debug("[target] = {}",target);
    	//정확힌 redirect를 하기 위해서...
    	
    	String signInPage = httpReq.getContextPath() + "/user/signin?target=" + target;  //쿼리스트링 넣는 경로와 쿼리스트링.
    	httpResp.sendRedirect(signInPage);
		
		// 들어온 요청을 보내주는 역할이 doFilter (그 다음 필터 또는 서블릿으로) 
		// 만약에 진행 시키지 않고 리다이렉트 시킬 경우 doFilter를 호출하지 않는다.
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		// WAS가 필터를 생성한 이후에 필터에서 초기화 작업이 필요한 코드들을 작성.
    	
    	// XML에 환경설정 정보를 읽어야할 경우 init에서 읽는 것. 필요 없을 경우 빈 메서드로 남겨두면 된다.
	}

}
