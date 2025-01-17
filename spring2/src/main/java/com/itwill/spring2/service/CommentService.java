package com.itwill.spring2.service;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Comment;
import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
	
	// final 필드와 (필수 아규먼트를 갖는) 생성자를 사용한 의존성 주입
	private final CommentDao commentDao;
	
	// 해당 아이디의 댓글 (1개)를 검색하는 서비스
	public CommentItemDto readById(Integer id) {
		log.debug("readById(id={})",id);
		
		// 영속성 계층의 메서드를 호출해서 select 쿼리를 실행.
		Comment comment = commentDao.selectById(id);
		
		return CommentItemDto.fromEntiy(comment); //dto 만들어지기 전 스태틱으로 
	}
	
	// 특정 포스트에 달려 있는 모든 댓글을 검색하는 서비스
	// 메서드 오버로딩은 파라미터가 다를때에만, 리턴 타입은 상관없음
	public List<CommentItemDto> readByPostId(Integer postId) {
		log.debug("readByPostId(postId={})", postId);
		
		List<Comment> list= commentDao.selectByPostId(postId);
//		List<CommentItemDto> result = new ArrayList<>();
//		for(Comment c : list ) {
//			result.add(CommentItemDto.fromEntiy(c));
//		}
//		return result;
		
		//람다 표현식 -----------
		return list.stream()
				.map(CommentItemDto::fromEntiy) //메서드 참조 //.map(x -> CommentItemDto.fromEntiy(x))
				//리스트 원소를 맵이 클래스로 아규먼트로 전달했을대 리턴해주는 값을 매핑해줌
				.toList();
		// 원소가 stream으로 들어오면 mapping을 하면 dto가 나오고 toList 리스트를 만드는것.
	}
	
	// 특정 포스트에 댓글을 추가하는 서비스
	public int create(CommentCreateDto dto) { //
		log.debug("create(dto={})", dto);
		
		int result = commentDao.insertComment(dto.toEntity());
		
		return result;
	}
	
	// 아이디가 일치하는 댓글을 삭제하는 서비스
	public int delete(Integer id) {
		log.debug("delete(id={})", id);
		
		return commentDao.deleteById(id);
	}
	
	// 댓글 내용을 수정하는 서비스
	public int update(CommentUpdateDto dto) {
		log.debug("update(dto={})", dto);
	
		
		return commentDao.updateComment(dto.toEntity());
		
	}
}
