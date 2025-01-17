package com.itwill.jsp2.web.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Member;
import com.itwill.jsp2.service.MemberService;

/**
 * Servlet implementation class UserSignInController
 */
@WebServlet(name = "userSignInController", urlPatterns = { "/user/signin" })
public class UserSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignInController.class);
	private final MemberService memberService = MemberService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignInController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	log.debug("doGet()");
    	request.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	// 로그인정보는 세션에 보내야 로그인이 유지가 된다.
    	// 양식 데이터(username, password)를 읽음.
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String target = request.getParameter("target");
    	log.debug("doPost(username={}, password={}, terget={})",username,password,target);
    	
    	// 서비스 계층의 메서드를 호출해서 로그인 성공/실패 여부를 판단.
    	Member member = memberService.signIn(username, password);
    	if(member != null) {
    		// username과 password가 일치하는 사용자가 있는 경우 -> 로그인 성공
    		// 세션에 로그인 정보를 저장 -> 포스트 목록 페이지로 이동(redirect)
    		HttpSession session = request.getSession();
    		Object signedInUser = session.getAttribute("signedInUser");
    		
    		session.setAttribute("signedInUser", member.getUsername());
    		
    		// target 페이지로 이동(redirect)
    		if (target != null && !target.equals("")) { // 로그인 정보가 있는 경우
    			response.sendRedirect(target); // 타겟 페이지로 이동.
    			
    		} else { // 
    			
    			String url = request.getContextPath() + "/"; // 홈페이지
    			response.sendRedirect(url); // 네비게이션 메뉴에서 고려하지 않았기 때문에 이 정보를 넣게됨.
    		}
    		
    	} else {
    		// username과 password가 일치하는 사용자가 없는 경우 -> 로그인 실패
    		// 다시 로그인 페이지로 이동(redirect).
    		// 로그인 실패 했을경우 리퀘스트 파라미터가 없기에 붙혀서 작성해줘야 로그인 실패한 페이지의 
    		// 리퀘스트 파라미터를 남기게 하기 위해서. 
    		String url = request.getContextPath() + "/user/signin?result=f&target=" 
    				+ URLEncoder.encode(target, "UTF-8"); // 리퀘스트 파라미터를 붙혀서 
    		log.debug("로그인 실패: redirect to {}", url);
     		response.sendRedirect(url);
    	}
    	
	}

}
