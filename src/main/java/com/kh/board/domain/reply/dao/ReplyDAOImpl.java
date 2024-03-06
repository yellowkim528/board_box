package com.kh.board.domain.reply.dao;

import com.kh.board.domain.entity.Reply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Repository
public class ReplyDAOImpl implements ReplyDAO{

  private final NamedParameterJdbcTemplate template;

  public ReplyDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  // 댓글 목록
  @Override
  public List<Reply> finaAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select reply_id, reply_body, reply_writer, post_id, cdate, udate ");
    sql.append("  from reply ");

    List<Reply> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Reply.class));

    return list;
  }

  // 댓글 작성
  @Override
  public Long addReply(Reply reply) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into reply(reply_id, reply_body, reply_writer, post_id) ");
    sql.append(" values(reply_reply_id_seq.nextval, :replyBody, :replyWriter, :postId) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(reply);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(), param, keyHolder, new String[]{"reply_id", "reply_body", "reply_writer", "post_id"});
    Long reply_id = ((BigDecimal) keyHolder.getKeys().get("reply_id")).longValue();

    return reply_id;
  }

  // 댓글 삭제
  @Override
  public int deleteById(Long replyId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" delete from reply ");
    sql.append("  where reply_id = :replyId ");

    SqlParameterSource param = new MapSqlParameterSource().addValue("replyId", replyId);
    int deleteCnt = template.update(sql.toString(), param);

    return deleteCnt;
  }

  // 댓글 수정
  @Override
  public int updateById(Long replyId, Reply reply) {
    StringBuffer sql = new StringBuffer();
    sql.append(" update reply ");
    sql.append("    set reply_body = :replyBody, ");
    sql.append("        udate = default ");
    sql.append("  where reply_id = :replyId ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("replyBody", reply.getReplyBody())
        .addValue("replyId", replyId);

    int updateCnt = template.update(sql.toString(), param);

    return updateCnt;
  }
}
