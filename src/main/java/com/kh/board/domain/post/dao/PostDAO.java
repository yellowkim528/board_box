package com.kh.board.domain.post.dao;

import com.kh.board.domain.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostDAO {
  // 작성
  Long save(Post post);

  // 조회
  Optional<Post> findById(Long postId);

  // 수정
  int updateById(Long postId, Post post);

  // 삭제
  int deleteById(Long postId);

  // 목록
  List<Post> findAll();

}
