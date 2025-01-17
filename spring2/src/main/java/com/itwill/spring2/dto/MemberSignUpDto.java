package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Member;

import lombok.Data;

@Data
public class MemberSignUpDto {
	private String username;
	private String password;
	private String email;
	
	// DTO -  Entity 변환 메서드
	public Member toEntity() {
		return Member.builder().username(username).password(password).email(email).build();
	}
}
