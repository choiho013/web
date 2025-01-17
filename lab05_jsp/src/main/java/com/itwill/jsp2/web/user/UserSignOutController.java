package com.itwill.jsp2.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class UserSignOutController
 */
@WebServlet(name = "userSignOutController", urlPatterns = { "/user/signout" })
public class UserSignOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignOutController.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignOutController() {}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	log.debug("doGet()");
    	
    	// 로그아웃: ************************************************************************
    	// (1) 세션에 저장된 로그인 관련 정보들(signedInUser)을 삭제.
    	// (2) 세션 객체를 무효화(invalidate)시킴 - 기존에 세션 객체를 지움.
    	// (2) 만 실행하면 (1)은 자동으로 실행됨.
    	
    	HttpSession session = request.getSession();
    	session.removeAttribute("signedInUser"); // (1)
    	// 아규먼트를 셋으로 지정한 이름을 리무브해야 지울 수 있다.
    	
    	session.invalidate(); //(2)
    	// was에 힙이 있는데 힙에 세션이라는 객체(주소 값)라고 생각하면 되는데 
    	// 힙에있는 객체를 지우고 새로 만드는게 invalidate . 
    	// 지워버리면 주소값이 바껴버리니까 새로운 쿠키를 주는 것
    	
    	// 로그아웃 이후에 홈페이지로 이동(redirect).
    	String url = request.getContextPath() + "/";
    	log.debug("로그아웃: redirect to {}", url);
    	response.sendRedirect(url);
	}

}
