## 금사파

시세가 변경되는 금의 주문판매 서비스입니다.

> 기간 : 2024.09.02 ~ 2024.09.09

## 목차

1. [프로젝트 환경](#프로젝트-환경)
2. [Quick Start](#quick-start)
3. [Quick Stop](#quick-stop)
4. [ERD](#erd)
5. [API 명세서](#api-명세서)
6. [디렉토리 구조](#디렉토리-구조)
7. [요구사항 달성도](#요구사항-달성도)

## 프로젝트 환경

|                                                             Stack                                                              |        Version        |
|:------------------------------------------------------------------------------------------------------------------------------:|:---------------------:|
|          ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)          |   Spring Boot 3.3.3   |
|           ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)            |      Gradle 8.8       |
|           ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)            |        JDK 17         |
|        ![MariaDB](https://img.shields.io/badge/mariadb-%2300A3E0.svg?style=for-the-badge&logo=mariadb&logoColor=white)         |    MariaDB 11.5.2     |
|          ![Docker](https://img.shields.io/badge/docker-%23296AAB.svg?style=for-the-badge&logo=docker&logoColor=white)          |     Docker 27.2.0     |
| ![Docker Compose](https://img.shields.io/badge/docker%20compose-%2318A9D0.svg?style=for-the-badge&logo=docker&logoColor=white) | Docker Compose 2.29.2 |

## Quick Start

### 1. 사전 준비 사항

- Docker 및 Docker Compose가 설치되어 있어야 합니다.

### 2. 데이터베이스 실행

애플리케이션을 시작하기 전에 데이터베이스를 Docker Compose를 사용하여 설정해야 합니다. <br/>
다음 명령어를 사용하여 각 서버의 데이터베이스를 실행합니다.

```shell
docker-compose -f ./auth-server/docker-compose.auth.yml up -d
docker-compose -f ./resource-server/docker-compose.resource.yml up -d
```

위 명령어는 백그라운드에서 데이터베이스 컨테이너를 실행합니다. <br/>
실행 중인 상태를 확인하려면 `docker ps` 명령어를 사용하세요.

### 3. 서버 실행

서버별로 터미널을 열어 다음 명령어를 실행합니다.

- 인증 서버 (포트 8888)

    ```shell
    ./gradlew :auth-server:bootJar
    ```

    ```shell
    java -jar ./auth-server/build/libs/auth-server-0.0.1-SNAPSHOT.jar
    ```

- 자원 서버 (포트 9999)

    ```shell
    ./gradlew :resource-server:bootJar
    ```

    ```shell
    java -jar ./resource-server/build/libs/resource-server-0.0.1-SNAPSHOT.jar
    ```

## Quick Stop

### 1. 서버 종료

서버를 종료하려면 터미널에서 다음 명령어를 실행합니다.

- 인증 서버 (포트 8888)

    ```shell
    sudo lsof -i :8888
    sudo kill -9 [PID] # [PID]는 실제 프로세스 ID로 대체
    ```

- 자원 서버 (포트 9999)

    ```shell
    sudo lsof -i :9999
    sudo kill -9 [PID] # [PID]는 실제 프로세스 ID로 대체
    ```

### 2. 데이터베이스 종료

데이터베이스를 종료하려면 다음 명령어를 사용합니다.

```shell
docker-compose -f ./auth-server/docker-compose.auth.yml down
docker-compose -f ./resource-server/docker-compose.resource.yml down
```

## ERD

<img width=70% alt="image" src="https://github.com/user-attachments/assets/3b18ae47-5266-43cd-aa6e-be77f3cdcf93">

- [DB Diagram](https://dbdiagram.io/d/금사파-ERD-66d7bb70eef7e08f0e9a917c) 에서 전체 ERD를 확인할 수 있습니다.
- [ERD 변경 기록](https://github.com/youhyeoneee/geumsapa/wiki/ERD-%EA%B8%B0%EB%A1%9D) 에서 ERD의 변화 히스토리를 확인할 수 있습니다.

## API 명세서

<img width=70% alt="image" src="https://github.com/user-attachments/assets/feb61d96-2cf4-45f4-b192-effb6da9a036">

<br/>

- [Postman](https://documenter.getpostman.com/view/9878847/2sAXqmBQp4)에서 예시 Request, Response을 확인할 수 있습니다.
- [에러 코드](https://github.com/youhyeoneee/geumsapa/wiki/Error-Code) 에서 응답에 사용된 에러 코드의 정보를 확인할 수 있습니다.

## 디렉토리 구조

### 1. 멀티 모듈

이 프로젝트는 멀티 모듈로 구성되어 있습니다.

- auth-server : 인증을 담당하는 서버입니다.
- resource-server : 자원을 담당하는 서버입니다.
- core : 에러코드, 공통 리스폰스, JpaConfig 설정 등 두 서버의 공통적인 부분을 담당하는 모듈입니다.

<details>
<summary><strong>구조도</strong></summary>
<div markdown="1">

```
.
├── README.md
├── auth-server
│   ├── build
│   ├── docker-compose.auth.yml
│   ├── .env
│   └── src
│       ├── main
│       │   ├── java
│       │   └── resources
│       └── test
├── core
│   ├── build
│   ├── src
│   └── test
├── gradle
│   └── wrapper
├── gradlew
├── gradlew.bat
├── resource-server
│   ├── build
│   ├── docker-compose.resource.yml
│   ├── .env
│   └── src
│       ├── main
│       │   ├── java
│       │   └── resources
│       └── test
└── settings.gradle
```

</details>

<br/>

### core

- config: 공통적으로 사용하는 설정입니다.
- entity: DB 테이블과 매칭되는 엔티티 클래스를 관리합니다. 공통적으로 사용하는 컬럼을 BaseEntity로 구성하였습니다.
- exception: 사용자 정의 예외 클래스를 관리하고 전역으로 예외를 처리합니다.
- util: 공통적으로 사용하는 유틸리티 클래스를 관리합니다. ApiUtils를 통해 공통적인 API 응답 형식을 제공합니다.
  <br/>

<details>
<summary><strong>core 구조도</strong></summary>
<div markdown="1">

```
.core
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── yhkim
    │   │           ├── config
    │   │           │   └── JpaConfig.java
    │   │           ├── entity
    │   │           │   └── BaseEntity.java
    │   │           ├── exception
    │   │           │   ├── CustomException.java
    │   │           │   ├── ErrorCode.java
    │   │           │   └── GlobalExceptionHandler.java
    │   │           └── util
    │   │               └── ApiUtils.java
    │   └── resources
    │       └── application-core.yml
    └── test
        ├── java
        └── resources
```

</details>

<br/>

### 2. auth-server

인증을 담당하는 서버입니다.

<b>도메인</b>

- auth: 토큰 및 및 인증 관련 기능
- user: 사용자 관련 기능

<br/>

<b>도메인의 하위 패키지</b>

- controller: MVC 패턴의 컨트롤러 역할을 하며, 사용자 요청을 처리합니다.
- dto: 비즈니스 로직 수행 시 사용하는 DTO (Data Transfer Object)를 관리합니다. 주로 요청(request) 및 응답(response) 객체를 포함합니다.
- entity: DB 테이블과 매칭되는 엔티티 클래스를 관리합니다. 테이블과 동일한 프로퍼티를 가진 클래스입니다.
- repository: Spring Data JPA를 위한 레포지토리 인터페이스를 관리합니다.
- service: 비즈니스 로직을 통해 데이터를 처리하고 가공하는 역할을 수행합니다.

<br/>

<details>
<summary><strong>auth-server 구조도</strong></summary>
<div markdown="1">

```
.auth-server
├── docker-compose.auth.yml
├── .env
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── yhkim
    │   │           ├── AuthServerApplication.java
    │   │           └── domain
    │   │               ├── auth
    │   │               │   ├── JwtTokenProvider.java
    │   │               │   ├── TokenType.java
    │   │               │   ├── UserDetailsImpl.java
    │   │               │   ├── config
    │   │               │   │   └── WebSecurityConfig.java
    │   │               │   ├── controller
    │   │               │   │   └── AuthController.java
    │   │               │   ├── dto
    │   │               │   │   ├── JwtTokenInfo.java
    │   │               │   │   └── ReissueTokenResponse.java
    │   │               │   ├── entity
    │   │               │   │   └── RefreshToken.java
    │   │               │   ├── filter
    │   │               │   │   └── JwtAuthenticationFilter.java
    │   │               │   ├── repository
    │   │               │   │   └── RefreshTokenRepository.java
    │   │               │   └── service
    │   │               │       ├── AuthService.java
    │   │               │       ├── AuthServiceImpl.java
    │   │               │       └── UserDetailsServiceImpl.java
    │   │               └── user
    │   │                   ├── controller
    │   │                   │   └── UserController.java
    │   │                   ├── dto
    │   │                   │   ├── DeleteUserResponse.java
    │   │                   │   ├── LoginUserRequest.java
    │   │                   │   ├── LoginUserResponse.java
    │   │                   │   ├── SignupUserRequest.java
    │   │                   │   ├── SignupUserResponse.java
    │   │                   │   └── UserDetailResponse.java
    │   │                   ├── entity
    │   │                   │   └── User.java
    │   │                   ├── repository
    │   │                   │   └── UserRepository.java
    │   │                   └── service
    │   │                       ├── UserService.java
    │   │                       └── UserServiceImpl.java
    │   └── resources
    │       └── application.yml
    └── test
        ├── java
        └── resources
```

</details>

<br/>

### 3. resource-server

자원을 담당하는 서버입니다.

<b>도메인</b>
<br/>

- order: 주문 관련 기능
- product: 상품 관련 기능

<br/>

<b>도메인의 하위 패키지</b>
<br/>

위와 같습니다.

<br/>

<details>
<summary><strong>resource-server 구조도</strong></summary>
<div markdown="1">

```
.resource-server
├── docker-compose.resource.yml
├── .env
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── yhkim
    │   │           ├── ResourceServerApplication.java
    │   │           └── domain
    │   │               ├── order
    │   │               │   ├── controller
    │   │               │   │   └── OrderController.java
    │   │               │   ├── dto
    │   │               │   │   ├── CreateOrderRequest.java
    │   │               │   │   ├── GetOrderRequest.java
    │   │               │   │   ├── Links.java
    │   │               │   │   ├── OrderDetailResponse.java
    │   │               │   │   └── UpdateOrderRequest.java
    │   │               │   ├── entity
    │   │               │   │   ├── Order.java
    │   │               │   │   ├── OrderStatus.java
    │   │               │   │   └── OrderType.java
    │   │               │   ├── repository
    │   │               │   │   └── OrderRepository.java
    │   │               │   ├── service
    │   │               │   │   ├── OrderService.java
    │   │               │   │   └── OrderServiceImpl.java
    │   │               │   └── util
    │   │               │       └── OrderNumberGenerator.java
    │   │               └── product
    │   │                   ├── dto
    │   │                   ├── entity
    │   │                   │   ├── Product.java
    │   │                   │   ├── ProductCode.java
    │   │                   │   └── ProductPrice.java
    │   │                   └── repository
    │   │                       ├── ProductPriceRepository.java
    │   │                       └── ProductRepository.java
    │   └── resources
    │       ├── application.yml
    │       └── data.sql
    └── test
        ├── java
        └── resources
```

</details>

<br/>

## 요구사항 달성도

| 기능              | 상태    |
|-----------------|-------|
| 회원가입            | 완료    |
| 로그인             | 완료    |
| 로그아웃            | 미완료   |
| 회원 정보 조회        | 완료    |
| 회원 정보 수정        | 완료    |
| 회원 탈퇴           | 완료    |
| AccessToken 재발급 | 완료    |
| 주문 등록           | 완료    |
| 주문 취소           | 완료    |
| 주문 상태 수정        | 완료    |
| 주문 조회           | 일부 구현 |

*GRPC 통신 미완료
