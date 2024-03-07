package com.kh.board.domain.member.svc;

import com.kh.board.domain.entity.Member;

import java.util.Optional;

public interface MemberSVC {
  //회원 가입
  Long joinMember(Member member);
  //회원 아이디 조회
  boolean existEmail(String email);

  //회원 조회
  Optional<Member> findByEmailAndPasswd(String email, String passwd);
}
