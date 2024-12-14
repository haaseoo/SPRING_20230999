## ❷ week

- 수업내용

  - vs code 스프링 부트 설정, 기본 화면 실행, 깃허브 연동
  - Thymeleaf 문법의 선언, 주석, 텍스트 출력

    > [!NOTE]<br>
    > 선언 : < html lang="en" xmlns:th="http://www.thymeleaf.org" > <br>
    > 주석 : < !--/_ This code will be removed at thymeleaf parsing time! _/-- > <br>
    > 텍스트 출력 : 변수 표현식, <sapn th:text=${data}"><br>
    > 참고 : https://www.thymeleaf.org/

  - url 맵핑, 컨트롤러 : templates 파일 수정, html 생성, 컨트롤러에 등록

- 연습문제

  - url 맵핑과 컨트롤러 추가 : hello2 맵핑, 5개 속성 출력하기<br>
    <p align="center">
    <img width="150" alt="2w 추가1" src="https://github.com/user-attachments/assets/decb9724-5423-459f-886f-3c52a2dff45b" /> <br></p>

  - Local Date 를 통한 날짜 추가 출력<br>
    <p align="center">
    <img height="200" alt="2w 추가2" src="https://github.com/user-attachments/assets/6054cfb3-c0ce-4dd6-acf7-64e53103321f" /></p>

<br>

## ❸ week

- 수업내용

  - 개인 포트폴리오 화면 추가 (메인 이미지 변경, 내 소개 추가 수정, 나의 전공&세부 분야 생각하기)

- 연습문제

  - 상세 페이지 수정하기 (about_detail.html)
  - 되돌아가기 버튼 수정 : 버튼 클릭시 창 닫기, 창 닫기 여부 묻는 팝업

    <p align="center">
    <img width="200" alt="3w 추가2" src="https://github.com/user-attachments/assets/c366c164-065b-43e7-8a79-44b8d1332aa1" />
    <img width="200" alt="3w 추가" src="https://github.com/user-attachments/assets/63604617-6523-4deb-98fe-e4c1bb3179bf" />
    </p>

    > [!NOTE]<br>
    > JS에 confirmClose 함수 추가 <br>
    >
    > 1. 특정 페이지에서만 실행 되도록 (더보기 창에서만 가능하도록) id 추가<br>
    > 2. if ($('#aboutPage').length > 0) : 현재 페이지에 id 를 갖은 요소가 존재하는지 확인하고 찾기, 해당 요소가 존재하면 길이가 1 이상이므로 함수 실행<br>
    > 3. confirmClose() 함수로 확인 / 취소 버튼 클릭 가능한 상자 표시, 확인 true, 취소 flase 반환<br>
    > 4. 이후 window.close() 로 창 닫고<br>
    > 5. window.location.href = "/"; 로 메인 페이지로 이동<br>
    > 6. event.preventDefault(); 로 취소 버튼 클릭시 링크 이동 방지<br>

<br>

## ❹ week

- 수업내용

  - 프로필 기술 경험 상세 수정
  - 데이터 베이스 연결
    > [!NOTE]<br>
    > MySql 사용 <br>
    > 데이터 베이스 생성 : create database spring; / flush privileges;<br>
    > 데이터 베이스 계정 정보 추가<br>
  - url 맵핑, 컨트롤러 : templates 파일 수정, html 생성, 컨트롤러에 등록

- 연습문제

  - testdb에 인물 추가 : INSERT 기능 사용
    <p align="center">
      <img width="220" alt="4주 추가1" src="https://github.com/user-attachments/assets/d02c71e3-1b2e-4c4f-bb30-5dc1ce2124e1" />
      <img height="140" alt="4w 추가2" src="https://github.com/user-attachments/assets/d3e512e4-6e71-486f-8ce1-a4d4df78581b" />
    </p>

    > [!NOTE] <br>
    > FindAll class를 추가해 모든 데이터 반복적으로 불러오도록
    > <br>

## ❺ week

- 수업내용

  - 블로그 게시판 메뉴, 링크 등록
  - th:each 반복 문법을 사용해 블로그 게시판 생성 & 등록
  - 게시판 조회, 데이터 베이스를 통해 임시 데이터 삽입, 글쓰기 기능 추가

    <p align="center"> <img width="400" alt="image" src="https://github.com/user-attachments/assets/701bebd7-d8b1-4a76-ab24-4aacf11ab9c9"> </p>

- 연습문제

  - 페이지 리다이랙트
    > [!NOTE]<br>
    > @RestController -> @Controller 방식 수정 <br>
    > "redirect:/article\_~~"; 형식으로 리턴 완료

  <p align="center"> <img width="400" alt="5w 추가1" src="https://github.com/user-attachments/assets/9e876268-9d11-4280-b4cb-de8076699fff" /> </p>
  <p align="center"> 5주 리다이렉트영상 </p>

## ❻ week

