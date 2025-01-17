package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml"})
public class CommentDaoTest {
	
	@Autowired CommentDao commentDao;  
	
//	@Test
	public void testSelectByPostId() {
		Assertions.assertNotNull(commentDao);
		
		List<Comment> list = commentDao.selectByPostId(1);
		log.debug("# of comments = {}", list.size());
		list.forEach(x -> log.debug("{}", x));
	}
	
//	@Test
	public void testInsertComment() {
		Comment comment = Comment.builder().postId(64).ctext("안녕2").username("admin").build();
		
		int result = commentDao.insertComment(comment);
		
		log.debug("comment = {}",result);
		Assertions.assertEquals(1, result);
	}
	
//	@Test
	public void testDeleteById() {
		int result = commentDao.deleteById(4);
		
		Assertions.assertEquals(1, result);
		
	}
	
//	@Test
	public void testDeleteByPostId() {
		int result = commentDao.deleteByPostId(64);
		Assertions.assertEquals(4, result);
	}
	
//	@Test
	public void testUpdateComment() {
		Comment comment = Comment.builder().id(7).ctext("안녕하세요").build();
		
		int result = commentDao.updateComment(comment);
		Assertions.assertEquals(1, result);
	}
	
//	@Test
	public void testSelectCommentCount() {
		Integer result = commentDao.selectCommentCount(1);
		
		Assertions.assertEquals(3, result);
	}
	
	@Test
	public void testSelectById() {
		// 댓글 아이디가 테이블에 있는 경우:
		Comment c1 = commentDao.selectById(6);
		
		Assertions.assertNotNull(c1);
		log.debug("c1 = {}",c1);
		
		// 댓글 아이디가 테이블에 없는 경우:
		Comment c2 = commentDao.selectById(1000);
		Assertions.assertNotNull(c2);
		log.debug("c1={}",c2);
	}
}
