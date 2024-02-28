package com.kh.board.domain.reply.dao;

import com.kh.board.domain.entity.Post;
import com.kh.board.domain.entity.Reply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class ReplyDAOImpl implements ReplyDAO{

  private final NamedParameterJdbcTemplate template;

  public ReplyDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public List<Reply> finaAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select reply_id, reply_body, reply_writer, post_id, cdate, udate ");
    sql.append("  from reply ");

    List<Reply> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Reply.class));

    return list;
  }
}
