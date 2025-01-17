package com.itwill.jsp1.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

/**
 * Servlet Filter implementation class FirstFilter
 */
// 필터 요청 주소 매핑 설정:
//(1) web.xml (deployment descriptor) 파일에서 <filter>, <filter-mapping> 태그로 설정
//(2) @WebFilter 애너테이션으로 설정.
//(주의) 한 개의 필터 클래스는 web.xml과 애너테이션을 중복으로 설정하면 안됨.
public class FirstFilter extends HttpFilter {
       
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public FirstFilter() {
    	System.out.println("FirstFilter() 생성자 호출");
    }

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
		// WAS가 종료될 때 생성된 필터 객체를 소멸시키기 위해서 호출하는 메서드.
    	// 필터가 사용하고 있었던 리소스들을 해제.
    	System.out.println("======= FirstFilter::destory() 호출");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("=====> FirstFilter chain.doFilter() 호출 전...");
		
		// 요청을 필터 체인으로 전달. --> 요청 주소에 매핑된 다른 필터 또는 서블릿 메서드를 호출.
		chain.doFilter(request, response);
		
		System.out.println("<===== FirstFilter chain.doFilter() 호출 후...");
		// 그냥 log 넣은것.
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// 필터가 생성된 후 필터의 초기화 작업을 수행하기 위해서 호출되는 메서드.
		System.out.println("===== FirstFilter::init() 호출");
	}

}
