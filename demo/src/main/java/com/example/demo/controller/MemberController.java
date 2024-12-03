package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import com.example.demo.model.service.AddMemberRequest;
import com.example.demo.model.service.MemberService;
import com.example.demo.model.domain.Member;

@Controller
public class MemberController {

  private final MemberService memberService;
  // 생성자 주입을 통한 MemberService 의존성 주입
  // @Autowired
  public MemberController(MemberService memberService) {
      this.memberService = memberService;
  }

  @GetMapping("/join_new") // 회원 가입 페이지 연결
  public String join_new() {
    return "join_new"; // .HTML 연결
  }

  // 회원가입 요청 처리
  @PostMapping("/api/members") // 회원 가입 저장
  public String addmembers(@ModelAttribute AddMemberRequest request) {
    memberService.saveMember(request);
    return "join_end"; // .HTML 연결
  }

  @GetMapping("/login") // 로그인 페이지 연결
  public String member_login() {
    return "login"; // .HTML 연결
  }

  @PostMapping("/api/login_check") // 로그인(아이디, 패스워드) 체크
  public String checkMembers(@ModelAttribute AddMemberRequest request, Model model) {
    try {
      // 이메일과 패스워드를 기반으로 로그인 체크
      Member member = memberService.loginCheck(request.getEmail(), request.getPassword());
      
      // 로그인 성공 시 회원 정보를 모델에 추가
      model.addAttribute("member", member);
      
      // 로그인 성공 후 게시판 리스트 페이지로 리다이렉트
      return "redirect:/board_list";
    } catch (IllegalArgumentException e) {
      // 로그인 실패 시 에러 메시지를 모델에 추가
      model.addAttribute("error", e.getMessage());
      // 로그인 실패 시 로그인 페이지로 리다이렉트
      return "login";
    }
  }
}
