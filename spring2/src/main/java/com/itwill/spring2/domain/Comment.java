package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DB comments 테이블의 엔터티(모델).
@Data									// 데이터는 필수 아규먼트를 갖는 생성자를 만들어줌
										// 필수는 파이널. 파이널이 없으면 기본 생성자.
@NoArgsConstructor // -> 스프링 컨테이너(디스패처 서블릿, 마이바티스)에서 사용되는 생성자.					
@AllArgsConstructor // -> Builder 디자인 패턴에서 사용되는 생성자.빌더에서 필요로 하는 생성자
@Builder								
public class Comment {
	private Integer id; 			   // PK
	private Integer postId;			   // post_id 컬럼. FK
	private String username;		   // 댓글 작성자
	private String ctext;			   // 댓글 내용
	private LocalDateTime createdTime; // created_time 컬럼. 댓글 작성 시간 
	private LocalDateTime modifiedTime;// modified_time 컬럼. 댓글 최종 수정 시간
	
	
}

//디스패쳐 서블릿은 아규먼트가 한개도 없는 기본 생성자만 사용하고
// 그리고 세터로 리퀘스트 파라미터로 값을 채우는 역할
// 그래서 모델 클래스를 만들 때 기본 생성자만 있으면 빌더는 오류가나고
// noArgs 와 allArgs가 다 있어야 함.
