# CS16

## 학습 계획

- [x]  학습 계획 작성
- [ ]  미션 이해하면서 학습 키워드 정리
- [x]  쪼개고 우선순위 정하기
- [ ]  구현
    - [x]  step 0: 입출력 및 명령어 파싱
    - [x]  step 1: list
    - [ ]  step 2: hash
    - [ ]  step 3: zlib
- [ ]  학습 정리

---

# 미션 쪼개기

- [x]  [step 0] 입력 받아서 명령어 만들기 - mit / 명령어 / path(String) 수준으로
- [x]  [step 0] 입출력 관리 객체, 출력용 객체 만들기
- [x]  [git] 작업 환경 조성 - local에 fork 및 test PR
- [x]  [step 1] list
    1. 시스템에 접근해서 특정 디렉토리의 entity 목록 fetch
    2. 각 파일의 용량 fetch
    3. 출력 객체 만들기
- [x]  [step 2] hash
    1. CryptoKit - hash 함수
    2. 파일에 대한 hash 구하기
- [ ]  [step 3] zlib
    1. NSData가 제공하는 기능으로 압축하기

---

# 미션

## 1단계: list

### 파일 다루기

macOS 파일 시스템과 파일 경로 다루기

[Swift로 파일 다루기](https://hcn1519.github.io/articles/2017-07/swift_file_manager)

[File System Basics](https://developer.apple.com/library/archive/documentation/FileManagement/Conceptual/FileSystemProgrammingGuide/FileSystemOverview/FileSystemOverview.html#//apple_ref/doc/uid/TP40010672-CH2-SW2)

### 방법 1

> 문자열로 파일을 가져오고 → 용량 따로 구하는 방법
> 

[contentsOfDirectory(atPath:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/filemanager/1414584-contentsofdirectory)

### 방법 2 ✅

> shallow 탐색으로 파일들에 대한 url 가져오고 url로 이름이랑 파일 정보 가져오는 방법
> 

이게 좀 더 정석 같은 방법이려나

문자열을 URL로 바꿔보자.

[fileURL(withPath:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/nsurl/1410828-fileurl)

그리고 디렉토리 하위 엔티티에 접근하자

이 중에서 파일인 친구들만 구해야 할 것이다.

[contentsOfDirectory(at:includingPropertiesForKeys:options:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/filemanager/1413768-contentsofdirectory)

파일의 URL을 통해 데이터를 생성하자

[init(contentsOf:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/nsdata/1413892-init)

자 이제 이 데이터의 크기만 구하면 된다.

잠깐… Data 타입으로 변환하지 않아도 사용할 수 있는 인터페이스가 있다고?

원하는 값을 키 타입으로 전달해서 얻는 인터페이스를 찾았다.

[resourceValues(forKeys:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/url/1780058-resourcevalues)

위 메소드는 키 셋을 전달하는 방법으로 속성에 접근한다.

키 타입은 다음과 같고 프로퍼티로 키를 제공한다.

(근데 enum같은 optionSet이 아니라 이런 식으로 구현하는 이유가 뭘까? 이런 패턴 볼 때마다 매번 궁금.)

[URLResourceKey | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/urlresourcekey)

암튼 사이즈 키에는 다른 키도 많았지만, 이게 제일 기본인 것 같아서 선택

[fileAllocatedSizeKey | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/urlresourcekey/1409814-fileallocatedsizekey)

이름은 굳이 따로 받지 않아도 이런 방법이 있었다.

[lastPathComponent | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/url/1780317-lastpathcomponent)

참고

- entity size
    
    [https://stackoverflow.com/questions/32814535/how-to-get-directory-size-with-swift-on-os-x](https://stackoverflow.com/questions/32814535/how-to-get-directory-size-with-swift-on-os-x)
    
- String → url / url → String
    
    [https://www.zerotoappstore.com/swift-url-to-string.html](https://www.zerotoappstore.com/swift-url-to-string.html)
    
- isDirectory
    
    [https://stackoverflow.com/questions/65152001/how-to-check-if-swift-url-is-directory](https://stackoverflow.com/questions/65152001/how-to-check-if-swift-url-is-directory)
    

## 2단계: hash

### URL → Data

[init(contentsOf:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/nsdata/1413892-init)

위에서 탈락한 방법인데 드디어 쓸 차례가 왔다. 얻은 data는 말 그대로 파일의 pure한 데이터이다.

### sha256

위에서 얻은 파일의 데이터(contents, 내용)를 가지고 SHA256 hash를 생성할 것이다.

[hash(data:) | Apple Developer Documentation](https://developer.apple.com/documentation/cryptokit/sha256/hash(data:))

CryptoKit 이 제공하는 SHA256 인터페이스로 쉽게 hash를 얻을 수 있었다. 다만 얻어진 hash는 String이 아니라 Digest라고 하는 특별한 결과 타입이었다.

[Introducing CryptoKit](https://www.kodeco.com/10846296-introducing-cryptokit#toc-anchor-003)

[init(describing:) | Apple Developer Documentation](https://developer.apple.com/documentation/swift/string/init(describing:)-67ncf)

스위프트 String에 알 수 없는 타입의 객체를 protocol conformation에 따라서 내부적으로 문자열로 리턴해주는 인터페이스가 있었는데, 문제는…

```
SHA256 Digest: d058287d76c9595fd7e27cee63880d87b39b284034de68a4f7a9bc9840ff0cf6
```

이렇게 description 형태의 문자열로 리턴된다는 것이다. pure 한 값을 얻을 수 없기 때문에 이걸 사용하는 건 의미 없다고 판단했다.

[How to calculate the SHA hash of a String or Data instance - free Swift 5.4 example code and tips](https://www.hackingwithswift.com/example-code/cryptokit/how-to-calculate-the-sha-hash-of-a-string-or-data-instance)

자료를 찾다보니 이렇게 포매팅하는 방법이 있었다. 위에서 사용했던 인터페이스와 결과는 동일해서 채택하기로 했다.

## 3단계: zlib