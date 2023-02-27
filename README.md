**[ pro-git 2nd ]**

[Git - 프로토콜](https://git-scm.com/book/ko/v2/Git-서버-프로토콜)

**[ 지옥에서 온 git ]**

[지옥에서 온 Git (새 수업으로 대체) - 생활코딩](https://opentutorials.org/course/2708)

**[ 지옥에서 온 git 대체 강의 ]**

[GIT1](https://opentutorials.org/module/3733)

## 기능 요구 사항

- 공통 프로젝트 저장소는 [https://github.com/codesquad-members-2023/common-mit](https://github.com/codesquad-members-2023/common-mit)
  - PR 운영 방식에 맞춰서 브랜치 생성하고 fork하는 것부터 시작한다
  - 무언가 잘못되도 git 명령으로 되돌릴 수 있는 방법이 있으니까 fork를 다시할 필요는 없다
  - PR 운영 방식에 딱 맞춰서 정상적으로 진행하는 것만이 목표는 아니고, 실수하면 해결하는 과정을 경험하는 것도 중요하다.

JS는 Node 기반 콘솔 명령 프로그램으로 sha256 이나 zlib은 node 패키지를 활용한다.

### 1. Working Directory(파일관련)

- `untracked`: 아직 tracking이 되지 않은 파일→ 기존에 존재하던 프로젝트에서 git을 초기화하거나 파일을 새로 만들면(또는 처음 저장소를 clone하면) `untracked` 상태이다.
- `tracked` : Unmodified/Modified로 나눌 수 있다. checkout된 이후 수정사항이 있지만 stage되지 않으면 modified된 상태이다.→ modified된 파일만 staging area로 옮겨갈 수 있다.

![1](https://user-images.githubusercontent.com/56246060/221575704-d0f57209-1215-4ea2-b9db-7936ea93b451.png)

### 2. Staging Area (Index)

- 커밋이 이뤄질 준비가 된 파일의 내용들이 위치하는 영역.
- tracked & staged 상태
- add 명령어를 통해 파일을 Staging Area에 올릴 수 있다.
  - 해당 변동 사항을 인덱스 영역에 반영시킬 수 있다.
- `git rm --cached`를 이용하면 unstage(Staging Area-> Working Directory)가 가능하다.
- `git diff --cached`는 index와 HEAD 사이의 변화를 보여준다.
- 실제 위치: `./git/index`
- 인덱스 파일(.git/index)에는 커밋이 이뤄질 준비가 된 파일의 내용들 각각에 대하여 그 파일명과 해당 파일의 내용을 담고 있는 Blob 파일의 주소(이름)가 기록된다.

### 3. 저장소

- 실제위치 : .git/objects/
- 깃이 버전 관리를 하기 위해 필요로 하는 데이터들을 저장하는 곳.
- 버전관리를 시작한 시점부터 현재 시점까지 관리해온 여러 버전들에 해당하는 파일들의 내용이 Blob 파일로서 이곳에 저장되어 있다.
- 이곳(저장소)에 저장된 파일들을 특별히 오브젝트 파일이라고 부르며, Blob 파일도 오브젝트 파일의 한 종류이다.

### 3. 오브젝트 파일 → .git/objects/ 폴더에 존재하는 파일.

- Blob: 버전 관리하는 파일들 각각의 내용은 깃의 저장소에서 Blob 파일의 형태로 저장된다. 파일의 내용에 SHA1이라는 해싱 기법을 적용하여 Blob 파일의 이름을 얻어내기 때문에, **내용이 같은 파일들은 모두 하나의 Blob 파일로서 저장**된다. 이러한 원리로 깃은 여러 버전에 걸쳐 존재하는 파일들의 내용을 중복 없이 관리할 수 있게 된다.
- Commit: 하나의 버전을 생성한다는 것은 하나의 Commit 파일을 만드는 것을 의미한다.  Commit 파일은 하나의 Tree 파일을 가리키게 되어 있다. 이 파일에는 **가리키고 있는 Tree 파일의 주소(이름)와 직전 버전에 해당하는 Commit 파일의 주소(이름)가 기록**된다.
- Tree: **커밋 시점의 파일들 각각에 대해 그 파일명과 해당 파일의 내용을 담고 있는 Blob 파일의 주소(이름)가 기록**된다. 위에서 설명했던 인덱스 파일(.git/index)과 성격이 유사하다.

![2](https://user-images.githubusercontent.com/56246060/221575712-acc7939f-9f45-4ff1-80cb-f02acafa2cce.png)

[출처](https://it-eldorado.tistory.com/4)

## 구현 사항

`process.cwd()` : 현재 작업 디렉토리를 반환합니다. 즉, `node`명령 을 호출 한 디렉토리입니다 .

`__dirname` :  JavaScript 소스 코드 파일을 포함하는 디렉토리의 디렉토리 이름을 리턴합니다.

### path.join 과 path.resolve 차이점

resolve 는 `/` 를 절대경로로 처리, join 은 상대경로로 처리한다.

### Node.js 대상 디렉토리 내의 모든 파일 읽어오기

```jsx
var fs = require("fs");

var files = fs.readdirSync(dir); // 디렉토리를 읽어온다.
```

## 참고

[[Git] 내부 동작 원리에 대한 이해](https://it-eldorado.tistory.com/4)

[Using SHA-256 with NodeJS Crypto](https://stackoverflow.com/questions/27970431/using-sha-256-with-nodejs-crypto)

[[NodeJS] Path.join()와 Path.resolve() 차이](https://velog.io/@thyoondev/Path.join와-Path.resolve-차이)

[Node.js 대상 디렉토리 내의 모든 파일 읽어오기](https://supdev.tistory.com/45)
