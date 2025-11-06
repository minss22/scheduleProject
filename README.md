# 📅 일정 관리 프로젝트
## 📖 프로젝트 소개
이 프로젝트는 사용자가 일정을 **등록, 조회, 수정, 삭제**할 수 있는 간단한 **일정 관리 API** 서버입니다.
**Spring Boot**를 기반으로 구현되었으며, **MySQL** 데이터베이스를 사용해 데이터를 관리합니다.
**3 Layer Architecture**에 따라 각 Layer의 목적에 맞게 개발되었으며, **JPA**를 사용하여 **CRUD** 기능을 구현하였습니다.

## 📑 API 명세서
### 1. 일정 생성
| 항목               | 내용                                                                                                                                                |
|-------------------| ------------------------------------------------------------------------------------------------------------------------------------------------- |
| **기능**           | 일정 생성                                                                                                                                             |
| **Method**        | POST                                                                                                                                              |
| **URL**           | `/schedules`                                                                                                                                      |
| **Parameters**    | -                                                                                                                                                 |
| **Request Body**  | `json { "title": "공부", "content": "스프링 부트 공부", "name": "홍길동", "password": 1234 }`                                                                 |
| **Response Body** | `json { "id": 1, "title": "공부", "content": "스프링 부트 공부", "name": "홍길동", "createdAt": "2025-11-05T10:00:00", "modifiedAt": "2025-11-05T10:00:00" }` |
| **상세 설명**       | 신규 일정 등록                                                                                                                                          |
| **상태 코드**       | 201(CREATED)                                                                                                                                                 |

### 2. 전체 일정 조회
| 항목               | 내용                                                                                                                                                    |
| ----------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| **기능**           | 전체 일정 조회                                                                                                                                              |
| **Method**        | GET                                                                                                                                                   |
| **URL**           | `/schedules`                                                                                                                                          |
| **Parameters**    | (Query) `name` *(optional)*                                                                                                                           |
| **Request Body**  | -                                                                                                                                                     |
| **Response Body** | `json [ { "id": 1, "title": "공부", "content": "스프링 부트 공부", "name": "홍길동", "createdAt": "2025-11-05T10:00:00", "modifiedAt": "2025-11-05T10:00:00" } ]` |
| **상세 설명**       | `name` 입력 시 해당 작성자 일정만 조회                                                                                                                             |
| **상태 코드**       | 200(OK)                                                                                                                                                      |

### 3. 선택 일정 조회
| 항목               | 내용                                                                                                                                                |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| **기능**           | 일정 단건 조회                                                                                                                                          |
| **Method**        | GET                                                                                                                                               |
| **URL**           | `/schedules/{id}`                                                                                                                                 |
| **Parameters**    | (Path) `id`                                                                                                                                       |
| **Request Body**  | -                                                                                                                                                 |
| **Response Body** | `json { "id": 1, "title": "공부", "content": "스프링 부트 공부", "name": "홍길동", "createdAt": "2025-11-05T10:00:00", "modifiedAt": "2025-11-05T10:00:00" }` |
| **상세 설명**       | 특정 일정 1건 조회                                                                                                                                       |
| **상태 코드**       | 200(OK)                                                                                                                                                  |

### 4. 일정 수정
| 항목               | 내용                                                                                                                                                |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| **기능**           | 일정 수정                                                                                                                                             |
| **Method**        | PUT                                                                                                                                               |
| **URL**           | `/schedules/{id}`                                                                                                                                 |
| **Parameters**    | (Path) `id`                                                                                                                                       |
| **Request Body**  | `json { "title": "공부", "name": "홍길동", "password": 1234 }`                                                                                         |
| **Response Body** | `json { "id": 1, "title": "공부", "content": "스프링 부트 공부", "name": "홍길동", "createdAt": "2025-11-05T10:00:00", "modifiedAt": "2025-11-05T11:00:00" }` |
| **상세 설명**       | 비밀번호 검증 필요                                                                                                                                        |
| **상태 코드**       | 200(OK)                                                                                                                                                  |

### 5. 일정 삭제
| 항목               | 내용                        |
|-------------------| --------------------------- |
| **기능**           | 일정 삭제                     |
| **Method**        | DELETE                      |
| **URL**           | `/schedules/{id}`           |
| **Parameters**    | (Path) `id`                 |
| **Request Body**  | `json { "password": 1234 }` |
| **Response Body** | -                           |
| **상세 설명**       | 비밀번호 검증 필요             |
| **상태 코드**       | 204(NO_CONTENT)             |

## 🗂️ ERD
<img width="346" height="422" alt="Image" src="https://github.com/user-attachments/assets/5b0fa224-5c2d-4484-8a67-8b3a87adcb97" />

## 🧱 프로젝트 구조
```
📂 src
 └─ 📂 main
     └─ 📂 java/schedule
         ├─ 📂 controller   # 요청을 처리하는 Controller
         │   └─ ScheduleController.java
         ├─ 📂 dto          # Request, Response DTO
         │   ├─ CreateScheduleRequest.java
         │   ├─ DeleteScheduleRequest.java
         │   ├─ ScheduleResponse.java
         │   └─ UpdateScheduleRequest.java
         ├─ 📂 entity       # Schedule 엔티티 클래스
         │   ├─ BaseEntity.java
         │   └─ Schedule.java
         ├─ 📂 repository   # 데이터베이스 접근하는 Repository
         │   └─ ScheduleRepository.java
         ├─ 📂 service      # 비즈니스 로직 처리하는 Service
         │   └─ ScheduleService.java
         └─ ScheduleProjectApplication.java

```

