package com.itwill.jsp2.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Post {
	private Integer id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
	public Post() {}

	public Post(Integer id, String title, String content, String author, LocalDateTime createdTime,
			LocalDateTime modifiedTime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", createdTime="
				+ createdTime + ", modifiedTime=" + modifiedTime + "]";
	}
	
	
	// builder 디자인 패턴
	
	public static PostBuilder bulider() {
		return new PostBuilder();	
		
	}
	public static class PostBuilder {
		private Integer id;
		private String title;
		private String content;
		private String author;
		private LocalDateTime createdTime;
		private LocalDateTime modifiedTime;
		
		
		private PostBuilder() {}
		
		// 세터와 비슷한데 리턴타입이 있는 메서드. 자기자신의 클래스 객체 타입을 리턴하는 메서드
		// 이 메서드를 호출하고 나서 포스트빌더로 체인하기 위해서 자기 클래스타입으로 리턴
		public PostBuilder id(Integer id) {
			this.id = id;
			return this;
		}
		
		public PostBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public PostBuilder content(String content) {
			this.content = content;
			return this;
		}

		public PostBuilder author(String author) {
			this.author = author;
			return this;
		}

		// 로컬은 필드에 저장하면 할 일은 끝나는데 이그제크트 쿼리에서 셀렉을 하면 
		// 컬럼의 값은 timestamp 값이기 때문에 변환하는 메서드를 넣은 것 
		// timestamp를 localDateTime으로 
		public PostBuilder createdTime(LocalDateTime createdTime) {
			this.createdTime = createdTime;
			return this;
		}

		public PostBuilder modifiedTime(LocalDateTime modifiedTime) {
			this.modifiedTime = modifiedTime;
			return this;
		}
		
		public PostBuilder modifiedTime(Timestamp modifiedTime) {
			this.modifiedTime = modifiedTime.toLocalDateTime();
			return this;
		}
		
		public PostBuilder createdTime(Timestamp createdTime) {
			this.createdTime = createdTime.toLocalDateTime();
			return this;
		}

		// 포스트 타입 객체를 생성해서 리턴해주는 메서드.
		public Post build() {
			return new Post(id, title, content, author, createdTime, modifiedTime);
		}
		// 내부클래스의 필드만 의미하는거고 포스트가 만들어지고 난 뒤의 아이디는 변경이 안됢
	}
	
	
}
