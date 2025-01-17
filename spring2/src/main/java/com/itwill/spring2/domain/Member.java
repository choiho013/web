package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DB members 테이블의 엔터티 클래스(Domain, Model)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Integer points;
	private LocalDateTime createdTiem;
	private LocalDateTime modifiedTime;

}
