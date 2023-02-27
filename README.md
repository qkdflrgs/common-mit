# common-mit
## 명령어

```tex
$ mit 명령어 디렉토리
```

## 구현할 기능

- 사용자가 명령어 입력했을 때  명령어 검증하는 기능
    - 잘못된 명령어 입력시 이유를 출력하고 다시 입력받게 한다.
- list 명령어 입력시 해당 디렉토리의 모든 파일들을 출력하는 기능
- hash 명령어 입력시 해당 디렉토리의 모든 파일 해시값 출력하는 기능
- zlib 명령어 입력시 해당 디렉토리 파일들을 각각 압축하는 기능
    - 압축한 파일확장자는 .z이다.

## 실행 결과

```tex
// 파일 경로는 사용자 홈 디렉토리 기준입니다.
$ mit list /Desktop/masters/cs/cs16/src/test/resources
b.swift 1.50KB
c.js 6.77KB
a.java 1.07KB

$ mit hash /Desktop/masters/cs/cs16/src/test/resources
b.swift = f40a6e77ce9160310a5f6ef36f4bc5eb00d50095fbb55490b8eaca039d198428
c.js = dcdcac46d4c233d169eff7fe8abf28989f470a414f8fa10e9133e473d5eae191
a.java = d8121be5c98622159556ac0cad7be70666d6dacd248b81cd7088eefd6fe68c0e

$ mit zlib /Desktop/masters/cs/cs16/src/test/resources
b.zip 0.31KB
c.zip 0.11KB
a.zip 0.33KB
```

