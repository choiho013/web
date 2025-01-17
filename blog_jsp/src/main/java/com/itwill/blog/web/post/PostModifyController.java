package com.itwill.blog.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.domain.Post;
import com.itwill.blog.service.PostService;

/**
 * Servlet implementation class PostModifyController
 */
@WebServlet(name = "postModifyController", urlPatterns = { "/post/modify" })
public class PostModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 디버그 로그 출력을 위한 객체 생성
	private static final Logger log = LoggerFactory.getLogger(PostModifyController.class);
    
	// 비지니스 로직을 처리하는 서비스 객체 (싱글톤 패턴)
	private final PostService postService = PostService.INSTANCE;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostModifyController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//PostModifyController doGet()요청 디버그 출력
		log.debug("doGet()");
		
		//detailds의 질의 문자열 요청 파라미터 id를 읽음. name 속성이 있어야함.
		Integer id = Integer.parseInt(request.getParameter("id"));
		//요청 파라미터 id 로그를 출력
		log.debug("doGet(id={})",id);
		
		//상세보기 목록을 읽음.
		Post post = postService.read(id);
		
		// 포스트 객체 요청의 데이터를 저장, jsp에 전달.
		request.setAttribute("post", post);
		
		// modify 뷰로 이동
		request.getRequestDispatcher("/WEB-INF/views/post/modify.jsp").forward(request, response);
	}


}
