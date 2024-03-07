package com.kh.board.web.form.reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReqAdd {
  private String replyBody;
  private String replyWriter;
  private Long postId;
}
