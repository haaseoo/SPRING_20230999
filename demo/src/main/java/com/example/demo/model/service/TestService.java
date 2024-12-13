package com.example.demo.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.TestDB;
import com.example.demo.model.repository.TestRepository;
import lombok.RequiredArgsConstructor;

@Service //  이 클래스가 서비스 계층임을 스프링에 알림, 스프링이 자동으로 빈으로 등록
@RequiredArgsConstructor // 생성자 생성 (Lombok이 자동으로 생성)
public class TestService {
  @Autowired // 스프링이 TestRepository의 구현체를 자동으로 주입
  private TestRepository testRepository;

  public TestDB findByName(String name) {
    return (TestDB) testRepository.findByName(name);
  }

  public List<TestDB> findAll() {
    return testRepository.findAll();
}
}