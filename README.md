# common-mit

## 학습목표
- 파일 시스템을 이해하고, 소스 관리를 위해서 필요한 필수 지식을 학습할 수 있다
- 깃헙 저장소 `upstream`과 `origin`을 통해서 버전 관리를 할 수 있고, PR을 오픈해서 변경된 코드를 리뷰하는 과정을 경험한다

## 목차

---
# 기능 구현
## 기능 요구 사항
- 공통 프로젝트 저장소에서 브랜치를 생성하고 fork한다.
  - PR운영 방식에 맞춰 진행하되, 실수하면 해결하는 과정을 경험하는 것도 중요하다.
- 콘솔 명령을 위한 프로그램 템플릿으로 시작한다.
  - 자바의 경우 sha256이나 zlib 프레임워크를 활용한다.

### `mit` 명령어
- 특정 파일들 버전 관리를 위한 `mit` 명령어를 만들려고 한다.
- 명령 형식은 `mit 명령어 디렉토리명`이다.
- 명령어의 종류는 다음과 같다.
  - `list` : 디렉터리를 탐새갛여 파일을 출력한다.
  - `hash` : 디렉터리에 포함된 파일들 hash를 출력한다.
  - `zlib` : 디렉터리에 포함된 파일들을 zlib로 압축한다.

## 프로그래밍 요구사항
- [ ] 배경 지식에 PR 운영 방식에 맞춰서 자신의 브랜치에 PR을 보내야 한다.
1. list 명령어 요구사항
2. hash 명령어 요구사항
3. zlip 명령어 요구사항

---
# 구현 과정
## 사용자 입력
1. 사용자 입력 유효성 검사
   - [x] 입력 첫 단어가 `exit` 혹은 `mit` 이 아니면 다시 입력 받는다. : `Terminal` > `isMit()` , `isExit()`
     - [x] 통과시 Command 타입과 함께 입력받은 문장을 반환한다.
   - [x] `mit` 일 경우, 유효한 디렉토리 형식인지 검사한다. : 
2. 사용자 입력 커맨드 유효성 검사
   - [x] `mit` 다음 명령어가 `list`, `hash`, `zlip`이 아니라면 다시 입력 받는다. : `MitController` > `isValidCommand()`
3. 사용자 입력 커멘드 구분 구현
   - [x] enum을 이용해 각 커맨드 분류 `Command`, `MitCommand`

## list
- 자바로 특정 디렉토리 경로의 파일 목록을 가져오는 방법
  - [File](https://mine-it-record.tistory.com/432)
  - [File size](https://hianna.tistory.com/593)
- [x] 위 링크와 공식 문서를 참조하여 구현 `MitCommand` > `LIST` > `run()`
```java
    LIST("list"){
        @Override
        void run(String directory) throws IOException {
            // 디렉토리에서 전체 파일 목록을 탐색하고, 파일 크기와 파일명을 출력한다
            File dir = new File(directory);
            char idx = 'a';

            for (File file : dir.listFiles()) {
                if (file.isHidden()) continue;  // 숨김 파일은 출력 생략
                System.out.printf("%s. %s %dkb\n",idx++, file.getName(), file.length()/1024);
            }
        }
    },
```

## hash
- SHA-256이란?
  - SHA-256은 SHA(Secure Hash Algorithm) 알고리즘의 한 종류로서 256비트로 구성되며 64자리 문자열을 반환한다.
  - 해시 알고리즘 SHA-2 계열 중 하나이며, 2^256만큼 경우의 수를 만들 수 있다.
- SHA-256 참고 링크
  - [해시넷 SHA256](http://wiki.hash.kr/index.php/SHA256#cite_note-8)
  - [MessageDigest](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=javaking75&logNo=220551348645)
  - [파일 MD5, SHA-256 해시값](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=96s0907&logNo=220930824319)
  - [SHA-256 암호화 하기](https://bamdule.tistory.com/233)
- [x] `MitService`로 메서드 분리
  - [x] list 기능 이전 `MitService` > `printFileInfo()`
- [x] 위 링크를 참조하여 구현 `MitService` > `printHashes()`

```java
    public void printHashes() throws IOException, NoSuchAlgorithmException {
        char idx = 'a';

        for (File file : files.listFiles()) {
            if (file.isDirectory()) continue;   // 폴더일 경우 내용 없어서 넘기기
            if (file.isHidden()) continue;      // 숨김파일 출력하지 않기

            FileInputStream fileInputStream = new FileInputStream(file);
            System.out.printf("%s. %s = %s%n", idx++, file.getName(), sha256(fileInputStream.readAllBytes()));
        }
    }

    public static String sha256(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes);

        return bytesToHex(md.digest());
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
```

---
# ref
- 기능 구현할 때 enum을 좀 더 적극적으로 활용하고 싶어 다음을 참조하였다. 
  - [Java Enum 활용기](https://techblog.woowahan.com/2527/)
