package com.itwill.blog.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Member {
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String birthday;
	private String email;
	private Integer points;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;

	public Member() {
	}

	public Member(Integer id, String username, String password, String name, String phone, String birthday,
			String email, Integer points, LocalDateTime createdTime, LocalDateTime modifiedTime) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.birthday = birthday;
		this.email = email;
		this.points = points;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", phone="
				+ phone + ", birthday=" + birthday + ", email=" + email + ", points=" + points + ", createdTime="
				+ createdTime + ", modifiedTime=" + modifiedTime + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public static MemberBuilder bulider() {
		return new MemberBuilder();
	}

	public static class MemberBuilder {
		private Integer id;
		private String username;
		private String password;
		private String email;
		private String name;
		private String phone;
		private String birthday;
		private Integer points;
		private LocalDateTime createdTime;
		private LocalDateTime modifiedTime;

		private MemberBuilder() {
		}

		public MemberBuilder id(Integer id) {
			this.id = id;
			return this;
		}

		public MemberBuilder username(String username) {
			this.username = username;
			return this;
		}

		public MemberBuilder password(String password) {
			this.password = password;
			return this;
		}

		public MemberBuilder email(String email) {
			this.email = email;
			return this;
		}

		public MemberBuilder name(String name) {
			this.name = name;
			return this;
		}

		public MemberBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public MemberBuilder birthday(String birthday) {
			this.birthday = birthday;
			return this;
		}

		public MemberBuilder points(Integer points) {
			this.points = points;
			return this;
		}

		public MemberBuilder createdTime(LocalDateTime createdTime) {
			this.createdTime = createdTime;
			return this;
		}

		public MemberBuilder createdTime(Timestamp createdTime) {
			this.createdTime = createdTime.toLocalDateTime();
			return this;
		}

		public MemberBuilder modifiedTime(LocalDateTime modifiedTime) {
			this.modifiedTime = modifiedTime;
			return this;
		}

		public MemberBuilder modifiedTime(Timestamp modifiedTime) {
			this.modifiedTime = modifiedTime.toLocalDateTime();
			return this;
		}

		public Member build() {
			return new Member(id, username, password, name, phone, birthday, email, points, createdTime, modifiedTime);
		}
	}

}
