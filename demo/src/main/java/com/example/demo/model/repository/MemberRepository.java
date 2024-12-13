package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    // 이메일로 member 엔티티 조회하는 메소드, 해당 이메일을 가진 member 엔티티 객체가 업을 경우 null 반환
}