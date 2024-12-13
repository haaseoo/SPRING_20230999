package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;

import com.example.demo.model.service.AddMemberRequest;
import com.example.demo.model.service.MemberService;
import com.example.demo.model.domain.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.servlet.http.Cookie;  // Cookie 클래스를 import
import java.util.UUID;

@Controller
public class MemberController {

  private final MemberService memberService;
  // 생성자 주입을 통한 MemberService 의존성 주입, spring @Autowired 어노테이션을 사용하여 MemberService 빈을 주입받음, 테스트 용이성과 의존성 관리에 유리
  @Autowired
  public MemberController(MemberService memberService) {
      this.memberService = memberService;
  }

  @GetMapping("/join_new") // 회원 가입 페이지 연결
  public String join_new() {
    return "join_new"; // .HTML 연결
  }

  // 회원가입 요청 처리, 유효성 검사를 통해 입력 데이터를 검증한 후 회원 저장
  @PostMapping("/api/members")
    public String addMembers(@Valid @ModelAttribute AddMemberRequest request) {
        memberService.saveMember(request); // 요청 데이터를 저장
        return "join_end"; // join_end.html 템플릿 반환
    }

  @GetMapping("/login") // 로그인 페이지 연결
    public String member_login() {
        return "login"; // .HTML 연결
    }

  @PostMapping("/api/login_check") // 로그인 검증 수행, 세션 관리 및 로그인 성공시 리다이렉트 처리
    public String checkMembers(@ModelAttribute AddMemberRequest request, Model model, HttpServletRequest request2, HttpServletResponse response) {
    try {
        // 1. 기존 세션이 있으면 무효화
        HttpSession session = request2.getSession(false); // 기존 세션 가져오기 (없으면 null 반환)
        if (session != null) {
            session.invalidate(); // 기존 세션 무효화

            // JSESSIONID 쿠키 초기화 및 삭제
            Cookie cookie = new Cookie("JSESSIONID", null); // JSESSIONID 쿠키 초기화
            cookie.setPath("/"); // 쿠키 경로 설정
            cookie.setMaxAge(0); // 쿠키 삭제 (만료시간 0으로 설정)
            response.addCookie(cookie); // 응답으로 쿠키 전달
        }

        // 2. 새로운 세션 생성
        session = request2.getSession(true); // 새로운 세션 생성

        // 3. 이메일과 패스워드를 기반으로 로그인 체크
        Member member = memberService.loginCheck(request.getEmail(), request.getPassword());

        // 4. 세션에 고유 ID와 이메일 저장
        String sessionId = UUID.randomUUID().toString(); // 고유 ID 생성
        session.setAttribute("userId", sessionId); // 임의의 고유 ID 세션에 저장
        session.setAttribute("email", request.getEmail()); // 이메일을 세션에 저장

        // 5. 세션 정보 로그 출력
        System.out.println("Session ID: " + session.getId());
        System.out.println("User Email: " + request.getEmail());

        // 6. 로그인 성공 시 회원 정보를 모델에 추가
        model.addAttribute("member", member);

        // 7. 로그인 성공 후 게시판 리스트 페이지로 리다이렉트
        return "redirect:/board_list";
        
    } catch (IllegalArgumentException e) {
        // 로그인 실패 시 에러 메시지를 모델에 추가
        model.addAttribute("error", e.getMessage());

        // 로그인 실패 시 로그인 페이지로 리다이렉트
        return "login";
    }
}

@GetMapping("/api/logout") // 로그아웃 버튼 동작
public String memberLogout(Model model, HttpServletRequest request, HttpServletResponse response) {
    try {
        // 기존 세션 가져오기 (존재하지 않으면 null 반환)
        HttpSession session = request.getSession(false);

        // 세션이 존재하면 처리
        if (session != null) {
            session.invalidate(); // 기존 세션 무효화

            // JSESSIONID 쿠키 삭제
            Cookie cookie = new Cookie("JSESSIONID", null); // JSESSIONID 쿠키 생성
            cookie.setPath("/"); // 쿠키 경로 설정
            cookie.setMaxAge(0); // 쿠키 만료 시간 0으로 설정하여 삭제
            response.addCookie(cookie); // 쿠키 응답에 추가
        }

        // 새로운 세션 생성
        session = request.getSession(true);

        // 세션 정보 출력 (디버그용)
        System.out.println("세션 userId: " + session.getAttribute("userId"));

        return "login"; // 로그인 페이지로 리다이렉트
    } catch (IllegalArgumentException e) {
        // 로그아웃 실패 시 에러 메시지를 모델에 추가
        model.addAttribute("error", e.getMessage());

        return "login"; // 로그인 실패 시 로그인 페이지로 리다이렉트
    }
}
}
