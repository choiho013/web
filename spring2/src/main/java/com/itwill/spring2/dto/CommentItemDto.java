package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentItemDto {
	
	// 서버에서 클라이언트
	private Integer id;
	private String username;
	private String ctext;
	private Timestamp modifiedTime;
	// 잭슨은 LDT을 자바스크립트 배열로 
	
	// 엔터티(Model, 도메인) 객체를 DTO 객체로 변환하는 편의 메서드.
	public static CommentItemDto fromEntiy(Comment comment) { 
		// db에서 select를 하면 커멘트객채가 나오고 그것을 필드4개를 갖는 dto로 변환하겠다
		
		if(comment != null) {
			
			return CommentItemDto.builder()
					.id(comment.getId())
					.username(comment.getUsername())
					.ctext(comment.getCtext())
					.modifiedTime(Timestamp.valueOf(comment.getModifiedTime()))
					.build();
		
		} else {
			
			return null;
		}
	}
}
