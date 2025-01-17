package com.itwill.blog.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.service.MemberService;

/**
 * Servlet implementation class UserSignOutController
 */
@WebServlet(name = "userSignOutController", urlPatterns = { "/user/signout" })
public class UserSignOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 디버그 로그 출력을 위한 객체 생성
	private static final Logger log = LoggerFactory.getLogger(UserSignOutController.class);
	
	// 비지니스 서비스 객체 생성
	private final MemberService memberService = MemberService.INSTANCE;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignOutController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
    	// 로그아웃 로그 출력
    	log.debug("doGet()");
    	
    	// 로그아웃 세션해제
    	HttpSession session = request.getSession();
    	
    	// 로그인 세션에 지정한 attribute 호출 후 삭제. 
    	session.removeAttribute("signedInUser");
    	
    	// 로그인 세션 초기화. 
    	session.invalidate();
    	
    	// 로그아웃 후 이동 할 패스
    	String url = request.getContextPath() + "/";
    	response.sendRedirect(url);
    	
	}


}
