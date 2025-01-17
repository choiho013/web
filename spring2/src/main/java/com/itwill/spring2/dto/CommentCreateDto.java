package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Comment;

import lombok.Data;

@Data
public class CommentCreateDto {
	private Integer postId;
	private String username;
	private String ctext;
	
	//DTO -> Entity 변환 메서드
	public Comment toEntity() {
		return Comment.builder()
				.postId(postId).username(username).ctext(ctext)
				.build();
				// 생성된 dto는 데이터를 갖고 있으니 갖고있는 데이터로 comment를 만듦
//		그래서 스태틱과 파람이 상관없
	}
	
	// reqeuest 컨트롤러가 서비스를 호출 그때 dto를 줌 서비스는 리포지토리에 전달  리포지토리와 연결되 있고
	// 서비스 리퍼지토리는 엔터티
}
