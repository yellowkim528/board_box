package com.kh.board.web.req.reply;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResAdd {
  private Long replyId;
  private String replyBody;
}