- 수업내용

  - 중첩 조합 (동적 링크)로 수정 & 삭제 버튼 등록
  - 블로그 게시판 수정 기능 구현
  - 블로그 게시판 삭제 기능 구현
  - 추가 예외 처리 (error, 에러 메시지 전달) <p align="center">
  <img width="400" alt="image" src="https://github.com/user-attachments/assets/248d4974-5b64-498e-b6a2-5718f866e3c0">
  <img width="400" alt="image" src="https://github.com/user-attachments/assets/868eb741-9399-4ed5-a76e-d199b2a4783b">
  <img width="400" alt="image" src="https://github.com/user-attachments/assets/96ca3e77-a683-4ec4-a289-c93b06ffc528">
  <img width="400" alt="image" src="https://github.com/user-attachments/assets/3f124f6f-c67c-4455-830a-567042d46226">
  </p>

- 연습문제

  - 매개변수가 문자열인 것도 에러 처리 (새로운 예외용 에러 페이지 생성) <p align="center"> <img width="400" alt="6W 2" src="https://github.com/user-attachments/assets/159e947f-dc86-4265-bbdc-849815764679" /> </p>

    > [!NOTE]<br>
    > 문자열 에러 처리<br>
    > URL 경로에 숫자를 기대하는 @PathVariable을 사용하고, 클라이언트가 문자열을 전달하는 경우 MethodArgumentTypeMismatchException 예외 발생 <br>
    > ex) id는 Long 타입이어야 하지만, abc라는 문자열을 전달하면 안 됨<br>
    > MethodArgumentTypeMismatchException이 발생할 경우, 사용자에게 "잘못된 요청입니다. ID는 숫자여야 합니다."라는 에러 메시지를 보여주고, article_error2라는 오류 페이지로 리다이렉트

## ❼ week

- 수업내용

  - 포트폴리오 나의 프로젝트 수정
  - 새 게시판 생성, 작성자/작성일/조회수/좋아요 등 추가
  - 새로운 테이블에 insert 쿼리문으로 새 글 삽입, 문자열 통일
  - 레포지토리에 새로운 테이블 연동
  - 글 제목 부분에 링크 추가하여 게시판 조회로 넘어가도록
  - 게시판 하단에 수정, 삭제 버튼 추가
  <p align="center"> 7주 이미지 </p>

- 연습문제

  - 글 수정 구현 (AddArticleRequest, BlogService)
    > [!NOTE]<br>
    >
    > 1. Optional : boardRepository.findById(id)를 통해 id에 해당하는 게시글을 조회, 해당 id에 게시글이 존재하지 않으면 Optional.empty()를 반환하고, 존재하면 Optional.of(board)를 반환<br>
    > 2. ifPresent() : board가 존재하면 해당 블록을 실행합니다. 즉 게시글이 존재하는 경우에만 수정 작업을 진행<br>
    > 3. 게시글 수정&저장 : board.update 에서 객체의 해당 필드들을 업데이트, boardRepository.save(board)를 호출해 게시글 정보를 데이터베이스에 저장
    <p align="center"> 7주 추가구현영상(수정) </p>

## ❽ week

- 수업내용

  - 글쓰기 기능 (board.write) 후 저장
  - 키워드 검색창과 3개 단위의 페이징
  - 하단 페이징 기능에 네비바 형태로 페이지 시작 ~ 끝 출력, 페이지 번호 이동 : 컨트롤러에 전달된 변수 - 전체/현재 페이지 계산 <p align="center"> <img width="400" alt="8w3" src="https://github.com/user-attachments/assets/f396b0fa-5820-4c7d-89aa-4fd455ff5e93" /> </p>

- 연습문제

  - id 대신 글 번호 출력 (현재 페이지 기준 1번 ~ n번) <p align="center"> <img width="400" alt="8w1" src="https://github.com/user-attachments/assets/642c079d-4b2f-47a0-bc47-f5f666dbbc05" /> </p>

    > [!NOTE]<br>
    > 페이지네이션 구현 : 페이지 단위 조회, 키워드 검색<br>
    > PageRequest.of : page로 현재 페이지 번호, pageSize로 한 페이지 당 조회할 게시글 수 지정<br>
    > blogService.searchByKeyword : 해당 키워드로 게시글을 검색하여 결과 페이지 단위로 조회<br>
    > int startNum = (page \* pageSize) + 1; : 현재 페이지에서 보여줄 게시글의 시작 번호 계산

  - 삭제 기능 구현 <p align="center"> <img width="400" alt="8w2" src="https://github.com/user-attachments/assets/0f7077ce-0cd5-4f2c-b196-0bc4469e187e" /> </p>
    > [!NOTE]<br>
    > 삭제 기능<br>
    > @DeleteMapping : HTTP DELETE 요청을 처리<br>
    > @PathVariable, board_delete : 어노테이셔을 사용해 게시글의 고유 id를 매서드의 파라미터로 전달 후 delete 메서드로 삭제 작업 수행<br>
    > blogService.delete(id)는 실제 게시글을 삭제하는 로직<br>
    > 삭제 후 클라이언트를 "/board_list" URL로 리다이렉트<br>

