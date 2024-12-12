package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;

import jakarta.servlet.http.HttpSession;

import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board;
import org.springframework.data.domain.Pageable;


@Controller
@Configuration
@ControllerAdvice
public class BlogController {

    @Autowired
    BlogService blogService; // 서비스 계층 의존성 주입

    // @GetMapping("/article_list") // 게시판 링크 지정
    // public String articleList(Model model) {
    //     List<Article> list = blogService.findAll(); // 게시판 리스트 조회
    //     model.addAttribute("articles", list); // 모델에 게시글 목록 추가
    //     // return "redirect:/article_list"; // article_list.html 템플릿 반환
    //     return "article_list";
    // }
    
    // 게시판 수정
    // @PutMapping("/api/article_edit/{id}")
    // public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
    //     blogService.update(id, request);
    //     return "redirect:/article_list"; // 글 수정 이후 .html 연결
    // }

    // @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    // public String article_edit(Model model, @PathVariable Long id) {
    //     Optional<Article> list = blogService.findById(id); // 선택한 게시판 글

    //     if (list.isPresent()) {
    //         model.addAttribute("article", list.get()); // 존재하면 Article 객체를 모델에 추가
    //     } else {
    //         // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
    //         return "/error_page/article_error"; // 오류 처리 페이지로 연결
    // }
    // return "article_edit"; // .HTML 연결
    // }

    // 게시판 삭제
    // @DeleteMapping("/api/article_delete/{id}")
    // public String article_delete(@PathVariable Long id) {
    //     blogService.delete(id);
    //     return "redirect:/article_list";
    // }

    
    // * board *
    @GetMapping("/board_list")
    public String boardList(Model model, 
                            @RequestParam(defaultValue = "0") int page, 
                            @RequestParam(defaultValue = "") String keyword, 
                            HttpSession session) {
        // 세션에서 사용자 ID 및 이메일 확인
        String userId = (String) session.getAttribute("userId");
        String uEmail = (String) session.getAttribute("email");
        
        if (userId == null || uEmail == null) {
            return "redirect:/login"; // 로그인되지 않은 사용자 처리
        }
    
        int pageSize = 3;  // 한 페이지의 게시글 수
        PageRequest pageable = PageRequest.of(page, pageSize);
    
        // 키워드 유무에 따라 전체 조회 또는 키워드 검색 수행
        Page<Board> list = keyword.isEmpty()
                ? blogService.findAll(pageable)
                : blogService.searchByKeyword(keyword, pageable);
        
        // 시작 번호 계산
        int startNum = (page * pageSize) + 1;
        
        // 모델에 데이터 추가
        model.addAttribute("boards", list);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("startNum", startNum);
        model.addAttribute("email", uEmail);  // 이메일을 uEmail로 변경
        model.addAttribute("keyword", keyword);
    
        return "board_list";  // board_list.html 템플릿 반환
    }

    @GetMapping("/board_list/basic")
    public String boardListBasic(Model model) {
        List<Board> list = blogService.findAll();
        model.addAttribute("boards", list);
        return "board_list";
    }
    
    @GetMapping("/board_view/{id}")
public String boardView(@PathVariable String id, HttpSession session, Model model) {
    try {
        Long longId = Long.parseLong(id); // 문자열을 Long으로 변환
        Board board = blogService.findById(longId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID: " + longId));
        
        // 로그인한 사용자 이메일 가져오기
        String loggedInUser = (String) session.getAttribute("email");
        System.out.println("[DEBUG] 현재 로그인한 사용자: " + loggedInUser);

        // 모델에 게시글과 로그인 사용자 정보 추가
        model.addAttribute("boards", board);
        model.addAttribute("loggedInUser", loggedInUser); // 현재 로그인한 사용자 추가
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("ID는 숫자여야 합니다.");
    }
    return "board_view";
}
    
    @GetMapping("/board_edit/{id}")
    public String editBoard(@PathVariable Long id, Model model) {
        // 예외 처리: 문자열로 id를 메시지와 함께 전달
        Board board = blogService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID: " + id));
    
        model.addAttribute("board", board);
        return "board_edit";
    }

    @PutMapping("/api/board_edit/{id}") // 게시글 수정 요청
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request); // 수정 로직 실행
        return "redirect:/board_list"; // 수정 후 게시판 목록 페이지로 리다이렉트
    }

    @PostMapping("/api/boards") // 새로운 게시글 추가 요청
    public String addBoard(@ModelAttribute AddArticleRequest request) {
        blogService.save(request); // 새로운 게시글 저장
        return "redirect:/board_list"; // 저장 후 게시판 목록 페이지로 리다이렉트
    }

    @GetMapping("/board_write")
public String boardWrite(HttpSession session, Model model) {
    // 세션에서 이메일 정보 가져오기
    String email = (String) session.getAttribute("email");
    System.out.println("[DEBUG] 로그인된 사용자 이메일: " + email);

    // 이메일이 없는 경우 기본값 설정
    if (email == null || email.isEmpty()) {
        email = "guest@example.com"; // 기본값
    }

    // 모델에 이메일 추가
    model.addAttribute("email", email);
    return "board_write";
}

    // 게시판 삭제
    @DeleteMapping("/api/board_delete/{id}")
    public String board_delete(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/board_list";
    }

    // * 문자열 에러 (6주차 추가 구현) *
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

     // IllegalArgumentException 발생 시 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_page/article_error"; // 오류 페이지로 이동
    }

    // MethodArgumentTypeMismatchException 발생 시 처리 (잘못된 타입의 PathVariable 처리)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("errorMessage", "잘못된 요청입니다. ID는 숫자여야 합니다.");
        return "error_page/article_error2"; // 오류 페이지로 이동
    }

    
}