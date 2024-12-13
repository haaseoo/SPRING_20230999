package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity // JPA 엔티티임을 지정, JPA가 이 클래스를 관리하며, TestDB라는 객체와 데이터베이스 테이블과 매핑
@Table(name = "testdb") // 엔티티가 매핑될 데이터베이스의 테이블 이름을 'testdb'로 지정
@Data // Lombok 어노테이션으로, 클래스의 모든 필드에 대한 getter, setter, toString, equals, hashCode 메서드를 자동으로 생성

public class TestDB {
  @Id // 이 필드가 데이터베이스 테이블의 기본 키(PK)임을 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 값이 없어도 데이터베이스가 자동으로 키 값 생성, 할당

  private Long id;
  
  @Column(nullable = true) // 테이블의 컬럼 설정 값을 명시 (데이터베이스 테이블의 컬럼과 매핑됨을 지정), null 값 허용함
  private String name;
}