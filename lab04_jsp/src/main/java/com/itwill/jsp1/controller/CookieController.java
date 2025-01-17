package com.itwill.jsp1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CookieController
 */
@WebServlet(name = "cookieController", urlPatterns = { "/cookie" })
public class CookieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	System.out.println("CookieController::doGet() 호출"); // 로그 찍히면 요청이 온 것.
    	
    	// 쿠기 객체 생성.
    	// 쿠키는 무조건 문자열 이름,값
    	Cookie cookie = new Cookie("hello", "안녕하세요");
    	
    	
    	// 쿠키 객체를 응답(response)에 포함시킴.
    	response.addCookie(cookie);
    	
    	// 쿠키 설정 - 쿠키 만료 시간, 도메인, 경로.
    	
    	int count = 1; //클라이언트가 서버를 방문한 횟수.
    	// 클라이언트가 보낸 쿠키들을 확인.
    	//동작방식을 이해하면 됨 응답으로 보낼때는 response고 서버로 포함해서 보내는거면 서버가 받으니 request
    	Cookie[] cookies = request.getCookies();
    	for(Cookie c : cookies) {
    		System.out.println(c.getName() + " = " + c.getValue());
    		
    		if(c.getName().equals("cnt")) { // 쿠키의 이름이 "cnt" 이면 
    		   count = Integer.parseInt(c.getValue()); // 쿠키의 값을 count에 저장.
    		}
    	}
    	
    	// count 변수(방문 횟수)를 뷰에게 전달하기 위해서, 요청 객체에 속성을 추가.
    	request.setAttribute("visitCount", count);
    	// 굉장히 중요함. 방식이 forward이면 정보를 set으로 담아서 전달하는 역할 
    	
    	count++; //방문횟수를 1증가.
    	// 변경된 방문횟수를 저장하는 쿠키를 생성.
    	Cookie visitCookie = new Cookie("cnt", String.valueOf(count)); 
    	// 쿠키를 응답에 포함.
    	response.addCookie(visitCookie);
    	
    	// 뷰로 요청을 전달.(forword)
    	request.getRequestDispatcher("/WEB-INF/views/cookie.jsp")
    			.forward(request, response);
    	// was가 doget을 호출할때 req respon를 그대로 requestdispatcher에 주고 그것을 그대로 forward로 전달
	}
}

// 브라우저는 쿠키를 서버에 요청(클릭, 새로고침)할때 방문횟수(count)몇이다 라고 요청을 하고 
// 서버는 방문횟수를 증가(count++)을 해서 응답을 해준다.
// 세션은 서버쪽에 저장하는 정보 쿠키
// 톰캣에서 주고 받는 쿠키.
// doget을 처음할때 아무것도 없는 상태에서 응답으로 count 1 을 줬으니 요청을 다시해도 응답은 1만온다 
// 요청받은 count를 변수에 담아서로 jsp에 보내주면 html 방문횟수를 표시해줌
