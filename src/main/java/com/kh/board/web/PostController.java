package com.kh.board.web;

import com.kh.board.domain.entity.Post;
import com.kh.board.domain.post.svc.PostSVC;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/board")
public class PostController {

  private PostSVC postSVC;

  public PostController(PostSVC postSVC) {
    this.postSVC = postSVC;
  }
  @GetMapping("/write")       // Get, http://localhost:9090/board/write
  //글쓰기양식
  public String writeForm(){
    return "board/write";     // view경로로 이동. 글쓰기화면
  }

  //글쓰기처리
  @PostMapping("/write")
  public String write(
      @RequestParam("postTitle") String postTitle,
      @RequestParam("writer") String writer,
      @RequestParam("postBody") String postBody,
      RedirectAttributes redirectAttributes
  ) {
    //글쓰기
    Post post = new Post();
    post.setPostTitle(postTitle);
    post.setWriter(writer);
    post.setPostBody(postBody);

    Long postId = postSVC.save(post);
    redirectAttributes.addAttribute("pid", postId);

    return "redirect:/board/{pid}/detail";
  }

  //조회
  @GetMapping("/{pid}/detail")
  public String findById(@PathVariable("pid") Long postId, Model model) {

    Optional<Post> findedPost = postSVC.findById(postId);
    Post post = findedPost.orElseThrow();
    model.addAttribute("post", post);

    return "board/body";
  }

  //수정 화면
  @GetMapping("/{pid}/edit")
  public String updateForm(
      @PathVariable("pid") Long postId,
      Model model){

    Optional<Post> optionalPost = postSVC.findById(postId);
    Post findedPost = optionalPost.orElseThrow();

    model.addAttribute("post",findedPost);
    return "board/update";
  }
  //수정 처리
  @PostMapping("/{pid}/edit")
  public String update(
      @PathVariable("pid") Long postId,
      @RequestParam("postTitle") String postTitle,
      @RequestParam("postBody") String postBody,
      @RequestParam("writer") String writer,
      RedirectAttributes redirectAttributes) {

    Post post = new Post();
    post.setPostTitle(postTitle);
    post.setPostBody(postBody);
    post.setWriter(writer);
    int updateRowCnt = postSVC.updateById(postId, post);
    redirectAttributes.addAttribute("pid", postId);

    return "redirect:/board/{pid}/detail";
  }

  //삭제
  @GetMapping("/{pid}/del")
  public String deleteById(@PathVariable("pid") Long postId) {
    int deleteRowCnt = postSVC.deleteById(postId);
    return "redirect:/board";
  }

  //목록
  @GetMapping
  public String findAll(Model model) {
    List<Post> list = postSVC.findAll();
    model.addAttribute("list", list);

    return "board/all";
  }


}