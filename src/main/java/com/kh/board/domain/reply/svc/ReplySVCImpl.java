package com.kh.board.domain.reply.svc;

import com.kh.board.domain.entity.Reply;
import com.kh.board.domain.reply.dao.ReplyDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplySVCImpl implements ReplySVC{

  private final ReplyDAO replyDAO;


  // 댓글 목록
  @Override
  public List<Reply> findAll(Long postId) {
    return replyDAO.finaAll(postId);
  }

  // 댓글 작성
  @Override
  public Long addReply(Reply reply) {
    return replyDAO.addReply(reply);
  }

  // 댓글 삭제
  @Override
  public int deleteById(Long replyId) {
    return replyDAO.deleteById(replyId);
  }

  // 댓글 수정
  @Override
  public int updateById(Long replyId, Reply reply) {
    return replyDAO.updateById(replyId, reply);
  }
}
