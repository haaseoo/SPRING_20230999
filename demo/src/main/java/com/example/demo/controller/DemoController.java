package com.example.demo.controller;


import com.example.demo.model.service.TestService;
import com.example.demo.model.domain.TestDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.util.List;

@Controller // 컨트롤러 어노테이션 명시
public class DemoController {

  @Autowired
  TestService testService;

  @GetMapping("/hello") // 전송 방식 GET
  public String hello(Model model) {
    model.addAttribute("data", " 방갑습니다."); // model 설정
    return "hello"; // hello.html 연결
  }

  @GetMapping("/hello2") // 전송 방식 GET
  public String hello2(Model model) {
    LocalDate today = LocalDate.now();
    model.addAttribute("data1", "안하서님 방갑습니다."); // model 설정
    model.addAttribute("data2", "오늘 날짜 : " + today.toString()); // model 설정
    model.addAttribute("data3", "오늘의 날씨도 매우 좋습니다"); // model 설정
    model.addAttribute("data4", "좋은하루 되세요"); // model 설정
    model.addAttribute("data5", "😊"); // model 설정
    return "hello2"; // hello.html 연결
  }

  @GetMapping("/about_detailed") // 전송 방식 GET
  public String about_detailed(Model model) {
    return "about_detailed"; // hello.html 연결
  }

  @GetMapping("/test1")
  public String thymeleaf_test1(Model model) {
    model.addAttribute("data1", "<h2> 방갑습니다 </h2>");
    model.addAttribute("data2", "태그의 속성 값");
    model.addAttribute("link", 01);
    model.addAttribute("name", "홍길동");
    model.addAttribute("para1", "001");
    model.addAttribute("para2", 002);
    return "thymeleaf_test1";
  }

  @GetMapping("/testdb")
  public String getAllTestDBs(Model model) {
    List<TestDB> users = testService.findAll();
    model.addAttribute("users", users);
    // System.out.println("데이터 출력 디버그 : " + users);
        return "testdb";
    }
}