package com.kh.board.domain.reply.dao;

import com.kh.board.domain.entity.Reply;

import java.util.List;

public interface ReplyDAO {
  // 목록
  List<Reply> finaAll();

  // 추가
  Long addReply(Reply reply);

  // 삭제
  int deleteById(Long replyId);

  // 수정
  int updateById(Long replyId, Reply reply);

}
