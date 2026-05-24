<div align="center">

<img src="docs/images/logo-ggshop.png" alt="GG# 로고" width="250">

# GG# Crew Energy Station

> 본 서비스는 전기차 사용자들이 연결되는 P2P 에너지 거래 플랫폼입니다.  
> 남는 전기 에너지를 개인 간 거래하고, V2G(Vehicle to Grid)를 통해 전기를 판매하는 흐름을 제공합니다.  
> 전기차 충전소 관리, 에너지 거래, P2P 게시판, 회원 관리를 한 곳에서 제공하는 통합 플랫폼입니다.  
> 함께 충전하고, 거래하고, 관리하는 새로운 EV 에너지 경험을 만듭니다.

![Java](https://img.shields.io/badge/Java-17-007396?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-3.0-000000?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-005F0F?style=flat-square&logo=thymeleaf&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8-02303A?style=flat-square&logo=gradle&logoColor=white)

</div>

---

## 기획 의도

<img src="docs/images/ev-market-growth.jpg" alt="양방향 EV 충전기 시장 성장률" width="893">

<img src="docs/images/v2g-concept.jpg" alt="V2G 찾아가는 충전 서비스" width="893">

본 프로젝트는 전기차 사용자가 증가하는 상황에서 **충전 인프라 부족**, **남는 배터리 에너지의 비효율**, **개인 간 에너지 거래 경험 부재**를 해결하고자 기획되었습니다.

주요 기획 배경은 다음과 같습니다.

1. **전기차 충전 수요 증가**

   전기차 보급이 확대되면서 충전소 탐색과 충전 가능 여부 확인이 사용자 경험의 핵심 문제가 되었습니다.  
   이에 따라 충전소 정보 관리와 사용자 중심의 충전 흐름을 제공하는 서비스가 필요하다고 판단했습니다.

2. **남는 배터리 에너지 활용**

   전기차는 단순 이동 수단을 넘어 에너지 저장 장치로 활용될 수 있습니다.  
   사용하지 않는 배터리 잔량을 P2P 거래나 V2G 방식으로 활용하면 사용자에게 새로운 수익 기회를 제공할 수 있습니다.

3. **안전하고 신뢰 가능한 거래 필요**

   개인 간 에너지 거래는 금액, 충전량, 거래 대상이 명확해야 하며 기록 관리가 중요합니다.  
   이를 위해 회원 기반 인증, 거래 내역 조회, 기업 관리자 기능을 포함한 플랫폼 구조를 설계했습니다.

이에 따라, **전기차 사용자와 충전 인프라를 연결하고 에너지 거래를 안전하게 관리할 수 있는 서비스**를 기획하게 되었습니다.

---

# EV 에너지 거래 시장 분석 보고서

양방향 전기차(V2G) 충전기 시장은 전 세계적으로 빠르게 성장하고 있으며, 전기차 보급 확대와 함께 충전 인프라 및 에너지 거래 서비스의 필요성이 높아지고 있습니다. 본 프로젝트는 이러한 흐름을 바탕으로 개인 간 에너지 거래와 기업 충전소 관리가 가능한 플랫폼을 구현하는 것을 목표로 했습니다.

## 1. 전기차 충전 시장의 장기적 변화

> <img src="docs/images/ev-market-growth.jpg" alt="EV 시장 성장 분석" width="558">

시장 자료를 기반으로 살펴보면, 양방향 전기차 충전기 시장은 연평균 성장률(CAGR) 24.3% 수준의 성장세를 보입니다.  
특히 아시아 태평양 지역이 주요 성장 시장으로 부상하고 있으며, 국내에서도 전기차 충전 인프라와 관련 서비스 수요가 점차 커지고 있습니다.

### 주요 성장 요인

- **전기차 보급 확대**  
  전기차 사용자가 증가하면서 충전소, 충전 관리, 에너지 사용 효율화 수요가 함께 증가했습니다.

- **V2G 기술 가능성 확대**  
  전기차 배터리를 에너지 저장소로 활용해 전력망에 전기를 공급하거나 개인 간 거래에 활용할 수 있습니다.

- **P2P 플랫폼 모델의 확산**  
  에어비앤비처럼 공급자와 수요자를 연결하는 플랫폼 모델을 에너지 거래 영역에 적용할 수 있다고 판단했습니다.

## 2. P2P 에너지 거래 모델

<img src="docs/images/p2p-business-model.jpg" alt="P2P 비즈니스 모델" width="893">

GG#은 전기차 보유자와 충전이 필요한 사용자를 연결하는 P2P 거래 구조를 기반으로 합니다.  
플랫폼은 거래 등록, 조회, 결제 흐름, 거래 기록 관리를 담당하며, 사용자는 남는 에너지를 판매하거나 필요한 에너지를 구매할 수 있습니다.

<br>

<img src="docs/images/p2p-v2g.png" alt="차량 간 에너지 전달 개념" width="500">

## 3. 결론

전기차 시장은 일시적인 인프라 부족과 제도적 한계가 존재하지만, 장기적으로는 충전 서비스와 에너지 활용 서비스가 함께 성장할 가능성이 높습니다.  
GG#은 이러한 흐름 속에서 충전소 관리, V2G 거래, P2P 게시판, 회원 관리 기능을 하나의 서비스로 연결한 프로젝트입니다.

---

## 기대 효과

<img src="docs/images/flowchart.png" alt="서비스 전체 흐름도" width="1000">

1. **충전 인프라 접근성 향상**

   - 충전소 정보를 등록하고 조회할 수 있어 사용자가 필요한 충전 인프라를 더 쉽게 찾을 수 있습니다.

2. **에너지 활용 효율 증가**

   - 남는 전기차 배터리 에너지를 거래하거나 V2G 방식으로 판매하여 에너지 낭비를 줄일 수 있습니다.

3. **사용자 수익 기회 제공**

   - 전기차 보유자는 남는 에너지를 판매함으로써 추가적인 경제적 가치를 얻을 수 있습니다.

4. **관리자 운영 효율 향상**

   - 기업 관리자는 충전소, 직원, 고객, 거래 내역을 한 화면 흐름에서 관리할 수 있습니다.

5. **커뮤니티 기반 거래 활성화**

   - P2P 게시판을 통해 사용자 간 거래, 댓글, 파일 첨부, 태그 기반 탐색이 가능해집니다.

---

## 프로젝트 사용 툴

| 구분 | 기술/도구 |
|------|-----------|
| **HTML Engine** | Thymeleaf |
| **Frontend** | HTML, JavaScript, CSS |
| **Backend** | Spring Boot, Java 17 |
| **Database** | MySQL |
| **Persistence** | MyBatis |
| **Build** | Gradle |
| **API** | Kakao Login, SMTP Mail, REST API, Lombok, Thumbnailator |
| **Auth** | Session, Cookie, Kakao OAuth 2.0 |
| **Tool** | IntelliJ IDEA, VS Code |
| **기타** | Git, GitHub, Postman, Sourcetree |
| **테스트** | JUnit5, Spring Boot Test, MyBatis Test |

---

## 주요 기능

### 1. 회원 시스템

<img src="docs/images/kakao-login.png" alt="카카오 로그인" width="280">

- 이메일 기반 단계별 로그인 흐름
- 신규 회원과 기존 회원 자동 분기
- 카카오 OAuth 2.0 로그인
- 아이디 기억 쿠키 처리
- 이메일 중복 확인 AJAX
- 세션 기반 로그아웃

### 2. EV 충전기 관리

- 충전소 등록, 목록, 상세, 수정, 삭제
- 주소, 설치일, 비고 기반 검색
- 페이징 처리
- 충전소 UID 중복 확인 AJAX API

### 3. ZZ1 V2G 에너지 거래

<img src="docs/images/logo-zz1.png" alt="ZZ1 에너지 로고" width="180">

- 전체, 일별, 주별, 월별 거래 내역 조회
- 이메일 기반 회원 조회
- UID 기반 충전기 조회
- 로그인 세션 기반 에너지 기여
- 기업 관리자 V2G 거래 수동 등록

### 4. P2P 거래 게시판

- 게시글 작성, 목록, 상세, 수정, 삭제
- 본인 게시글 수정/삭제 권한 검증
- 파일 첨부 및 이미지 리사이징
- 태그 등록 및 삭제
- 댓글 작성, 수정, 삭제
- 구매/판매 필터, 키워드 검색, 페이징

### 5. 회사 직원 관리

- 직원 등록, 목록, 상세, 수정, 삭제
- 이름과 이메일 기반 검색
- 페이징 처리

### 6. 회원 프로필 관리

- 닉네임 변경
- 주소 변경
- 비밀번호 변경
- 프로필 이미지 업로드
- 팔로우, 차단, 신고 관련 화면 구성

---

## ERD (Entity Relationship Diagram)

<img src="docs/images/erd.png" alt="ERD 다이어그램" width="1000">

| 테이블 | 설명 |
|--------|------|
| `tbl_member` | 회원 정보 |
| `tbl_ev_charger` | EV 충전소 정보 |
| `tbl_car` | 회원 차량 정보 |
| `tbl_board` | P2P 거래 게시글 |
| `tbl_board_file` | 게시글 첨부파일 |
| `tbl_board_tag` | 게시글 태그 |
| `tbl_board_payment` | 게시글 결제 내역 |
| `tbl_comment` | 게시글 댓글 |
| `tbl_company` | 기업 정보 |
| `tbl_company_employee` | 기업 직원 정보 |
| `tbl_vtog_payment` | V2G 에너지 거래 내역 |

---

## 담당 업무

<img src="docs/images/flow_05.png" alt="P2P Board Flow" width="950">

<br>▶ 회원 및 인증

- 단계별 로그인
- 회원가입
- 로그아웃
- 카카오 OAuth 로그인
- 이메일 중복 확인
- 쿠키 기반 아이디 기억

<br>▶ EV 충전기 관리

- 충전기 목록
- 충전기 상세
- 충전기 등록
- 충전기 수정
- 충전기 삭제
- 충전기 UID 중복 확인
- 충전기 검색 및 페이징

<br>▶ V2G 에너지 거래

- 거래 목록 조회
- 일별, 주별, 월별 필터
- 에너지 기여 등록
- 회원 이메일 조회
- 충전기 UID 조회
- 기업 관리자 거래 등록

<br>▶ P2P 게시판

- 게시글 목록
- 게시글 상세
- 게시글 작성
- 게시글 수정
- 게시글 삭제
- 게시글 파일 업로드
- 태그 등록 및 삭제
- 댓글 작성
- 댓글 수정
- 댓글 삭제
- 구매/판매 필터
- 키워드 검색 및 페이징

<br>▶ 공통

- MyBatis XML 매퍼 작성
- DTO/VO 설계
- 서비스 계층 구현
- 컨트롤러 요청 흐름 구성
- 예외 상황 처리
- 화면 연결 및 테스트

---

## 서비스 플로우차트

### Login Flow - 1. 로그인 페이지

<img src="docs/images/flow_01.png" alt="Login Flow" width="950">

---

### Login Flow - 2. 카카오 OAuth 로그인

<img src="docs/images/flow_02.png" alt="Kakao OAuth Flow" width="950">

---

### EV Charger Flow - 충전기 관리

<img src="docs/images/flow_03.png" alt="EV Charger Flow" width="950">

---

### V2G Flow - 에너지 거래

<img src="docs/images/flow_04.png" alt="V2G Energy Flow" width="950">

---

### P2P Board Flow - 1. 게시판 목록과 작성

<img src="docs/images/flow_05.png" alt="P2P Board List Flow" width="950">

---

### P2P Board Flow - 2. 게시판 상세와 댓글

<img src="docs/images/flow_06.png" alt="P2P Board Detail Flow" width="950">

---

### Company Employee Flow - 직원 관리

<img src="docs/images/flow_07.png" alt="Company Employee Flow" width="950">

---

## 오류 상황들

### **1. DB 연결 실패를 사용자 흐름에서 처리하자**

#### **문제 상황**

- 로그인 이메일 확인 단계에서 DB 연결이 실패하면 500 에러 페이지가 노출되었습니다.

#### **문제 원인**

- `memberService.existsByEmail()` 호출 중 발생한 DB 예외가 컨트롤러에서 처리되지 않고 그대로 전파되었습니다.

#### **해결 방안**

```java
try {
    exists = memberService.existsByEmail(trimmedEmail);
} catch (Exception ex) {
    if (!isDbConnectionIssue(ex)) {
        redirectAttributes.addAttribute("error", "invalidCredential");
        return new RedirectView("/ev/company/loginLv2");
    }
    redirectAttributes.addAttribute("error", "dbUnavailable");
    return new RedirectView("/ev/company/loginLv2");
}
```

- DB 연결 관련 예외를 별도로 판별하고, 사용자에게 적절한 에러 상태를 전달하도록 처리했습니다.

### **2. 권한 검증은 화면 진입과 실제 처리 모두에서 확인하자**

#### **문제 상황**

- P2P 게시글 수정 화면에 들어갈 때는 작성자 검증이 있었지만, 실제 수정 POST 요청에서는 검증이 부족했습니다.

#### **문제 원인**

- GET 요청과 POST 요청의 역할이 분리되어 있었고, POST 처리 로직에 작성자 ID 검증이 누락되었습니다.

#### **해결 방안**

```java
BoardDTO foundBoard = boardService.detail(boardDTO.getId());
if (!member.getId().equals(foundBoard.getBoardMemberId())) {
    redirectAttributes.addFlashAttribute("errorMessage", "본인이 작성한 게시글만 수정할 수 있습니다.");
    return "redirect:/p2p/detail/" + boardDTO.getId();
}
```

- 수정 처리 단계에서도 세션 회원 ID와 게시글 작성자 ID를 비교하도록 추가했습니다.

### **3. 리다이렉트 경로는 인증 흐름을 고려하자**

#### **문제 상황**

- 로그아웃 후 이전 페이지로 이동하도록 Referer 기반 리다이렉트를 적용했을 때, 보호 페이지로 다시 이동하며 로그인 요청이 반복되는 문제가 있었습니다.

#### **문제 원인**

- 로그인이 필요한 페이지가 Referer로 저장되면 로그아웃 이후 다시 인증이 필요한 페이지로 이동하는 순환 구조가 발생했습니다.

#### **해결 방안**

- 로그아웃 후 이동 경로를 `/main`으로 고정하여 인증이 필요한 페이지로 되돌아가지 않도록 수정했습니다.

---

## QA 테스트

- 회원가입 및 로그인 흐름 테스트
- 카카오 OAuth 로그인 테스트
- EV 충전기 CRUD 테스트
- 충전기 UID 중복 확인 테스트
- V2G 거래 등록 및 조회 테스트
- P2P 게시글 CRUD 테스트
- 게시글 권한 검증 테스트
- 파일 업로드 및 이미지 리사이징 테스트
- 댓글 작성, 수정, 삭제 테스트
- 검색, 필터, 페이징 테스트

---

## 총평

### 기획

- 전기차 충전 인프라와 P2P 거래를 연결하는 구조를 설계하면서, 단순 CRUD가 아니라 사용자와 기업 관리자가 각각 어떤 흐름으로 서비스를 사용하는지 먼저 정리하는 것이 중요하다는 점을 배웠습니다.  
  특히 충전기, 회원, 거래 내역, 게시판이 서로 연결되기 때문에 DB 구조와 화면 흐름을 함께 설계해야 했습니다.

### 협업

- 기능이 회원, 충전기, 게시판, 거래, 기업 관리로 나뉘어 있어 팀원 간 API 경로와 DTO 구조를 빠르게 공유하는 것이 중요했습니다.  
  구현 중 변경된 필드나 URL을 즉시 공유하지 않으면 화면 연결 단계에서 오류가 발생할 수 있다는 점을 경험했습니다.

### 좋았던 점

- Spring Boot, MyBatis, Thymeleaf를 기반으로 백엔드와 화면을 직접 연결하며 전체 웹 서비스 흐름을 경험할 수 있었습니다.  
  특히 파일 업로드, 이미지 리사이징, OAuth 로그인, AJAX 중복 확인, 페이징 등 실제 서비스에 필요한 기능을 다양하게 구현해 볼 수 있었습니다.

### 아쉬웠던 점

- 초기에 예외 처리와 권한 검증 기준을 더 명확히 잡았다면 수정 단계에서 발생한 보안 흐름 문제를 줄일 수 있었을 것 같습니다.  
  다음 프로젝트에서는 기능 구현 전 인증, 권한, 에러 처리 정책을 먼저 문서화하고 개발에 들어가는 방식이 더 효율적이라고 느꼈습니다.

---

**작성자:** 정충효 &nbsp;&nbsp;&nbsp;&nbsp; **TEAM:** GG#  
**기간:** 프로젝트 진행 기간
