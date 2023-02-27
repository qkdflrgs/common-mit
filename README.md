# common-mit

CS16 공통 프로젝트 저장소

# CS16. 버전관리와 PR

## ⭐️ 학습키워드: `#commit` `#SHA` `#git` `#zlib` `#Pull Request` `#file` `#PR`

## ⏰ 학습 계획

- 루카스 배경지식 학습하기
- 버전 관리 시스템에 대해 알아보기
- git 관련 용어 학습하기
- 좋은 커밋 메세지 작성하기 위한 요령

## 📚 학습 정리

### 1) git 관련 용어 정리

(1) 저장소 관련 용어

- local
- remote
- init
- clone

(2) 상태 관리 용어

- working directory
- staging area
- git repository

(3) 파일 관련 용어

- Untracked
- Unmodified
- Modified
- Staged

<img width="1440" alt="git-workflow" src="https://user-images.githubusercontent.com/76121068/221515533-29dac77d-95b0-475c-ac8f-ab5f439ff2c2.png">

(4) Git의 객체들(Objects)

- Commit
- Tree
- Blob
- Tag

(5) Git 브랜치

- branch
- merge

### 2) Hash와 Map

### 3) Git 명령어들

### 4) PR 운영 방식

## 🗂️ 좋은 CL 설명문 작성법

CL 설명문이란 코드에 어떤 변화가 있고 왜 그런 변화가 필요한지 설명하는 기록물이다. 좋은 CL문 작성은 언제나 중요하다. 항상 작성 전에 1분 정도 고민하고 작성하는 습관을 들이자. 미래의 나와 나의 코드를 볼 다른 많은 개발자들을 위해서 제대로 작성하자!

> `CL`: ‘Change List’의 약어, 소스 코드 관리에 반영되었거나 리뷰를 받고 있는 코드 집합체 단위

### 1) 첫 줄

- 무엇을 했는지를 간략히 설명한다.
- 명령문 형태의 완전한 문장으로 작성한다.
- 첫 줄 이후에는 줄바꿈을 한 번 한다.

CL 설명문의 첫 줄은 전체 내용의 요약이 되므로 최대한 간결하고 명확하게 작성되어야 한다. 나중에 코드 이력을 찾아보는 개발자가 한 눈에 알아볼 수 있게 작성되어야 한다.

### 2) 본문에는 유용한 정보를 담는다.

**“어떤”** 문제를 **“어떻게”** 해결했는지를 적고 **“왜”** 이것이 최선의 방법인지 설명을 적는다. 그리고 코드에 대한 설명도 적고 배경설명과 추후 발전 방향도 포함될 수 있다. 해결책에 기술적인 결점이 있다면 이 또한 포함한다. 이슈 트래킹 넘버나 참고 문서를 덧붙여도 좋다. 작고 사소한 CL이라도 배경 설명을 꼭 한다.

### 안 좋은 CL 설명문 예시

“Fix bug”는 설명문으로는 매우 빈약하다. 어떤 버그인가? 버그를 고치기 위해 어떤 일을 했는가?

> 아래와 같은 설명문은 모두 안 좋은 예시이다.
>
> - “Fix build”
> - “Add patch.”
> - “Moving code from A to B.”
> - “Add convenience functions.”

짧고 디테일하지 않은 설명문은 유용한 정보를 제공해주지 못한다.

### 좋은 CL 설명문 예시

### Functionality change

예시:

> rpc: remove size limit on RPC server message freelist.
>
> Servers like FizzBuzz have very large messages and would benefit from reuse.
> Make the freelist larger, and add a goroutine that frees the freelist entries
> slowly over time, so that idle servers eventually release all freelist
> entries.

처음 몇 개의 단어들에는 CL이 무엇을 하는지 적혀있다. 나머지 설명에는 그 문제를 어떻게 해결했는지, 왜 이것이 좋은 해결책인지, 그리고 구체적인 실행에 대한 추가 정보가 적혀있다.

### 3) CL을 제출하기 전에 설명문 검토하기

코드 리뷰를 거치면서 꽤 많은 것이 바뀌었을 수도 있다. 최종적으로 코드를 머지하기 전 설명문에 변경 사항이 반영되어 있는지 꼭 확인한다.

### 4) 작게 쪼개기

- 작다는 것의 기준은 무엇인가?
  일반적으로 하나의 CL에는 **하나의 독립적인 변화**가 있어야만 한다.
  - 최소한으로 단 하나만 포함시킨다. 기능(feature) 중 일부만을 하나의 CL로 만든다. 하지만 리뷰어가 알아야하는 모든 정보를 담는다. CL이 머지된 후에도 프로그램은 잘 돌아가야한다.

## 💻 미션. mit 명령어

### 🥲 미션 수행과정

## 📜 참고자료

- [https://soojin.ro/review/cl-descriptions](https://soojin.ro/review/cl-descriptions) (좋은 CL 설명문 작성법)
- [http://rogerdudler.github.io/git-guide/index.ko.html](http://rogerdudler.github.io/git-guide/index.ko.html) (git 간편 안내서)
- [https://git-scm.com/book/ko/v2](https://git-scm.com/book/ko/v2) (Pro Git 2nd edition)
- [https://opentutorials.org/course/3838](https://opentutorials.org/course/3838) (생활코딩 - GITn)
- [https://it-eldorado.tistory.com/4](https://it-eldorado.tistory.com/4) (Git 내부 동작원리 이해)
- [https://blog.outsider.ne.kr/1505](https://blog.outsider.ne.kr/1505) (checkout vs switch/restore)
