package com.itwill.jsp1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet(name = "redirectServlet", urlPatterns = { "/ex4" })
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
    	System.out.println("RedirectServlet() 생성자 호출");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("RedirectServlet::doGet() 호출");
		
		/*
		 * "redirect" 방식의 페이지 이동:
		 * 클라이언트가 최초 요청(request) 
		 *  --> WAS가 redirect(요청을 다시 보내세요) 응답(response)
		 *  --> 클라이언트가 재요청
		 *  --> 재요청을 받은 WAS가 응답.
		 *  브라우저의 주소창의 URL이 최초 요청 주소가 아니라 최종 요청 주소로 바뀜.
		 *  최초 요청의 request, response 객체가 이동하는 페이지로 전달되지 않음.
		 *  같은 WAS 뿐만 아니라, 다른 웹 서버, 다른 웹 애플리케이션으로 redirect 가능.
		 *  response.sendRedirect("https://www.naver.com"); 가능  
		 */
		
		
		response.sendRedirect("ex3"); // 작성 후 서버를 수동으로 재시작
		
		// 클라이언트에게 다시 요청을 보내세요라고 하는 응답을 보내기 때문에 response.
	}
}

// wax -> RedirectServlet -> doGet -> response.send -> ex3