## ❾ week

- 수업내용

  - 스프링 시큐리티로 의존성 추가, 로그인&로그아웃
  - 회원가입 기능 구현, 테이블 생성, 레포지토리, 가입 화면, 서비스, dto
  - 로그인 기능과 검증 구현
    > [!NOTE]<br>
    >
    > 1. MemberService.java : 회원 관리 시스템 (회원 가입과 로그인 기능) <br>
    > 2. 유효성 검증 : @Valid와 @Validated 사용하해 회원 가입 시 나이, 비밀번호, 이메일 형식 등의 유효성 검사 <br>
    > 3. 중복 가입 방지 : validateDuplicateMember 메서드로 이메일 중복을 확인, 중복 가입 방지 <br>
    > 4. 비밀번호 암호화 : PasswordEncoder를 사용해 비밀번호 암호화 <br>
    > 5. 회원 저장 : MemberRepository.save로 회원 정보 데이터베이스에 저장. <br>
    > 6. 로그인 인증 : loginCheck 메서드를 통해 이메일과 암호화된 비밀번호를 비교하여 사용자 인증

- 연습문제

  - 회원가입 입력값 필터링, 제한 : 100세이상 불가능, 비밀번호 제한
    > [!NOTE]<br>
    >
    > 1. 나이 제한 <br>
    >    @NotNull: 나이는 필수 입력값 <br>
    >    @Min: 최소 값, 나이가 19세 이상 <br>
    >    @Max: 최대 값, 90세 이하<br>
    > 2. 비밀번호 제한<br> > \*@NotBlank: 비밀번호가 공백일 수 없음<br>
    >    ^(?=._[A-Z]) 대문자가 최소 1개 포함 / (?=._[a-z]) 소문자가 최소 1개 포함 / (?=.\*\\d) 숫자가 최소 1개 포함 / .{8,}$: 총 길이가 8자 이상<br>
    >    = 비밀번호는 8자 이상, 대문자, 소문자 및 숫자를 포함

  <p align="center"> 9w 영상 </p>

## ❶⓿ week

- 수업내용

  - 프로필 팀원, 추천글, 후기 추가
  - 로그인 기능 구현
  - 로그인 후 게시판에 사용자 출력 (메일)
  - 로그아웃 구현 : 세션 삭제
  <p align="center"> 10주 </p>

- 연습문제

  - 로그인한 사용자로 작성자 제공
  - 글 작성자만 수정, 삭베 기능 수행

  <p align="center"> 10 </p>

## ❶❶ week

- 수업내용

  - 메일 업로드 기능 구현 (파일) : 서버 내부 upload 폴더
  - 최하단 지도, 프로필 주소 정보
  - 깃허브 페이지 웹 사이트 활성화 : https://haaseoo.github.io/SITE/
   <p align="center"> <img width="400" alt="11w git" src="https://github.com/user-attachments/assets/f3cdeba4-9193-477d-82d6-039c56bcb4c5" />
  <img width="500" alt="11주 pages" src="https://github.com/user-attachments/assets/dad0ad89-5e3a-4d05-9f7f-79fb5befd5b6" />
   </p>

- 연습문제

  - 2명 이상 로그인 가능하도록, 사용자마다 다른 이름 세션 생성
  - 로그아웃 시 현재 사용자의 세션과 쿠키만 삭제
    <p align="center"> 11ww</p>

  - 동일 파일 업로드 시 다른 이름으로 업로드
  - 파일 업로드 에러 페이지
    <p align="center"> 11ww</p>

## 개인 추가 구현

1. 메인 홈페이지 색상 변화 주기 <p align="center"> <img width="400" alt="추가1" src="https://github.com/user-attachments/assets/a147fcd6-8f65-476c-8116-754067db47f1" /> </p>

   > [!NOTE]<br>
   > class에 btn-custom, css로 btn-custom만 하늘색으로 변경

<br><hr><br>

2. 회원가입 시 비밀번호 보기 박스 <p align="center"> 번 </p>

   > [!NOTE]<br>

<br><hr><br>

3. 버튼 누르면 날짜, 현재 위치, 날씨 등장 <p align="center"> <img width="1078" alt="추가3" src="https://github.com/user-attachments/assets/b0aea278-9939-4e14-a96a-7047bd71d57f" /> </p>

   > [!NOTE]<br>

<br><hr><br>

4. 들어갔던 게시판 제목 색상 변화 & 글쓰기 버튼 오른쪽 정렬 <p align="center"> <img width="400" alt="추가4" src="https://github.com/user-attachments/assets/82af7220-428c-493c-bb11-614a975ada43" /> </p>
   > [!NOTE]<br>
