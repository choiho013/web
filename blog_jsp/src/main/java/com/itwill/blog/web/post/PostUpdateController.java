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
import com.itwill.blog.service.PostService;

/**
 * Servlet implementation class PostUpdateController
 */
@WebServlet(name = "postUpdateController", urlPatterns = { "/post/update" })
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2,
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 50
	)
public class PostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 디버그 로그 출력을 위한 객체 생성.
	private static final Logger log = LoggerFactory.getLogger(PostUpdateController.class);
	
	// 비지니스 로직을 처리하는 서비스 객체 생성 (싱글톤 패턴)
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
    	request.setCharacterEncoding("UTF-8");
    	// 양식 데이터를 읽음
    	Integer id = Integer.parseInt(request.getParameter("id"));
    	String title = request.getParameter("title");
    	String content = request.getParameter("content");
    	String existingFiles = request.getParameter("existingFiles");
    	
    	// 기존 파일 삭제
        if (existingFiles != null && !existingFiles.isEmpty()) {
            deleteFiles(existingFiles);
        }
 
    	List<String> fileNames = saveUploadeFiles(request);
    	String files = String.join(",", fileNames);
    	
    	// 포스트 객체 생성 및 빌더패턴 적용
    	Post post = Post.bulider()
    			.id(id)
    			.title(title)
    			.content(content)
    			.files(files)
    			.build();
    	
    	
    	// post에 할당된 데이터 로그 출력
    	log.debug("doPost(post= {})", post);
    			
    	// 게시물 수정 로직
    	postService.update(post);
    	
    	// 수정 후 지정된 경로로 이동
    	String url = request.getContextPath() + "/post/list";
    	response.sendRedirect(url);
	}
    
    
    private void deleteFiles(String existingFiles) {
    	String uploadPath = "C:/java157/tool/git/HTML/lab-web/blog_jsp/src/main/webapp/static/file";
    	
    	String[] files = existingFiles.split(",");
    	
    	for(String fileName : files) {
    		File file = new File(uploadPath + File.separator + fileName);
    		if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    log.debug("Deleted file: {}", file.getAbsolutePath());
                } else {
                    log.warn("Failed to delete file: {}", file.getAbsolutePath());
                }
            }
    	}
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
