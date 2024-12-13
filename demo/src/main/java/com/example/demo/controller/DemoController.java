package com.example.demo.controller;

import com.example.demo.model.service.TestService;
import com.example.demo.model.domain.TestDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.util.List;

/*
 이 컨트롤러 클래스는 다양한 페이지 요청을 처리하며, 
 테스트 데이터베이스 조회 및 간단한 템플릿 렌더링 기능을 제공
 */
@Controller // 컨트롤러 어노테이션 명시
public class DemoController {

  @Autowired
  TestService testService;

  // GET 요청 처리하여 페이지 반환, 사용자가 /hello 로 접근할 때 호출됨
  @GetMapping("/hello")
  public String hello(Model model) {
    model.addAttribute("data", " 방갑습니다."); // model 에 'data' 속성 추가
    return "hello"; // hello.html 연결
  }

    // GET 요청 처리하여 페이지 반환, 사용자가 /hello2 로 접근할 때 호출됨
  @GetMapping("/hello2")
  public String hello2(Model model) {
    LocalDate today = LocalDate.now(); // 현재 날짜 가져옴
    model.addAttribute("data1", "안하서님 방갑습니다."); // 모델에 data1 속성 추가
    model.addAttribute("data2", "오늘 날짜 : " + today.toString()); // 모델에 data2 속성 추가
    model.addAttribute("data3", "오늘의 날씨도 매우 좋습니다"); // 모델에 data3 속성 추가
    model.addAttribute("data4", "좋은하루 되세요"); // model 설정
    model.addAttribute("data5", "😊"); // model 설정
    return "hello2"; // hello.html 연결
  }

  // GET 요청 처리하여 페이지 반환, 사용자가 /about_detailed 로 접근할 때 호출됨
  @GetMapping("/about_detailed") // 전송 방식 GET
  public String about_detailed(Model model) {
    return "about_detailed"; // hello.html 연결
  }

  // GET 요청 처리하여 페이지 반환, 사용자가 thymeleaf_test1 로 접근할 때 호출됨
  @GetMapping("/test1")
  public String thymeleaf_test1(Model model) {
    model.addAttribute("data1", "<h2> 방갑습니다 </h2>"); // HTML 태그를 포함한 'data1' 속성 추가
    model.addAttribute("data2", "태그의 속성 값"); // 'data2' 속성 추가
    model.addAttribute("link", 01); // 'link' 속성 추가 (정수형)
    model.addAttribute("name", "홍길동"); // 'name' 속성 추가
    model.addAttribute("para1", "001"); // 'para1' 속성 추가
    model.addAttribute("para2", 002); // 'para2' 속성 추가 (정수형)
    return "thymeleaf_test1"; // 'thymeleaf_test1.html' 템플릿으로 이동
  }

  // Get 요청을 처리하여 데이터베이스에 저장된 모든 TestDB 엔티티 조회, 이를 testdb 페이지에 전달하여 렌더링, 클라이언트가 /testdb url로 접근할 때 호출됩니다.
  @GetMapping("/testdb")
  public String getAllTestDBs(Model model) {
    List<TestDB> users = testService.findAll(); // 모든 TestDB 엔티티를 조회
    model.addAttribute("users", users); // 모델에 'users' 속성 추가
    // System.out.println("데이터 출력 디버그 : " + users);
        return "testdb";
    }

    // @GetMapping("/article_list")
    // public String article_list() {
    //   return "article_list";
    // }
}