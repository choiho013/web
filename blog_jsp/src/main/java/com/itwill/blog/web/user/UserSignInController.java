package com.itwill.blog.web.user;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.domain.Member;
import com.itwill.blog.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserSignInController
 */
@WebServlet(name = "userSignInController", urlPatterns = { "/user/signin" })
public class UserSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 디버그 로그 객체 생성
	private static final Logger log = LoggerFactory.getLogger(UserSignInController.class);
	
	// 비지니스 서비스 객체 생성(싱글톤 패턴)
	private final MemberService memberService = MemberService.INSTANCE;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignInController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	// 로그인 doGet 로그 출력
    	log.debug("doGet()");
    	request.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 로그인 페이지의 요청 파라미터를 가져옴
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String target = request.getParameter("target");
		
		Member member = memberService.signIn(username, password);
		
		// 로그인 상태 유지
		if(member != null) {
			//세션에 로그인 정보를 유지
			HttpSession session = request.getSession();
			// 로그인된 사용자 이름을 세션 속성에 추가
			session.setAttribute("signedInUser", member.getUsername());
			
            // 포인트 추가 처리
            int pointsToAdd = 10; // 추가할 포인트
            boolean success = memberService.addPoints(member.getId(), pointsToAdd);

            if (success) {
                int currentPoints = memberService.getCurrentPoints(member.getId()); // 현재 포인트 조회

                // 세션에 포인트 정보 저장
                session.setAttribute("alertMessage", "로그인 성공! " + pointsToAdd + " 포인트가 추가되었습니다.");
                session.setAttribute("currentPoints", currentPoints);
                session.setAttribute("pointAdded", pointsToAdd);
            } else {
                session.setAttribute("alertMessage", "로그인 성공! 그러나 포인트 추가에 실패했습니다.");
            }
			
			// target 페이지로 이동(redirect)
    		if (target != null && !target.equals("")) { // 로그인 정보가 있는 경우
    			response.sendRedirect(target); // 타겟 페이지로 이동.
    		} else { 
    			String url = request.getContextPath() + "/"; // 홈페이지
    			response.sendRedirect(url); // 네비게이션 메뉴에서 고려하지 않았기 때문에 이 정보를 넣게됨.
    		}
    		
    		
		// 로그인 실패했을 경우
		} else { 
			// 실패한 페이지의 리퀘스트 파라미터를 남김
			String url = request.getContextPath() + "/user/signin?result=f&target="
					+ URLEncoder.encode(target, "UTF-8");
					
			log.debug("로그인 실패: redirect to {}", url);
     		response.sendRedirect(url);
		}
	}

}
