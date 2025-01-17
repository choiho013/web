package com.itwill.spring1.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring1.dto.User;

import lombok.extern.slf4j.Slf4j;

/*
 * @Controller vs @RestController
 * @Controller 애너테이션이 설정된 클래스의 컨트롤러 메서드들의 리턴 값은
 *  - 기본적으로는 뷰의 이름을 의미.
 *  - @ResponseBody가 설정된 메서드인 경우에는, 응답으로 전송되는 데이터.
 * @RestController 애너테이션이 설정된 클래스의 모든 컨트롤러 메서드들의 리턴 값은
 * - 응답으로 전송되는 데이터.
 * - RestController의 메서드에서는 @ResponseBody 애너테이션을 사용하지 않음.
 */

@Slf4j
@RestController // 리스폰바디가 생략된 것.

public class ExampleRestController {
	
	@GetMapping("/rest3")
	public String rest3() {
		log.debug("rest3()");
		
		return "안녕하세요, REST!";
	}
	
	@GetMapping("/rest4")
	public List<User> rest4() {
		log.debug("rest4()");
		
		User user1 = User.builder().age(10).username("홍길동").build();
		User user2 = User.builder().age(16).username("오쌤").build();
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		
		return users;
	}

	// 리스펀스 바디는 메서드에 쓰는 것 . 메서드의 리턴값이 뷰의 이름이 아니고 응답으로 직접 전달하는 
	// 컨트롤러가 리스펀스 바디로 이루어져 있으면, 클래스에서 RestController로 설정을 하고,
	// 요청방식 주소 매핑을 하면 어떤 것들이던지 클라이언트로 직접 응답
	
	// 오브젝트를 표기하는 문자열 표기 방식 JSON
}
