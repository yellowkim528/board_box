package com.kh.board.web.req.reply;

import lombok.Data;

@Data
public class ReqAdd {
  private String replyBody;
  private String replyWriter;
  private Long postId;
}
