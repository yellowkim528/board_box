package com.kh.board.domain.reply.svc;

import com.kh.board.domain.entity.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReplySVC {
  //댓글 목록
  List<Reply> findAll();

  //댓글 작성
  Long addReply(Reply reply);

  //댓글 삭제
  int deleteById(Long replyId);

  //댓글 수정
  int updateById(Long replyId, Reply reply);
}
