package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Article;
import com.example.demo.model.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
// import java.util.Optional;

@Service // 서비스 계층임을 명시하여 Spring이 관리할 수 있도록 함
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동 생성

public class BlogService {
    @Autowired // 객체 주입 자동화
    private final BlogRepository blogRepository; // 리포지토리 의존성 주입

    // 게시판 전체 목록 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article save(AddArticleRequest request) {
      // dto가 없을 경우 직접 이 공간에 구현 가능
        return blogRepository.save(request.toEntity());
    }

    public Optional<Article> findById(Long id) {
      return blogRepository.findById(id);
    }

    public void update(Long id, AddArticleRequest request) {
      Optional<Article> optionalArticle = blogRepository.findById(id); // 단일 글 조회
      optionalArticle.ifPresent(article -> { // 값이 있으면
        article.update(request.getTitle(), request.getContent()); // 값을 수정
        blogRepository.save(article); // Article 객체에 저장
      });
    }

    // 삭제 기능
    public void delete(Long id) {
      blogRepository.deleteById(id);
    }
}