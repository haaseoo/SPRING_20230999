package com.example.demo.model.domain;

import lombok.*; // 어노테이션 자동 생성
import jakarta.persistence.*; // 기존 javax 후속 버전

@Getter // 모든 필드에 대한 getter 메서드를 생성, setter는 생성하지 않아 무분별한 변경을 방지
@Entity // 이 클래스가 JPA 엔티티임을 지정, JPA가 이 클래스를 관리하며, 데이터베이스 테이블과 매핑
@Table(name = "article") // 테이블 이름을 지정. 없는 경우 클래스이름으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부 생성자 접근 방지

public class Article {
  @Id // 기본 키
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 1씩 증가
  
  @Column(name = "id", updatable = false) // 수정 x
  private Long id;
  @Column(name = "title", nullable = false) // null x
  private String title = "";
  @Column(name = "content", nullable = false)
  private String content = "";

  @Builder // 생성자에 빌더 패턴 적용 (불변성), 객체 생성 시 가독성을 
  public Article(String title, String content){
    this.title = title;
    this.content = content;
  }

  public void update(String title, String content) { // 현재 객체 상태 업데이트, 이 메서드를 이용해 제목과 내용 변경 가능
    this.title = title;
    this.content = content;
    }
}