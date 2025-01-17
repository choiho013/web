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

import com.itwill.blog.domain.Member;
import com.itwill.blog.service.MemberService;

/**
 * Servlet implementation class UserPointController
 */
@WebServlet(name = "userPointController", urlPatterns = { "/user/point" })
public class UserPointController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 디버그 로그 출력
	private static final Logger log = LoggerFactory.getLogger(UserPointController.class);
	
	// 유저 비지니스 서비스 객체 생성(싱글톤)
	private static MemberService memberService = MemberService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPointController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);

        // 세션에서 로그인된 사용자 정보 가져오기
        Member member = (Member) session.getAttribute("signedInUser");

        if (member != null) {
            // 포인트 추가 처리
            int pointsToAdd = 10; // 지급할 포인트
            boolean success = memberService.addPoints(member.getId(), pointsToAdd);

            if (success) {
                // 업데이트된 포인트 가져오기
                int currentPoints = memberService.getCurrentPoints(member.getId());
                
                // 데이터 JSP로 전달
                request.setAttribute("alertMessage", "로그인 성공! 10 포인트가 추가되었습니다.");
                request.setAttribute("currentPoints", currentPoints);
                request.setAttribute("pointAdded", pointsToAdd);
                request.setAttribute("result", "success");
            } else {
                request.setAttribute("alertMessage", "로그인 성공! 그러나 포인트 추가에 실패했습니다.");
                request.setAttribute("result", "failure");
            }

            // signin.jsp로 포워딩
            request.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(request, response);
        } else {
            // 세션이 없거나 로그인되지 않은 경우 로그인 페이지로 이동
            response.sendRedirect(request.getContextPath() + "/user/signin");
        }
    }
}
