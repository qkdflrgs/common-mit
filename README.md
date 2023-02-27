# common-mit
CS16 공통 프로젝트 저장소

### Git 저장소

local : 현재 컴퓨터에 위치한 git 저장소

remote : 다른컴퓨터나 원격서버에 위치한 git 저장소

명령어로서의 git remote : 현재 git 저장소에 연결된 원격 저장소를 관리하기 위한 명령어이다.
```agsl
git remote add <원격 저장소 이름> <원격 저장소 URL>: 새로운 원격 저장소를 현재 git 저장소에 추가합니다.
git remote remove <원격 저장소 이름>: 현재 git 저장소에서 특정 원격 저장소를 삭제합니다.
git remote rename <기존 이름> <새로운 이름>: 현재 git 저장소에서 원격 저장소의 이름을 변경합니다.
```

init : 현재 위치한 디렉토리에 git 저장소를 만들기 위한 명령어. 이후 생성되는 파일들은 git으로 관리할 수 있다.

clone : 원격저장소의 내용을 로컬 저장소로 복제하는 명령어.

### git에서 작업을 수행하는 영역

git repository : 소스 코드가 포함된 여러개의 branch가 모여있는 공간. 파일들이 변경된 이력 별로 구분되어서 저장되어있다.

staging area : working directory의 파일들 중, 수정 후 add 되어 stage에 올라온 파일들의 공간. 개발자는 이곳에서 git repository에 올라갈 파일을 지정해서 commit 할 수 있다.

    commit 명령이 내려오면 stage의 내용으로 트리를 만들어서 그 트리를 commit한다.

working directory : 개발자가 작업하는 공간. 수정 후 git add 명령어를 통해서 파일을 staging area로 옮겨 이후 commit에 이용한다.


### git에서 파일들의 상태 종류

Untracked : git이 인식하지 못하고 있는 파일. 새로 생성된 파일, git에 올리지 않은 파일이에 해당된다.

Tracked : git이 인식하고 있는 파일

아래는 Tracked이자 ~~ 상태인 파일들

- Unmodified : git 저장소에서 관리되고 있고, working directory에서 더이상 수정이 없었던 파일.

- Modified : git 저장소에서 관리되는 파일이고, working directory에서 수정이 있었던 파일이다. 하지만 아직 staging area에 포함되지 않은 파일이므로 git repository에 commit 하고 싶다면 add를 통해 staging area에 올려야한다.

- Staged : staging area에 포함된 파일이다. 이후에 원하는 파일을 선택해서 commit 할 수 있다.

---

## Git의 객체

- Commit
- Tree
- BLOB

### Commit

특정 시점의 스냅샷

- 참조 : 부모 커밋에 대한 참조를 갖는다.
- 메시지 : commit 시에 등록한 메시지를 갖는다.
- 트리 : commit은 트리의 구조를 갖는다.

그리고, Commit은 불변객체이다. Commit 할때마다 새로운 객체를 생성한다.

### Tree

- Commit 당 하나 이상의 트리를 포함하는데, 이것은 리눅스 파일시스템의 디렉토리와 유사하다.
- 트리는 서브 트리 혹은 BLOB 객체를 포함한다.

### BLOB

Binary Large OBject

- 개발자가 생성한 파일들이 전부 BLOB이 된다.
- 불변객체이다. 개발자가 파일을 수정하면 새로운 BLOB이 생성된다.
- SHA1 CHECKSUM을 통해 파일 내용을 확인한다.

## Branch

어떤 특정 커밋에 대한 참조. 브랜치란 이 커밋에서 뻗어나온 가지라고 생각하면 된다.

---

git log -> 최근의 commit이 부모 commit 을 참조하기때문에 따라 올라가다보면 로그를 작성할 수 있을것이다.

git push : 브랜치의 commit 중에 remote(원격 저장소)에 없는 commit을 동기화 시키는 명령


---

PR(Pull Request) : 보통 오픈소스 프로젝트에서 많이 사용된다. 원본 소스르 복제해서 사용자가 작업하다가, 좋은 수정사항일 경우 원본 개발자에게 수정요청을 보내는것을 의미한다.

간단한 흐름은 아래와 같다

- 원본 프로젝트를 fork를 통해 복제해서 내 git 저장소로 가져온다.

- fork해서 복제된 프로젝트를 clone을 통해 내 로컬 저장소로 가져온다.

- 로컬 저장소에서 작업한 파일이 원본 저장소에도 반영되기를 원한다면, PR을 보내서 원본저장소의 개발자들이 검토하고 반영하게끔 할 수 있다.

---

Git 명령어

저장소 생성

add, commit, push

git reset

변경점을 commit하지 않고 임시로 저장 stash

변경점 저장하기 git diff, git add

커밋 되돌리기

변경점 묶어 관리 branch

변경점 동기화
dadfafadfdaf