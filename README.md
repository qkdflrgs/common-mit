# CS16.버전관리와 PR
## 학습 키워드
`#commit`, `#SHA`, `#git`, `#zlib`, `#Pull Request`, `#file`

## 체크리스트
- [x] 학습키워드 정리
- [x] 과제
- [x] 풀이

## 학습 키워드별 정리
### commit [[`위키백과`](https://ko.wikipedia.org/wiki/%EC%BB%A4%EB%B0%8B), [`boostcourse`](https://www.boostcourse.org/cs102/lecture/1427417?isDesc=false)]
저장소에 소스 코드의 일부의 최신 변경사항을 추가함으로써 이러한 변경사항을 저장소의 최상위 리비전의 일부분으로 만들어주는 것

git에서 버전을 저장하는 것을 의미

### SHA [[`위키백과`](https://ko.wikipedia.org/wiki/SHA), [`나무위키`](https://namu.wiki/w/SHA)]
Secure Hash Algorithm, 안전한 해시 알고리즘
미국 NSA가 제작하고 미국 국립표준기술연구소(NIST)에서 표준으로 채택한 암호학적 해시 함수이다.

### git [[`위키백과`](https://ko.wikipedia.org/wiki/%EA%B9%83_(%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4)),[`boostcourse`](https://www.boostcourse.org/cs102/lecture/1427416?isDesc=false)]
컴퓨터 파일의 변경사항을 추적하고 여러 명의 사용자들 간에 해당 파일들의 작업을 조율하기 위한 스냅샷 스트림 기반의 분산 버전 관리 시스템이다.

버전관리 시스템은 프로그램의 소스코드를 관리하는 프로그램을 말한다

깃은 소프트웨어 버전관리 시스템(VCS, Version Control System)의 한 종류이다.

### zlib [[`나무위키`](https://namu.wiki/w/zlib)]
Deflate 압축 알고리즘을 C언어로 구현한 라이브러리이다.

zlib와 deflate 의 차이점은, deflate는 압축 알고리즘이고, zlib 는 이걸 실제 프로그래밍 언어로 구현한 구현체라는 것이다.

원래 Deflate는 ZIP 포맷에 사용하기 위해서 만들어진 비손실 압축 알고리즘인데, 이 알고리즘이 구현되자 이를 Jean-Loup Gailly와 Mark Adler가 오픈 소스로 구현후 공개한것이 zlib이다.

압축 알고리즘계의 산업 표준이며 수많은 프로그램에 이식되어 사용되고 있다. 

GitHub의 수많은 프로젝트들에도 거의 zlib을 포함 라이브러리로 요구하는 경우가 많다.

### Pull Request
협업시에 팀원들이 함께 push한 작업물들을 로컬환경에 반영하고 합쳐 개발을 계속하고자 할 때 활용하는 요청이다

### file [[`위키백과`](https://ko.wikipedia.org/wiki/%EC%BB%B4%ED%93%A8%ED%84%B0_%ED%8C%8C%EC%9D%BC)]
의미가 있는 정보를 담는 논리적인 단위이다.
저장매체에 대하여 추상화된 정보 단위이며 운영체제는 파일 조작에 관련된 기능을 API로 제공한다.
일반적으로 파일의 이름과 확장자로 식별하며, 운영 체제에 따라 대소문자를 구별하거나 구별하지 않는다.