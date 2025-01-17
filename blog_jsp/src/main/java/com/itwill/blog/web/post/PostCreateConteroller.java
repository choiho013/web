package com.itwill.blog.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.domain.Post;
import com.itwill.blog.domain.Post.PostBuilder;
import com.itwill.blog.service.PostService;

/**
 * Servlet implementation class PostCreateConteroller
 */

@WebServlet(name = "postCreateConteroller", urlPatterns = { "/post/create" })
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 2,
	maxFileSize = 1024 * 1024 * 5,
	maxRequestSize = 1024 * 1024 * 50
)
	
public class PostCreateConteroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//디버그 로그 출력
	private static final Logger log = LoggerFactory.getLogger(PostCreateConteroller.class);
	
	// 비지니스 로직을 처리하는 서비스 객체 (싱글톤 패턴)
	private final PostService postService = PostService.INSTANCE;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public PostCreateConteroller() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	// 서버에서 create.jsp로 요청 전달
    	request.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	// post 디버그 출력.
    	log.debug("doPost()");
    	
    	// 새 글 작성의 데이터 요청 파라미터 읽기
    	String title = request.getParameter("title"); // 제목 읽기
    	String content = request.getParameter("content"); // 내용 읽기
    	String author = request.getParameter("author"); // 작성자 읽기
    	
    	List<String> fileNames = saveUploadeFiles(request);
    	String files = String.join(",", fileNames);
    	
    	// 포스트 객체를 생성하기 위한 빌더 패턴
    	// 빌더를 통해 Post 객체의 title, content, author 필드를 설정하고 생성.
    	Post post = Post.bulider() 
    			.title(title) 		// 게시물 제목 설정
    			.content(content)   // 게시물 내용 설정
    			.author(author)     // 작성자 설정
    			.files(files)		// 파일 설정
    			.build();			// 설정한 값으로 post 객체 생성
    	
    	// 요청 데이터로 게시물을 생성하는 비지니스 로직 호출
    	postService.create(post);
    	
    	// 게시물 생성 후 게시물 목록 페이지로 리다이렉트
    	String url = request.getContextPath() + "/post/list" ; // 응답 URL 생성
    	response.sendRedirect(url); // 클라이언트를 목록 페이지로 리다이렉트
	}
    
    private List<String> saveUploadeFiles(HttpServletRequest request) throws ServletException, IOException {
    	List<String> fileNames = new ArrayList<>();
    	
        // 프로젝트 루트 디렉토리 기준으로 저장 경로 설정
        // 저장 경로 설정

    	String uploadPath = "C:/java157/tool/git/HTML/lab-web/blog_jsp/src/main/webapp/static/file";
//    	String uploadPath = "C:/Github/HTML/lab-web/blog_jsp/src/main/webapp/static/file";
    	

        log.debug("Upload Path: " + uploadPath);

        // 저장 디렉토리가 없으면 생성
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            if (uploadDir.mkdirs()) {
                log.debug("Directory created: " + uploadPath);
            } else {
                throw new IOException("Failed to create directory: " + uploadPath);
            }
        }

        // 파일 저장
        for (Part part : request.getParts()) {
            if ("files".equals(part.getName()) && part.getSize() > 0) {
                // 업로드된 파일 이름 가져오기
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String savedPath = uploadPath + File.separator + fileName;

                try {
                    // 파일을 저장 경로에 저장
                    part.write(savedPath);
                    fileNames.add(fileName); // 파일 이름 저장
                    log.debug("File saved to: " + savedPath);
                } catch (IOException e) {
                    log.error("File save failed for: " + savedPath, e);
                }
            }
        }

        return fileNames;
    }
}