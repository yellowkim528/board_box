package com.kh.board.domain.reply.dao;

import com.kh.board.domain.entity.Reply;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ReplyDAOImplTest {

  @Autowired
  ReplyDAO replyDAO;

  @Test
  @DisplayName("댓글목록")
  void finaAll() {
    List<Reply> replies = replyDAO.finaAll(1L);
    for (Reply reply : replies) {
      log.info("replies={}", replies);
    }
    log.info("size={}", replies.size());

  }

  @Test
  @DisplayName("댓글수정")
  void updateById() {
    Long replyId = 1L;
    Reply reply = new Reply();
    reply.setReplyBody("수정된 댓글1");

    int updatedRowCnt = replyDAO.updateById(replyId, reply);

    log.info("updatedRowCnt={}", updatedRowCnt);

  }
}