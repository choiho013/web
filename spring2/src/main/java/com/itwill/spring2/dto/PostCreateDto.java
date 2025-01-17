package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Post;

import lombok.Data;

// DTO(Data Transfer Object): 메서드의 아규먼트로 전달되거나, 메서드의 리턴 값으로 사용되는 객체.
// 클라이언트 ==> DispatcherServlet ==> PostController에게 요청 파라미터들을 전달할 때 사용할 객체.
// PostController ==> PostService 계층으로 새 글 작성 데이터를 전달할 때 사용할 객체.
@Data
public class PostCreateDto {
	// 필드 이름들을 요청 파라미터 이름과 같게 선언 & 기본 생성자 & setter
	private String title;
	private String content;
	private String author;
	
	// DTO를 Model로 변환해서 리턴하는 메서드.
	public Post toEntity() {
		return Post.builder().title(title).content(content).author(author).build();
	}
}
// 폼양식 새글, 수정..?

// 메서드를 호출하면서 아규먼트를 전달하고
// 서비스는 컨트롤러에 리턴값으로 객체를 전달
// 서비스가 다오에 메서드의 아규먼트로 전달 
// 다오는 ;;