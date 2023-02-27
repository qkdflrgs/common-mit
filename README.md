# common-mit
CS16 공통 프로젝트 저장소

# mit 명령어
## mit 명령어 - 기능 요구 사항
- 파일들의 버전 관리를 위한 mit 명령어 구현

### mit 명령어 형식
```shell
$ mit 명령어 디렉토리명

$ mit list /Work/Masters/
$ mit list /Work/Masters
$ mit hash /Work/Masters/
$ mit zlib /Work/Masters/
```

### mit 명령어 종류
- `list` : 해당 디렉토리의 파일 목록을 출력
- `hash` : 해당 디렉토리의 파일들의 hash를 출력
- `zlib` : 해당 디렉토리의 파일들을 zlib로 압축

## mit 명령어 - 프로그래밍 요구 사항
- 디렉토리명은 디렉토리이름으로 끝나거나 `/`로 끝날 수 있습니다.
  - 예를 들어 `/Work/Masters/` 또는 `/Work/Masters`가 디렉토리명이 될 수 있습니다.
- mit 명령어의 디렉토리 입력이 파일이거나 디렉토리이지만 안에 파일이 없는 경우 "경고 메시지"를 출력후 다시 입력하게 합니다.
- `mit list` 명령어 실행시 출력되는 파일들의 크기는 KB 단위로 소수점 2번째자리까지 출력합니다.
  - ex) a.java 파일이 142byte인 경우 "a.java 0.14KB"와 같이 출력됩니다.
- 디렉토리 탐색시 해당 위치에 또 다른 디렉토리가 존재할때 재귀적으로 탐색하지 않습니다.
  - ex) "./Work/" 디렉토리에 Masters 디렉토리가 저장되어 있고 Masters 디렉토리에 a.java 파일이 있다면
  a.java 파일을 탐색하지 않습니다.

## mit 명령어 - 동작예시
```shell
> mit list ./Work/Masters
a.java 0.14KB
Person.java 0.35KB

> mit hash ./Work/Masters
a.java = 96831b0e7ba1a6f4ec183f2d4f011154f270050169ae24e5a9a6ede7097407a4
Person.java = b282f862b97920376e9d34d11ac27c0c2d813b61977fd4d27a91ff3b44e26fa9

> mit zlib ./Work/Masters
a.java.z 0.07KB
Person.java.z 0.0175KB

> mit list
유효하지 않은 명령어입니다. 형식 : mit list|hash|zlib 디렉터리명

> mit list ./Work
디렉토리가 비어있습니다. : ./Work

> mit list ./Work/Masters/a
입력하신 디렉토리명은 디렉토리가 아닙니다. : ./Work/Masters/a

> mit list ./Work/Masters/a.java
입력하신 디렉토리명은 디렉토리가 아닙니다. : ./Work/Masters/a.java
```