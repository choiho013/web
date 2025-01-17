package com.itwill.blog.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.service.PostService;

/**
 * Servlet implementation class PostDeleteController
 */
@WebServlet(name = "postDeleteController", urlPatterns = { "/post/delete" })
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//디버그 로그 출력
	private static final Logger log = LoggerFactory.getLogger(PostCreateConteroller.class);
		
	// 비지니스 로직을 처리하는 서비스 객체 (싱글톤 패턴)
	private final PostService postService = PostService.INSTANCE;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDeleteController() { //기본 생성자
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
    	// 요청 파라미터 id 값을 읽음.
    	Integer id = Integer.parseInt(request.getParameter("id"));
    	// 요청된 아이디의 값을 로그 출력
    	log.debug("doGet(id = {})", id);
    	
    	// 게시물 데이터를 삭제하는 로직 호출
    	postService.delete(id);
    	
    	// 삭제후 목록 페이지 이동
    	String url = request.getContextPath() + "/post/list";
    	response.sendRedirect(url);
    }

}
