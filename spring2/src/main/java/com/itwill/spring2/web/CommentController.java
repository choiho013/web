package com.itwill.spring2.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.domain.Comment;
import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.service.CommentService;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // 필수 아규먼트 생성자
@RestController //-> 컨트롤러 메서드의 리턴 값이 뷰의 이름이 아니라 클라이언트로 직접 응답되는 데이터.
@RequestMapping("/api/comment")
public class CommentController {
	
	// final 필드와 (필수 아규먼트를 갖는) 생성자를 이용한 의존성 주입
	private final CommentService commentService;
	
	@GetMapping("/{id}")
	//-> 요청 주소가 변수처럼 변할 수 있는 값(경로변수, path variable)일 때, {varName} 형식으로 작성. 
	public ResponseEntity<CommentItemDto> getCommentById(@PathVariable Integer id) {
		//PathVariable: 요청 URI에 포함되어있는 변수. 
		// @PathVariable: 요청 주소의 일부가 변수처럼 변할 수 있는 값일 때, (요청 주소를 분석하는것은 디스패쳐 서블릿)
		// 디스패쳐 서블릿이 요청 주소를 분석해서 메서드의 아규먼트로 전달해줌.	
		// (1) @PathVariable(name = "id") 처럼 경로 변수(path variable)의 이름을 명시하거나,
        // (2) 메서드의 파라미터 이름을 경로 변수와 동일하게 선언하면 됨.
		// (Eclipse) Window -> Preferences -> Java -> Compiler ->
        // "Store information about method parameters (usable via reflection)" 항목을 체크해야 함.
		log.debug("getCommentById(id={})", id);
		
		CommentItemDto comment = commentService.readById(id);
		
		return ResponseEntity.ok(comment); 
		// 리스폰스 엔터티로 응답을 조절가능하다 ResponseEntity를 안쓰거나 ok면 널이든 아니든 200 성공으로 나왔는데
		// 어떤메서드를 이용하더라도 응답이 200이 될수도 400이 될수도 조절할 수 있는 메서드

		// ResponseEntity<T>
		// 서버가 클라이언트와 보내는 데이터와 응답코드를 함께 설정할 수 있는 객체
		
		// REST 컨트롤러 메서드가 자바 객체를 리턴하면
        // jackson-databind 라이브러리가 자바 객체를 JSON 문자열로 변환을 담당하고,
        // JSON 문자열이 클라이언트로 전송(응답)됨.
        // jackson-databind 라이브러리의 역할:
        //   1. 직렬화(serialization): 자바 객체 -> JSON (문자열)
        //   2. 역직렬화(de-serialization): JSON -> 자바 객체
        // jackson-databind 라이브러리에서 
        // Java 8 이후에 생긴 날짜/시간 타입(LocalDate, LocalDateTime)을 JSON으로 변환하기 위해서는
        // jackson-datatype-jsr310 모듈이 필요함(POM.xml에 dependency 추가).
	}
	
	@GetMapping("/all/{postId}")
	public ResponseEntity<List<CommentItemDto>> getAllCommentsByPostId(@PathVariable Integer postId){
		log.debug("getAllCommentsByPostId(postId={})",postId);
		
		List<CommentItemDto> list = commentService.readByPostId(postId);
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<Integer> registerComment(@RequestBody CommentCreateDto dto) {
		// @RequestBody 없으면 에러 500. 
		// @RequestBody:
		// 디스패쳐 서블릿이 Ajax 요청에서 요청 패킷 몸통(body)에 포함된 JSON 문자열을 읽고
		// jackson-databind 라이브러리를 사용해서 자바 객체로 변환 후 = 역직렬화
		// 컨트롤러 메서드의 아규먼트로 전달해줌.
		log.debug("registerComment(dto={})", dto);
		
		int result = commentService.create(dto);
		
		return ResponseEntity.ok(result);
	}
	//패킷 
	// 헤더와 바디 헤더는 기본정보 실제 정보는 바디.
	// 클라이언트가 서버 또는 반대도 http 패킷
	// 제이슨 문자열의 형식은 중괄호는 오브젝트, 프로퍼티 이름과 값을 콜론, 이름은 반드시 큰따옴표
	// 프로퍼티끼리는 쉼표로 구분, 배열은 대괄호, 그래서 프로퍼티 이름은 dto의 필드이름과 같아야함
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteComment(@PathVariable Integer id){
		log.debug("deleteComment(id={})",id);
		
		int result = commentService.delete(id);
		
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> updateComment(@PathVariable Integer id, @RequestBody CommentUpdateDto dto){
		log.debug("updateComment(id={},dto=})",dto);
		
		// 중복되는 데이터를 패킷에 보낼때 id가 겹치게 된다.
		// 플러그인에 send 할때 body에 텍스트만 있고 아이디를 뺐을때 dto에 들어오는 아이디는 null인데 
		// 컨트롤러 서비스를 채워줄때 dto.setId(id)를 넣었기 때문에 서비스에 자동적으로 id가 채워지게 된다.
		dto.setId(id); // 클라이언트에 정보를 보내줄 때 아이디도 리퀘스트 바디 dto에 넣어서 보내기
		int result = commentService.update(dto);
		
		return ResponseEntity.ok(result);
		
		
	}
	
}