## 📦 3 Layer Architecture
### 1. Controller
- 사용자의 요청(Request)을 받아 Service 계층에 전달하고, 응답(Response)을 반환하는 역할을 담당
- 클라이언트로부터 일정 관련 요청을 받아 처리
- **생성(POST), 조회(GET), 수정(PUT), 삭제(DELETE)** 기능 제공

### 2. Service
- Controller로부터 요청을 받아 데이터 처리 과정을 수행하고, Repository와 상호작용
- **일정 생성, 조회, 수정, 삭제** 등의 핵심 로직을 수행
- Repository를 이용해 데이터베이스에 접근

### 3. Repository
- 데이터베이스와 직접 연결되는 계층으로, JPA를 통해 **CRUD** 작업을 처리
- Spring Data JPA의 JpaRepository를 상속받아 CRUD 메서드를 제공

## ⚙️ 주요기능
| 기능               | 설명                    | Method / URL                 |
| ---------------- | --------------------- | ---------------------------- |
| **일정 생성**        | 새로운 일정을 등록합니다.        | `POST /schedules`            |
| **전체 일정 조회**     | 모든 일정을 조회합니다.         | `GET /schedules`             |
| **작성자명으로 일정 조회** | 특정 작성자의 일정만 조회합니다.    | `GET /schedules?name={name}` |
| **단일 일정 조회**     | 특정 ID의 일정을 조회합니다.     | `GET /schedules/{id}`        |
| **일정 수정**        | 비밀번호를 검증 후 일정을 수정합니다. | `PUT /schedules/{id}`        |
| **일정 삭제**        | 비밀번호를 검증 후 일정을 삭제합니다. | `DELETE /schedules/{id}`     |

## 📋 요구사항
### Lv 1. 일정 생성  `필수`
- **일정 생성(일정 작성하기)**
    - 일정 생성 시, 포함되어야할 데이터
        - `일정 제목`, `일정 내용`, `작성자명`, `비밀번호`, `작성/수정일`을 저장
        - `작성/수정일`은 날짜와 시간을 모두 포함한 형태
    - 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
    - 최초 생성 시, `수정일`은 `작성일`과 동일
    - `작성일`, `수정일` 필드는 `JPA Auditing`을 활용하여 적용
    - API 응답에 `비밀번호`는 제외

### Lv 2. 일정 조회  `필수`
- **전체 일정 조회**
    - 전체 일정을 조회하거나, `작성자명`을 기준으로 등록된 일정 목록을 전부 조회
    - `작성자명`은 조회 조건으로 포함될 수도 있고, 포함되지 않을 수도 있음
    - 하나의 API로 작성
    - `수정일` 기준 내림차순으로 정렬
    - API 응답에 `비밀번호`는 제외
- **선택 일정 조회**
    - 일정의 고유 식별자(ID)를 사용하여 단건의 정보를 조회
    - API 응답에 `비밀번호`는 제외

### Lv 3. 일정 수정  `필수`
- **선택한 일정 수정**
    - 선택한 일정 내용 중 `일정 제목`, `작성자명` 만 수정 가능
    - 서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달
    - `작성일` 은 변경할 수 없으며, `수정일` 은 수정 완료 시, 수정한 시점으로 변경
    - API 응답에 `비밀번호`는 제외
   
### Lv 4. 일정 삭제  `필수`
- **선택한 일정 삭제**
    - 선택한 일정을 삭제
    - 서버에 일정 삭제을 요청할 때 `비밀번호`를 함께 전달

## 💻 결과
### 1. 일정 생성
<img width="1034" height="675" alt="Image" src="https://github.com/user-attachments/assets/7b5669cb-6d15-47f1-a2cc-9f569f8fb505" />

### 2. 전체 일정 조회
<img width="1035" height="843" alt="Image" src="https://github.com/user-attachments/assets/be91e908-6c7a-4c20-a56b-24b14bd1d1a5" />

### 3. 작성자의 전체 일정 조회
<img width="1037" height="854" alt="Image" src="https://github.com/user-attachments/assets/7df6a4ce-c5fa-42b1-aa29-3b853321c1cc" />

### 4. 일정 단건 조회
<img width="1037" height="444" alt="Image" src="https://github.com/user-attachments/assets/a277bc66-f08c-4a08-8fa8-d1234096be23" />

### 5. 일정 수정
<img width="1045" height="646" alt="Image" src="https://github.com/user-attachments/assets/c65b5549-659f-4302-9974-6c37f63ff176" />

#### 수정일 기준으로 내림차순 정렬 확인
<img width="1034" height="844" alt="Image" src="https://github.com/user-attachments/assets/d027fc06-6488-4f7c-91fa-4c5da6cf7c64" />

### 6. 일정 삭제
<img width="1043" height="433" alt="Image" src="https://github.com/user-attachments/assets/d17c2fdd-78bd-4964-89f0-1cefe6d0146c" />

#### 삭제 확인
<img width="1032" height="658" alt="Image" src="https://github.com/user-attachments/assets/367e4b5d-e031-4d24-8055-a758b7c24f30" />