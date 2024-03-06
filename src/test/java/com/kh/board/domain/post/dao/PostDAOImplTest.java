package com.kh.board.domain.post.dao;

import com.kh.board.domain.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class PostDAOImplTest {

  @Autowired
  PostDAO postDAO;

  @Test
  @DisplayName("삭제")
  void deleteById() {
    Long pid = 52L;
    int deletedRowCnt = postDAO.deleteById(pid);
    Assertions.assertThat(deletedRowCnt).isEqualTo(1);
  }

  @Test
  @DisplayName("수정")
  void updateById() {
    Long postId = 22L;
    Post post = new Post();
    post.setPostTitle("제목바꿈");
    post.setWriter("작가바꿈");
    post.setPostBody("내용바꿈");

    int updatedRowCnt = postDAO.updateById(postId, post);

//    log.info("updatedRowCnt={}", updatedRowCnt);

    if (updatedRowCnt == 0) {
      Assertions.fail("변경한게 없는디");
    }
    Optional<Post> optionalPost = postDAO.findById(postId);
    if (optionalPost.isPresent()) {
      Post findedPost = optionalPost.get();
      Assertions.assertThat(findedPost.getPostTitle()).isEqualTo("제목바꿈");
      Assertions.assertThat(findedPost.getWriter()).isEqualTo("작가바꿈");
      Assertions.assertThat(findedPost.getPostBody()).isEqualTo("내용바꿈");
    } else {
      Assertions.fail("바꿀놈이 음슴");
    }
    
  }
}