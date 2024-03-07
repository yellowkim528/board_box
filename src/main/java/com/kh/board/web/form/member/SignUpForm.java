package com.kh.board.web.form.member;

import lombok.Data;

@Data
public class SignUpForm {
  private String email;
  private String passwd;
  private String nickname;
}
