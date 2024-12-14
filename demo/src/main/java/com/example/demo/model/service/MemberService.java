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

// 서비스 계층은 주로 비즈니스 로직을 담당
@Service // 이 클래스가 서비스 계층임을 스프링에 알림, 스프링이 자동으로 빈으로 등록
@Validated // 메서드 파라미터 검증을 활성화
@Transactional // 클래스 내 모든 메서드에 트랜잭션을 적용
@RequiredArgsConstructor // Lombok이 final 필드에 대한 생성자를 자동으로 생성

public class MemberService {
    private final MemberRepository memberRepository; // 회원 정보를 데이터베이스에 저장하고 조회하는 리포지토리
    private final PasswordEncoder passwordEncoder; // 비밀번호를 암호화하거나 비교하는 데 사용되는 비밀번호 인코더

    // 중복된 이메일이 있는지 확인하는 메서드
    private void validateDuplicateMember(AddMemberRequest request) {
        // 입력된 이메일로 회원을 조회
        Member findMember = memberRepository.findByEmail(request.getEmail());

        // 회원이 이미 존재하면 예외를 발생
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    
    // 회원을 저장하는 메서드
    public Member saveMember(@Valid AddMemberRequest request) {
        // 회원 가입 전에 중복된 이메일이 있는지 확인
        validateDuplicateMember(request); 

        // 비밀번호를 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 암호화된 비밀번호를 요청 객체에 설정
        request.setPassword(encodedPassword); 

        // 요청 객체를 엔티티로 변환하여 데이터베이스에 저장하고, 저장된 회원 정보를 반환
        return memberRepository.save(request.toEntity()); 
    }

    // 로그인 체크를 하는 메서드
    public Member loginCheck(String email, String rawPassword) {
        // 이메일로 회원을 조회
        Member member = memberRepository.findByEmail(email);
    
        // 회원이 존재하지 않으면 예외를 발생
        if (member == null) {
            throw new IllegalArgumentException("등록되지 않은 이메일입니다.");
        }
    
        // 입력된 비밀번호와 저장된 비밀번호가 일치하는지 비교합
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    
        // 비밀번호가 맞으면 회원 객체를 반환하여 로그인 성공
        return member;
    }
}