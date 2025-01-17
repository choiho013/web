package com.itwill.spring2.repository;

import com.itwill.spring2.domain.Member;
import com.itwill.spring2.dto.MemberSignInDto;

public interface MemberDao {
	Member selectByUsername(String username);
	Member selectByEmail(String email);
	int insert(Member member);
	Member selectByUsernameAndPassword(Member member);

}
