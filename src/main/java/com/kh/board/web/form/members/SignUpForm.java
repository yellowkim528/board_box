package com.kh.board.web.form.members;

import lombok.Data;

@Data
public class SignUpForm {
  private String email;
  private String passwd;
  private String nickname;
}
