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
 * Servlet Filter implementation class SecondFilter
 */
public class SecondFilter extends HttpFilter {
       
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public SecondFilter() {
    	System.out.println("===== SecondFilter() 생성자 호출");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("========== SecondFilter::destroy() 호출");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("==========> SecondFilter chain.doFilter() 호출 전.");
		
		// 필터 체인을 따라서 요청을 전달.  
		chain.doFilter(request, response);
		// 클라이언트에서 요청이 왔을거고 (doFilter) 그것을 그대로 chain 전달했으니 두번째 request
		// 두번째도 넘기는데 다른애(Filter)가 없으면 servlet으로 넘기는 구조.
		
		System.out.println("<========== SecondFilter chain.doFilter() 호출 후.");
	}
	
	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("========== SecondFilter::init() 호출");
	}

}
