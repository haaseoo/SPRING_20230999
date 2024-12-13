package com.example.demo.model.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.model.domain.Member;
import com.example.demo.model.service.AddMemberRequest;
import com.example.demo.model.repository.MemberRepository;
import jakarta.validation.Valid; // Spring Boot 3.0 이상 (JDK 17 이상)
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;

@Service // 이 클래스가 서비스 계층임을 스프링에 알림, 스프링이 자동으로 빈으로 등록
@Validated // 메서드 파라미터 검증을 활성화
@Transactional // 클래스 내 모든 메서드에 트랜잭션을 적용
@RequiredArgsConstructor // Lombok이 final 필드에 대한 생성자를 자동으로 생성

public class MemberService {
    private final MemberRepository memberRepository; // 회원 정보를 관리하는 리포지토리
    private final PasswordEncoder passwordEncoder; // 비밀번호 인코더
    private void validateDuplicateMember(AddMemberRequest request) {
        Member findMember = memberRepository.findByEmail(request.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    
    public Member saveMember(@Valid AddMemberRequest request) {
        validateDuplicateMember(request);  // 중복 회원 검증
        String encodedPassword = passwordEncoder.encode(request.getPassword()); // 비밀번호 암호화
        request.setPassword(encodedPassword); // 암호화된 비밀번호 설정
        return memberRepository.save(request.toEntity()); // 회원 저장 및 반환
    }

    public Member loginCheck(String email, String rawPassword) {
        Member member = memberRepository.findByEmail(email); // 이메일 조회
    
        // 회원이 존재하지 않을 경우 예외
        if (member == null) {
            throw new IllegalArgumentException("등록되지 않은 이메일입니다.");
        }
    
        // 입력된 비밀번호와 저장된 비밀번호가 일치하지 않을 경우 예외
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    
        // 인증 성공 시 회원 객체 반환
        return member;
    }
}