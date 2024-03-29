package com.kh.board.web;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/test/reply")
public class TestController {

  @GetMapping
  public String bbsView() {
    return "/board/testbody";
  }
}
