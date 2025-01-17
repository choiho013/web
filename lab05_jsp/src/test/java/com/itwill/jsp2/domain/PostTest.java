package com.itwill.jsp2.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostTest {
	private static final Logger log = LoggerFactory.getLogger(PostTest.class);
	
	@Test
	public void testPostBuilder() {
		Post post = Post.bulider()
				.id(1)
				.title("test")
				.content("테스트 빌더 디자인패턴")
				.author("admin")
				.createdTime(LocalDateTime.now())
				.modifiedTime(LocalDateTime.now())
				.build();
		
		//단위 테스트의 성공 케이스를 작성.
		Assertions.assertNotNull(post); //post 객체가 null이 아니면 단위 테스트 성공
		
		// Assertions.assertEquals(expected, actual): 실제값(actual)이 기댓값(expected)와 같으면 단위 테스트 성공.
		Assertions.assertEquals(1, post.getId()); // post 객체의 id 값이 1이면 단위 테스트 성공.
		Assertions.assertEquals("test",	post.getTitle());
		
		
		log.debug("post={}", post);
	}
	
}
