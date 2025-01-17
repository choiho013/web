package com.itwill.jsp2.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostDeleteController
 */
@WebServlet(name = "postDeleteController", urlPatterns = { "/post/delete" })
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostDeleteController.class);
	private final PostService postService = PostService.INSTANCE;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDeleteController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
    	// 요청 파라미터 id 값을 읽음.
		int id = Integer.parseInt(request.getParameter("id"));
		log.debug("doGet(id={})",id);
		
		// 서비스 계층의 메서드를 호출해서 해당 아이디의 포스트를 DB 테이블에서 삭제함.
		postService.delete(id);
		
		// 목록 페이지로 이동(redirect).
		String url = request.getContextPath() + "/post/list";
		log.debug("redirect to {}", url);
		response.sendRedirect(url);
		
//		request.setAttribute("post", post);
		
		
	}

}
