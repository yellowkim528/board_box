package com.kh.board.web;

import com.kh.board.domain.entity.Member;
import com.kh.board.domain.member.svc.MemberSVC;
import com.kh.board.web.form.members.LoginForm;
import com.kh.board.web.form.members.LoginMember;
import com.kh.board.web.form.members.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final MemberSVC memberSVC;

  // 로그인 화면
  @GetMapping("/login")
  public String loginForm() {
    return "signIn";
  }

  // 로그인 처리
  @PostMapping("/login")
  public String login(LoginForm loginForm, HttpServletRequest request,
                      @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {
    // 유효성 체크
    ////

    // 회원 유무 체크
    if (memberSVC.existEmail(loginForm.getEmail())) {
      Optional<Member> optionalMember = memberSVC.findByEmailAndPasswd(loginForm.getEmail(), loginForm.getPasswd());

      if (optionalMember.isPresent()) {
        HttpSession session = request.getSession(true);

        Member member = optionalMember.get();
        LoginMember loginMember = new LoginMember(
            member.getMemberId(),
            member.getEmail(),
            member.getNickname(),
            member.getGubun());
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
      } else {
        return "signIn";
      }

    } else {
      return "signIn";
    }
    return redirectUrl != null ? "redirect:"+redirectUrl : "redirect:/";
  }


  // 로그아웃
  @ResponseBody
  @PostMapping("/logout")
  public String logout(HttpServletRequest request) {

    String flag = "NOK";
    HttpSession session = request.getSession(false);

    if (session != null) {
      session.invalidate();
      flag = "OK";
    }
    return flag;
  }
}
