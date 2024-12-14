## ❷ week

- 수업내용

  - vs code 스프링 부트 설정, 기본 화면 실행, 깃허브 연동
  - Thymeleaf 문법의 선언, 주석, 텍스트 출력

    > [!NOTE]
    > 선언 : < html lang="en" xmlns:th="http://www.thymeleaf.org" > <br>
    > 주석 : < !--/_ This code will be removed at thymeleaf parsing time! _/-- > <br>
    > 텍스트 출력 : 변수 표현식, <sapn th:text=${data}"><br>
    > 참고 : https://www.thymeleaf.org/

  - url 맵핑, 컨트롤러 : templates 파일 수정, html 생성, 컨트롤러에 등록

- 연습문제

  - url 맵핑과 컨트롤러 추가 : hello2 맵핑, 5개 속성 출력하기<br>
    <img width="150" alt="2w 추가1" src="https://github.com/user-attachments/assets/decb9724-5423-459f-886f-3c52a2dff45b" /> <br>

  - Local Date 를 통한 날짜 추가 출력<br>
    <img height="200" alt="2w 추가2" src="https://github.com/user-attachments/assets/6054cfb3-c0ce-4dd6-acf7-64e53103321f" />

<br>

## ❸ week

- 수업내용

  - 개인 포트폴리오 화면 추가 (메인 이미지 변경, 내 소개 추가 수정, 나의 전공&세부 분야 생각하기)

- 연습문제

  - 상세 페이지 수정하기 (about_detail.html)
  - 되돌아가기 버튼 수정 : 버튼 클릭시 창 닫기, 창 닫기 여부 묻는 팝업

    <img width="200" alt="3w 추가2" src="https://github.com/user-attachments/assets/c366c164-065b-43e7-8a79-44b8d1332aa1" />
    <img width="200" alt="3w 추가" src="https://github.com/user-attachments/assets/63604617-6523-4deb-98fe-e4c1bb3179bf" />

    > [!NOTE]
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
    > [!NOTE]
    > MySql 사용 <br>
    > 데이터 베이스 생성 : create database spring; / flush privileges;<br>
    > 데이터 베이스 계정 정보 추가<br>
  - url 맵핑, 컨트롤러 : templates 파일 수정, html 생성, 컨트롤러에 등록

- 연습문제

  - testdb에 인물 추가 : INSERT

    <img width="220" alt="4주 추가1" src="https://github.com/user-attachments/assets/d02c71e3-1b2e-4c4f-bb30-5dc1ce2124e1" />
    <img height="140" alt="4w 추가2" src="https://github.com/user-attachments/assets/d3e512e4-6e71-486f-8ce1-a4d4df78581b" />

<br>

## ❺ week

## ❻ week

## ❼ week

## ❽ week

## ❾ week

## ❶⓿ week

## ❶❶ week

## 개인 추가 구현

- LocalDate로 오늘 날짜 추가하기

- 메인 홈페이지 색상 변화 주기

* <img width="400" alt="추가2" src="https://github.com/user-attachments/assets/a147fcd6-8f65-476c-8116-754067db47f1" />
  <hr>

- 내가 들어갔던 게시판 제목 색상 변화 주기 & 글쓰기 버튼 오른쪽 정렬
  <img width="400" alt="추가3" src="https://github.com/user-attachments/assets/82af7220-428c-493c-bb11-614a975ada43" />

<img width="200" alt="5w 추가1" src="https://github.com/user-attachments/assets/9e876268-9d11-4280-b4cb-de8076699fff" />

<img width="200" alt="8w3" src="https://github.com/user-attachments/assets/f396b0fa-5820-4c7d-89aa-4fd455ff5e93" />
<img width="200" alt="8w2" src="https://github.com/user-attachments/assets/0f7077ce-0cd5-4f2c-b196-0bc4469e187e" />
<img width="200" alt="8w1" src="https://github.com/user-attachments/assets/642c079d-4b2f-47a0-bc47-f5f666dbbc05" />
<img width="200" alt="6W 2" src="https://github.com/user-attachments/assets/159e947f-dc86-4265-bbdc-849815764679" />
<img width="200" alt="6w 1" src="https://github.com/user-attachments/assets/acb22143-9bba-4bfb-9c93-0dfe39d0a178" />

<img width="957" alt="11w git" src="https://github.com/user-attachments/assets/f3cdeba4-9193-477d-82d6-039c56bcb4c5" />
<img width="794" alt="11주 pages" src="https://github.com/user-attachments/assets/dad0ad89-5e3a-4d05-9f7f-79fb5befd5b6" />

> [!NOTE]\
> dd
