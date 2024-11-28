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
  Board save(Board entity);
}