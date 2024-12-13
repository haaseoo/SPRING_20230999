package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository; // JPA 리포지토리 인터페이스
import org.springframework.stereotype.Repository;
import com.example.demo.model.domain.TestDB; // TestDB 도메인 클래스

@Repository
public interface TestRepository extends JpaRepository<TestDB, Long> {
  // Find all TestDB entities by a name
  TestDB findByName(String name); 
  // 이름으로 TestDB 엔티티 조회하는 메서드
}