package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.repository.BlogRepository;
import com.example.demo.model.domain.Board;
import com.example.demo.model.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;

@Service // 서비스 계층임을 명시하여 Spring이 관리할 수 있도록 함
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동 생성

public class BlogService {
    @Autowired // 객체 주입 자동화

    // * article *
    private final BlogRepository blogRepository; // 리포지토리 의존성 주입
    private final BoardRepository boardRepository; // Board 관련 리포지토리

    // article 게시판 전체 목록 조회
    // public List<Article> findAll() {
    //   return blogRepository.findAll();
    // }
    
    // public Article save(AddArticleRequest request) {
    //   // dto가 없을 경우 직접 이 공간에 구현 가능
    //   return blogRepository.save(request.toEntity());
    // }
    
    // public Optional<Article> findById(Long id) {
    //   return blogRepository.findById(id);
    // }
    
    // public void update(Long id, AddArticleRequest request) {
    //   Optional<Article> optionalArticle = blogRepository.findById(id); // 단일 글 조회
    //   optionalArticle.ifPresent(article -> { // 값이 있으면
    //     article.update(request.getTitle(), request.getContent()); // 값을 수정
    //     blogRepository.save(article); // Article 객체에 저장
    //   });
    // }
    
    // 삭제 기능
    public void delete(Long id) {
      boardRepository.deleteById(id);
    }

    public List<Board> findAll() { // 게시판 전체 목록 조회
      return boardRepository.findAll();
    }
    
    public Board save(AddArticleRequest request){
      return blogRepository.save(request.toEntity());  // 게시글 저장 및 반환
    }

    public Optional<Board> findById(Long id) { // 게시판 특정 글 조회, ID로 게시글 조회
      return boardRepository.findById(id);
    }
    
    public void update(Long id, AddArticleRequest request) {
      Optional<Board> optionalBoard = boardRepository.findById(id); // 단일 글 조회
      optionalBoard.ifPresent(board -> { // 값이 있으면 (게시글이 있으면)
        board.update(request.getTitle(), request.getContent(), board.getUser(), board.getNewdate(), board.getCount(), board.getLikec());
        boardRepository.save(board); // Board 객체에 저장, 게시글 정보 업데이트, 업데이트 된 게시글 저장
      });
    }
    
    // 기본 게시글 목록, 키워드 검색 결과 기능 구현
    public Page<Board> findAll(Pageable pageable) {
      return boardRepository.findAll(pageable); // 페이징된 게시글 조회
      }

    public Page<Board> searchByKeyword(String keyword, Pageable pageable) {
      return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
      } // LIKE 검색 제공 (대소문자 무시)


  }