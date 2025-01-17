package com.itwill.spring2.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml"})
public class PostServiceTest {
	
	// 단위 테스트에서는, 
	// 단위 테스트 객체를 생성하고 테스트 메서드를 호출하는 테스트 메인 쓰레드는 
	// 단위 테스트 클래스(class PostServiceTest)의 "기본 생성자"만 호출.
	// -> 아규먼트를 갖는 생성자를 호출할 수 없음. 그렇게 때문에 
	// -> 애너테이션을 사용한 의존성 주입만 가능.
	
	@Autowired private PostService postService; // 애너테이션을 사용한 의존성 주입
	
//	@Test
	public void testRead() {
		Assertions.assertNotNull(postService);
		
		List<Post> list = postService.read();
		list.forEach(x -> log.debug("{}",x));
		
	}
	
//	@Test
	public void testReadId() {
		
		Post post = postService.read(61);
		Assertions.assertNotNull(post);
	}
	
//	@Test
//	public void testCreat() {
//		
//		Post post = Post.builder()
//				.title("안녕하세").content("월요일1").author("aa").build();
//		
//		postService.create(post);
//		Assertions.assertNotNull(post);
//	}
	
//	@Test
//	public void testUpdate() {
//		Post post = Post.builder()
//				.title("나나나").content("라라라").id(82).build();
//		
//		int result = postService.update(post);
//		Assertions.assertEquals(1, result);
//	}
	
//	@Test
	public void testDelete() {
		int result = postService.delete(82);	
		Assertions.assertEquals(1, result);
	}
}
