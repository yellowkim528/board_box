package com.kh.board.domain.member.dao;

import com.kh.board.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {

  private final NamedParameterJdbcTemplate template;

  //회원가입
  @Override
  public Long joinMember(Member member) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into member (member_id, email, passwd, nickname) ");
    sql.append(" values(member_member_id_seq.nextval, 'user1@kh.com', 'user1', '사용자1') ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(member);
    KeyHolder keyHolder = new GeneratedKeyHolder();

    template.update(sql.toString(), param, keyHolder, new String[]{"member_id"});
    Long memberId = ((BigDecimal) keyHolder.getKeys().get("member_id")).longValue();
    return memberId;
  }

  // 회원 이메일 조회(중복체크)
  @Override
  public boolean existEmail(String email) {
    String sql = "select count(email) from member where email = :email ";

    Map<String, String> param = Map.of("email", email);

    Integer cnt = template.queryForObject(sql, param, Integer.class);
    return cnt == 1 ? true : false;
  }

  // 회원 조회(로그인)
  @Override
  public Optional<Member> findByEmailAndPasswd(String email, String passwd) {
    StringBuffer sql = new StringBuffer();
    sql.append("select * from member where email = :email");
    sql.append("   and passwd = :passwd ");

    Map<String, String> param = Map.of("email", email, "passwd", passwd);

    try {
      Member member = template.queryForObject(sql.toString(), param, new BeanPropertyRowMapper<>(Member.class));
      return Optional.of(member);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }
}
