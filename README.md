## 학습계획
1. 공통 프로젝트 저장소를 fork 하고 PR을 오픈해서 mergr하는 방법을 테스트해본다.
2. git 용어 및 명령어를 학습해본다.
3. mit 명령어를 하나씩 구현하고 PR을 생성한다.
## git 용어 및 명령어
### 저장소 관련 용어
- **remote**  
  remote 저장소 : 원격 저장소, PC가 아닌 다른 원격의 저장소, github에 만든 repository가 원격 저장소이다.
- **local**  
  local 저장소 : 현재 PC에 존재하는 저장소
- **init**  
  `git init` : 로컬 저장소를 원격 저장소와 연결하기 위해 제일 처음 사용하는 명령어로 `git init`을 실행하면 현재
  디렉토리에 `.git` 디렉토리를 생성한다. 이를 저장소를 초기화한다고 하고 git 명령어를 사용할 수 있게 된다.
- **clone**  
  원격 저장소의 데이터를 로컬 저장소로 복제하는 기능으로  
  `git clone {원격 저장소 주소} [디렉토리명]` 명령어로 복제할 수 있다.
### 상태 관리 용어
- **git repository**  
git 저장소 : 커밋된 데이터들을 보관하는 저장소
- **staging area**  
변경 사항이 있는 파일들을 커밋하기 위해서 보관하는 공간  
`git add {파일명}` 명령으로 추가할 수 있다.  
- **working directory**  
현재 작업하고 있는 디렉토리. 여기서 코드를 작성하고 add 명령을 통해 staging area로 옮길 수 있다.  
### 파일 관련
- **Untracked**  
working directory에 있지만 git으로 버전관리를 하지 않는 상태  
- **Unmodified**  
신규로 파일이 추가된 상태  
- **modified**  
추가된 파일이 수정된 상태  
- **staged**  
Staging Area에 반영된 상태(`git add`로 추가한 상태)

## 미션 구현
### File 클래스 사용
```java
// 디렉토리 생성
File directory = new File("C:/CodeSquad/CS16/common-mit/");
// 파일 목록
File[] files = directory.listFiles();
// 파일 크기
long fileSize = file.length();
```