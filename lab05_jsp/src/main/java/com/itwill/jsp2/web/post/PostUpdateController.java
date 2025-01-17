package com.itwill.jsp2.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostUpdateController
 */
@WebServlet(name = "postUpdateController", urlPatterns = { "/post/update" })
public class PostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostUpdateController.class);
	private final PostService postService = PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostUpdateController() {
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	// 양식 데이터(id, title, content) 값을 읽음.
    	int id = Integer.parseInt(request.getParameter("id"));
    	String title = request.getParameter("title");
    	String content = request.getParameter("content");
    	
    	Post post = Post.bulider()
    			.id(id).title(title).content(content).build();
    	log.debug("doPost(post={})", post);
    	
    	// 서비스 계층의 메서드를 호출해서 DB 테이블에 업데이트.
    	postService.update(post);
    	
    	// 목록 페이지로 이동(redirect).
    	// 상세보기 페이지 이동: "/post/details?id=" + id;
    	String url = request.getContextPath() + "/post/list";
    	log.debug("redirect to {}", url);
    	
    	response.sendRedirect(url);
	}

}
