package com.itwill.spring2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.PostDao;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 스프링 컨테이너에 서비스 컴포넌트로 등록.
@RequiredArgsConstructor //final 필드를 초기화할 수 있는 아규먼트(들)을 갖는 생성자.
public class PostService {
	// 의존성 주입 방법:
	// (1) 애너테이션을 사용한 의존성 주입
	// (2) final 필드와 생성자를 사용한 의존성 주입 - 스프링 공식 문서에서 권장하는 방법.
	
	// (1) 애너테이션을 사용한 의존성 주입(DI: Dependency Injection)
//	@Autowired private PostDao postDao; 
	// 객체를 사용하는 클래스는 수동적으로 생성되있는 객체를 주입하여 사용하는게 의존성 주입, 제어의 역전
	
	// (2) final 필드와 생성자를 사용한 의존성 주입
	private final PostDao postDao;
	
	// 컨테이너는 말 그대로 박스, 객체들을 가지고 있는 컨테이너.
	
	// 목록보기 서비스
	public List<Post> read(){
		log.debug("read()");
		
		List<Post> list=postDao.selectOrderByIdDesc();
		
		return list;
	}
	
	// 상세보기 서비스
	public Post read(Integer id) {
		
		Post post = postDao.selectById(id);
		return post;
		
	}
	
	// 새글작성 서비스
	public int create(PostCreateDto dto) {
		log.debug("create(dto={}",dto);
		
		// PostService ==> PostDao 계층에서 메서드 호출 & Entity를 아규먼트로 전달.
		// 컨트롤러가 넘겨준 dto를 모델타입으로 변환해서 다오에 넘겨주는거고 다오는 실제 테이블에 insert 해주는 것
		int result = postDao.insertPost(dto.toEntity());
		return result;
	}
	
	// 수정하기 서비스
	public int update(PostUpdateDto dto) {
		int result = postDao.updatePost(dto.toEntity());
		return result;
	}
	
	// 삭제하기 서비스
	public int delete(Integer id) {
		int result = postDao.deletePost(id);
		
		return result;
	}
	
	public List<Post> read(PostSearchDto dto) {
		log.debug("read(dto={})", dto);
		
		// 영속성 계층의 메서드를 호출해서 DB에서 select를 수행하고 결과를 가져옴.
		List<Post> list = postDao.search(dto);
		log.debug("# of search result = {}", list.size());
		
		return list;
	}
}
