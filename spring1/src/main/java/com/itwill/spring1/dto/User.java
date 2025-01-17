package com.itwill.spring1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data //-> @Getter, @Setter, @toString, @EqualsAndHashCode, @RequiredArgsConstructor
// @RequiredArgsConstructor: final 필드를 초기화할 수 있는 아규먼트(들)을 갖는 생성자.
@NoArgsConstructor //-> 기본 생성자.  
@AllArgsConstructor //-> 모든 필드를 초기화할 수 있는 아규먼트들를 갖는 생성자.
@Builder //-> Builder 디자인 패턴을 적용. 
public class User {
	// final 필드가 하나도 없기 때문에 기본 생성자가 만들어 진거고 RequiredArgsConstructor
	// AllArgs를 쓰게되면 기본생성자는 없어지기 때문에 noArgs도 같이 설정.
	
	private String username;
	private Integer age;
	//기본 타입보다는 래퍼클래스 타입 Integer로 적는게 좋다. 기본값이 없을때 널로 자동으로 채워주기 때문
	
//	@Getter private int id;  // 따로 하나만 줄 경우에 애너테이션
	
}
