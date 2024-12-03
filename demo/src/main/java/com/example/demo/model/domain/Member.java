package com.example.demo.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor  // 기본 생성자 필요 (JPA에서 필요)

public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키를 자동 증가
  @Column(name = "id", updatable = false) // 컬럼 설정, 수정 불가
  private Long id;
  
  @Column(name = "name", nullable = false) // 컬럼 설정, null 허용하지 않음
  private String name = "";
  
  @Column(name = "email", unique = true, nullable = false) // 유일성 보장, null 허용하지 않음
  private String email = "";
  
  @Column(name = "password", nullable = false) // null 허용하지 않음
  private String password = "";
  
  @Column(name = "age", nullable = false) // null 허용하지 않음
  private String age = "";
  
  @Column(name = "mobile", nullable = false) // null 허용하지 않음
  private String mobile = "";
  
  @Column(name = "address", nullable = false) // null 허용하지 않음
  private String address = "";
  
  @Builder // Builder 패턴을 사용해 객체 생성
  public Member(String name, String email, String password, String age, String mobile, String address) {
      this.name = name;
      this.email = email;
      this.password = password;
      this.age = age;
      this.mobile = mobile;
      this.address = address;
  }
}
