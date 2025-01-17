package com.itwill.blog.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.domain.Member;
import com.itwill.blog.service.MemberService;

/**
 * Servlet implementation class UserSignUpController
 */
@WebServlet(name = "userSignUpController", urlPatterns = { "/user/signup" })
public class UserSignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 디버그 객체 생성
	private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);
	
	// 비지니스 서비스 객체 생성 (싱글톤 패턴)
	private final MemberService memberService = MemberService.INSTANCE;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignUpController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	// 회원가입 페이지 로그
    	log.debug("doGet()");
    	
    	request.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
    	// 회원가입 페이지 요청 파라미터를 가져옴.
    	String username = request.getParameter("username");
    	String name = request.getParameter("name");
    	String password = request.getParameter("password");
    	String email = request.getParameter("email");
    	String phone = request.getParameter("phone");
    	String birthday = request.getParameter("birthday");
    	
    	log.debug("doPost(username={}, name={}, password={}, email={}, phone={}, birthday={})",
    			username, name, password, email, phone, birthday);
    	
    	// 회원 데이터의 객체를 생성(빌드), 
    	Member member = Member.bulider()
    			.username(username)
    			.name(name)
    			.password(password)
    			.email(email)
    			.birthday(birthday)
    			.phone(phone)
    			.build();
    	
    	
    	// 비지니스 서비스 테이블에 회원 정보 호출 삽입
    	memberService.signUp(member);
    	
    	String url = request.getContextPath() + "/user/signin";
    	response.sendRedirect(url);
	}

}