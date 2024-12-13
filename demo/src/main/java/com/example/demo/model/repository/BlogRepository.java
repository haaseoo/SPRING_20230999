package com.example.demo.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long>{
  Page<Board> findByTitleContainingIgnoreCase(String title, Pageable pageable);
  // 제목에 특정 키워드가 포함된 Board 엔티티들을 대소문자 구분 없이 페이징하여 조회하는 메서드

  Board save(Board entity);
  // Board 엔티티를 저장하는 메서드, 
}