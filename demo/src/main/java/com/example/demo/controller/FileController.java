package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 이 컨트롤러 클래스는 파일 업로드와 관련된 요청을 처리
 사용자가 이메일, 제목, 메시지를 입력하여 텍스트 파일로 저장하는 기능을 제공
 */

@Controller
public class FileController {

    @Value("${spring.servlet.multipart.location}") // application.properties에서 업로드 경로 주입
    private String uploadFolder;

    // 사용자가 입력한 이메일, 제목, 메시지를 텍스트 파일로 저장, 유효성 검사를 통해 입력 데이트 검증, 파일 시스템에 안전하게 저장
    @PostMapping("/upload-email")
    public String uploadEmail(
        @RequestParam("email") String email,
        @RequestParam("subject") String subject,
        @RequestParam("message") String message,
        RedirectAttributes redirectAttributes) {

    try {
        // 입력 메시지 검증 : 메시지가 null이거나 공백인 경우 에러 처리
        if (message == null || message.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "메시지를 입력해주세요. 공백은 허용되지 않습니다.");
            return "redirect:/error_page/upload_error"; // 에러 처리 페이지로 리다이렉트
        }

        // 업로드 경로 설정 : application.properties에서 주입받은 업로드 폴더 경로를 절대 경로로 변환
        Path uploadPath = Paths.get(uploadFolder).toAbsolutePath();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // 업로드 폴더가 없으면 생성
        }

        // 파일명으로 사용할 이메일 주소를 안전하게 변환 : 이메일 주소에 포함될 수 없는 문자를 '_'로 대체
        String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");
        Path filePath = uploadPath.resolve(sanitizedEmail + ".txt"); // 기본 파일 경로 설정

        // 동일한 파일이 존재하는 경우 이름 변경 : 파일명에 숫자를 추가하여 중복 방지
        int count = 1;
        while (Files.exists(filePath)) {
            filePath = uploadPath.resolve(sanitizedEmail + "_" + count + ".txt");
            count++;
        }

        System.out.println("File path: " + filePath); // 디버깅용 출력

        // 파일에 데이터 쓰기 : 제목과 메시지를 텍스트 파일에 기록
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            writer.write("메일 제목: " + subject);
            writer.newLine(); // 줄 바꿈
            writer.write("요청 메시지:");
            writer.newLine();
            writer.write(message);
        }

        // 성공 메시지 설정 : 업로드 완료 후 사용자에게 알림 메시지 전달
        redirectAttributes.addFlashAttribute("message", "메일 내용이 성공적으로 업로드되었습니다! 파일 이름: " + filePath.getFileName());

    } catch (IOException e) {
        e.printStackTrace();
        // 오류 메시지 설정, 업로드 중 오류 발생 시 사용자에게 에러 메시지 전달
        redirectAttributes.addFlashAttribute("message", "업로드 중 오류가 발생했습니다: " + e.getMessage());
        return "redirect:/error_page/upload_error"; // 오류 처리 페이지로 리다이렉트
    }

    return "upload_end"; // 성공 시 업로드 완료 페이지로 리다이렉트
}

/*
 이 컨트롤러 클래스는 업로드 과정에서 발생한 오류 처리하고, 사용자에게 에러 페이지를 보여줌
 */
@Controller
public class ErrorPageController {

    // 업로드 오류 페이지 반환, 플래시 속성으로 전달된 메시지를 모델에 추가해 사용자에게 표시
    @GetMapping("/error_page/upload_error")
    public String showUploadErrorPage(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("message", message); // 전달된 메시지를 모델에 추가
        return "error_page/upload_error"; // 뷰 이름 반환 (resources/templates/error_page/upload_error.html)
    }
}


}
