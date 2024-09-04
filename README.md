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
