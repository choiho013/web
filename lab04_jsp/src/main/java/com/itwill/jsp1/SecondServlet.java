package com.itwill.jsp1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Servlet implementation class SecondServlet
 */
// 1개의 서블릿이 2개이상의 요청주소를 처리하도록 만들 수 있다. 문자열의 배열에 추가하면 됨.
// 서블릿 매핑 설정 - web.xml에서 설정하지 않은 경우에는 애너테이션을 이용.
@WebServlet(name = "secondServlet", urlPatterns = { "/ex2" })
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondServlet() {
    	System.out.println("SecondServlet() 생성자 호출");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	System.out.println("SecondServlet::doGet() 호출");
    	
    	// WAS가 클라이언트로 보내는 컨텐트 타입을 설정(한글이 깨지지 않도록)_
    	// WAS가 클라이언트로 받는거면 requset, 클라이언트로 보내주는거면 response
    	response.setContentType("text/html; charset=UTF-8");
    	
    	LocalDateTime now = LocalDateTime.now();
    	
    	// 응답으로 보낼 HTML을 작성.
    	PrintWriter out = response.getWriter();
    	out.append("<!doctype html>")
    		.append("<html>")
    		.append("	<body>")
    		.append("		<h1>두번째 서블릿</h1>")
    		.append("		<h2>" + now + "</h2>")
    		.append("		<a href='/jsp1/'>목차</a>")
    		.append("   </body>")
    		.append("</html>");    	
    }

}
