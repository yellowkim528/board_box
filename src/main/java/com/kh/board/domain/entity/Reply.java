package com.kh.board.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reply {
  private Long replyId;                // REPLY_ID	NUMBER(10,0)
  private String replyBody;            // REPLY_BODY	CLOB
  private String replyWriter;          // REPLY_WRITER	VARCHAR2(60 BYTE)
  private Long postId;                 // POST_ID	NUMBER(10,0)
  private LocalDateTime cdate;         // CDATE	TIMESTAMP(6)
  private LocalDateTime udate;         // UDATE	TIMESTAMP(6)
}
