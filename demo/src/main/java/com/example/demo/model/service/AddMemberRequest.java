package com.example.demo.model.service; // 패키지 경로를 상황에 맞게 수정

import com.example.demo.model.domain.Member;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // 모든 필드에 대한 getter 메서드 생성
@Setter // 모든 필드에 대한 setter 메서드 생성
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성
@Builder // 빌더 패턴을 적용하여 객체 생성 유연성 제공
public class AddMemberRequest {

    @NotBlank(message = "이름은 공백일 수 없습니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 특수문자를 포함할 수 없습니다.")
    private String name;

    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String email;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$", message = "비밀번호는 8자 이상, 대문자, 소문자 및 숫자를 포함해야 합니다.")
    private String password;

    @NotNull(message = "나이는 필수 입력값입니다.")
    @Min(value = 19, message = "나이는 19세 이상이어야 합니다.")
    @Max(value = 90, message = "나이는 90세 이하이어야 합니다.")
    private Integer age;

    @NotBlank(message = "전화번호는 공백일 수 없습니다.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "전화번호는 숫자만 입력하며, 10~11자리여야 합니다.")
    private String mobile;

    @NotBlank(message = "주소는 공백일 수 없습니다.")
    private String address;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .age(String.valueOf(age)) // age를 String으로 변환
                .mobile(mobile)
                .address(address)
                .build();
    }
}