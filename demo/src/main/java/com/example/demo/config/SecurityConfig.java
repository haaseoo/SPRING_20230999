package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 설정 클래스임을 나타냄
@EnableWebSecurity // 스프링 웹 보안 활성화
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // XSS 보호 헤더 설정
            .headers(headers -> headers
                .addHeaderWriter((request, response) -> {
                    response.setHeader("X-XSS-Protection", "1; mode=block");
                })
            )
            
            // CSRF 비활성화
            .csrf(csrf -> csrf.disable())
            
            // 세션 관리 설정
            .sessionManagement(session -> session
                .invalidSessionUrl("/session-expired") // 세션이 무호화 됐을 때 리다이렉트 할 url
                .maximumSessions(1) // 한 사용자당 허용되는 최대 세션 수
                .maxSessionsPreventsLogin(true) // 최대 세션 수 초과시 새로운 로그인 막음
            );

        return http.build(); // 설정을 기반으로 SecurityFilterChain 빌드
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt를 사용한 비밀번호 인코더 반환
    }
}
