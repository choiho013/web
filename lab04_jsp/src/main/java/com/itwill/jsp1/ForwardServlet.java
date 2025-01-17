package com.itwill.jsp1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet(name = "forwardServlet", urlPatterns = { "/ex3" })
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwardServlet() {
    	System.out.println("ForwordServlet() 생성자 호출");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	System.out.println("ForwordServlet::doGet() 호출");
    	
    	/*
    	 * 요청이 오면 WAS는 web.xml 또는 @WebServlet 애너테이션에서 설정된
    	 * URL 매핑에 따라서 요청을 처리할 수 있는 서블릿 객체의 메서드(doGet, doPost)를 호출.
    	 * 서블릿은 응답으로 보낼 HTML을 작성.
    	 * 서블릿에서 HTML을 작성하는 것은 너무 번거로움.
    	 * 서블릿은 JSP로 요청을 전달하고, JSP가 HTML을 작성하는 것이 더 쉬움.
    	 * WAS는 JSP가 작성한 HTML을 클라이언트에게 응답을 보냄.
    	 * 
    	 */
    	
    	/*
    	 * "forword" 방식의 웹 페이지 이동:
    	 * - 같은 WAS의 같은 웹 애플리케이션 안에서만 페이지를 이동하는 방식.
    	 * - 최초 요청 주소가 바뀌지 않음.
    	 * - request, response 객체가 유지됨. WAS가 준거를 그 페이지에 그대로 줬다는 의미
    	 * - 다른 WAS 또는 다른 웹 애플리케이션의 페이지로는 포워드할 수 없음.
    	 */
    	
    	
    	request.getRequestDispatcher("example.jsp") //아규먼트가 String
    			.forward(request, response);  //메서드를 두번 호출
    	
    	// 응답을 하기전이니 request
    	
    	// 서블릿이 받은 리케 리스판 을 jsp에 전달하면서 example보고 html을 만들어라고 전달하는게 forward
    	// html을 만들 수 있는 jsp에게 만들어 달라고 하는거
    	
    	
	}

}
