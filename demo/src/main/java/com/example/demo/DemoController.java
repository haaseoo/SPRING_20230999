package com.example.demo;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 어노테이션 명시
public class DemoController {

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
}