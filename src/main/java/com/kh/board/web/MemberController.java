package com.kh.board.web;

import com.kh.board.domain.entity.Member;
import com.kh.board.domain.member.svc.MemberSVC;
import com.kh.board.web.form.members.SignUpForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberSVC memberSVC;

  //회원가입양식
  @GetMapping("/join")
  public String signUpForm() {
    return "/member/signUp";
  }

  //회원가입처리
  @PostMapping("/join")
  public String signUp(SignUpForm signUpForm) {
    log.info("{signUpForm={}",signUpForm);
    // 유효성검증

    // 가입처리
    Member member = new Member();
    BeanUtils.copyProperties(signUpForm, member);
    Long memberId = memberSVC.joinMember(member);
    log.info("memberId={}",memberId);

    return (memberId != null) ? "redirect:/login" : "redirect:/join";

  }
}
