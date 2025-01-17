package com.itwill.blog.web.post;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.domain.Post;
import com.itwill.blog.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostListController
 */
@WebServlet(name = "postListController", urlPatterns = { "/post/list" })
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 디버그 로그 출력을 위한 객체 생성.
	private static final Logger log = LoggerFactory.getLogger(PostListController.class);
	
	// 게시물 목록 데이터를 처리하는 비지니스 로직 서비스 객체 (싱글톤 패턴)
	private final PostService postService = PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostListController() { // 기본 생성자
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
//    	// 디버그 로그: doGet 메서드 호출 확인
//    	log.debug("doGet()"); 
//    	
//    	// 게시물 목록 데이터를 조회
//    	List<Post> post = postService.read(); 
//    	
//    	// 조회된 게시물 목록을 요청 속성에 추가
//    	request.setAttribute("posts", post); 
//    	
//    	// 서버에서 list.jsp로 요청 전달
//    	request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
        String pageParam = request.getParameter("p");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1; // 기본 페이지 번호는 1

        // 페이지 당 표시할 게시물 개수
        int pageSize = 10;

        // 전체 게시물 개수 가져오기
        int totalPosts = postService.getTotalPostCount();

        // 현재 페이지에 해당하는 게시물 가져오기
        List<Post> posts = postService.read(page, pageSize);

        // 마지막 페이지 번호 계산
        int lastPage = (totalPosts % pageSize == 0) ? (totalPosts / pageSize) : (totalPosts / pageSize + 1);

        // JSP에 필요한 데이터 설정
        request.setAttribute("posts", posts);
        request.setAttribute("currentPage", page);
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("totalPosts", totalPosts);

        // JSP로 포워딩
        request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
    }

}