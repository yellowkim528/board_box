package com.kh.board.domain.member.svc;

import com.kh.board.domain.entity.Member;
import com.kh.board.domain.member.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberSVCImpl implements MemberSVC{

  @Autowired
  MemberDAO memberDAO;
  
  // 회원가입
  @Override
  public Long joinMember(Member member) {
    return memberDAO.joinMember(member);
  }

  @Override
  public boolean existEmail(String email) {
    return memberDAO.existEmail(email);
  }

  @Override
  public Optional<Member> findByEmailAndPasswd(String email, String passwd) {
    return memberDAO.findByEmailAndPasswd(email, passwd);
  }
}
