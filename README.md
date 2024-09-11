## 금사파

금을 판매하고 구매하는 서비스입니다.

## Quick Start

### 1. 사전 준비 사항

- Docker 및 Docker Compose가 설치되어 있어야 합니다. (버전 20.10 이상 권장)

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

- 인증 서버 (포트 8080)

    ```shell
    ./gradlew :auth-server:bootJar
    ```

    ```shell
    java -jar ./auth-server/build/libs/auth-server-0.0.1-SNAPSHOT.jar
    ```

- 자원 서버 (포트 8081)

    ```shell
    ./gradlew :resource-server:bootJar
    ```

    ```shell
    java -jar ./resource-server/build/libs/resource-server-0.0.1-SNAPSHOT.jar
    ```

## Quick Stop

### 1. 서버 종료

서버를 종료하려면 터미널에서 다음 명령어를 실행합니다.

- 인증 서버 (포트 8080)

    ```shell
    sudo lsof -i :8080
    sudo kill -9 [PID] # [PID]는 실제 프로세스 ID로 대체
    ```

- 자원 서버 (포트 8081)

    ```shell
    sudo lsof -i :8081
    sudo kill -9 [PID] # [PID]는 실제 프로세스 ID로 대체
    ```

### 2. 데이터베이스 종료

데이터베이스를 종료하려면 다음 명령어를 사용합니다.

```shell
docker-compose -f ./auth-server/docker-compose.auth.yml down
docker-compose -f ./resource-server/docker-compose.resource.yml down
```

### [DB Diagram](https://dbdiagram.io/d/금사파-ERD-66d7bb70eef7e08f0e9a917c)

![금사파 ERD (3)](https://github.com/user-attachments/assets/3b18ae47-5266-43cd-aa6e-be77f3cdcf93)

### API 명세서

[Postman](https://documenter.getpostman.com/view/9878847/2sAXqmBQp4)
