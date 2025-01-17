package com.itwill.blog.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.domain.Post;
import com.itwill.blog.service.PostService;

/**
 * Servlet implementation class PostSearchController
 */
@WebServlet(name = "postSearchController", urlPatterns = { "/post/search" })
public class PostSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 디버그 로그 출력을 위한 객체
	private static final Logger log = LoggerFactory.getLogger(PostSearchController.class);
	
	private final PostService postService = PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostSearchController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
        String category = request.getParameter("category");
        String keyword = request.getParameter("keyword");
        String pageParam = request.getParameter("p"); // 페이지 번호 파라미터
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1; // 기본 페이지는 1

        log.debug("doGet(category={}, keyword={}, page={})", category, keyword, page);
        // 기본값 설정
        if (category == null || category.isEmpty()) {
            category = "t"; // 기본값: 제목 검색
        }
        if (keyword == null || keyword.isEmpty()) {
            keyword = ""; // 기본값: 빈 문자열
        }
        
        // 게시물 목록 가져오기 (페이징 적용)
        List<Post> posts = postService.read(category, keyword, page);
        

        // JSP로 전달할 데이터 설정
        request.setAttribute("posts", posts);
        request.setAttribute("currentPage", page);
        request.setAttribute("category", category);
        request.setAttribute("keyword", keyword);

        // JSP로 포워딩
        request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
    	
    	
	}

}