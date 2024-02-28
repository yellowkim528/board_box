package com.kh.board.domain.reply.svc;

import com.kh.board.domain.entity.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReplySVC {
  //목록
  List<Reply> findAll();
}
