# common-Mit
CS16 공통 프로젝트 저장소

## 버전관리 시스템

시스템의 종류
1. VCS 로컬 방식 : RCS, SCCS
2. CVCS 중앙 서버 방식 : Subversion(SVN), CVS, Perforce, ClearCase, TFS
3. DVCS 분산 저장소 방식 : Git, Mercurial, Bitkeeper, SVK, Darcs

로컬 VCS 방식
- 서버 없이 로컬 컴퓨터 내에서 버전을 관리하는 방식, 간단한 DB로 구현이 가능하므로 개인 프로젝트에 적합하나, 협업에서 사용하기 어렵다.

CVCS?
프로젝트 파일이 서버에서만 위치하며, 버전이 일관적이며 관리가 쉽다는 장점.
단점 : 네트워크 문제가 생길 경우 파일 업/다운로드가 불가능
커밋 시에 수정사항이 바로 반영되므로 다른 개발자에게 안좋은 영향을 줄 수 있다.

DVCS?
로컬 저장소에 프로젝트 파일을 1차적으로 저장한다.
네트워크 문제가 발생해도 사용자 간 파일을 다양한 방법으로 주고받을 수 있다.
로컬 저장소를 각자 보유하기 때문에 원격 저장소에 저장하기 전에 쉽게 수정할 수 있다.

## 키워드 정리
Remote
1. 외부 깃헙 저장소가 있는 url에 대한 alias를 만들어 관리하기 위한 명령어
2. 원격 저장소를 의미

Local
1. Local 저장소를 의미

init
- 빈 git Repository를 생성하거나, 이미 있는 repository를 초기화
- .git이라는 하위 폴더가 생성되어 저장소에 대한 git 정보가 저장된다.

clone
- git 원격 저장소를 복제하여 로컬에 생성

repository
- 소스 코드, 이미지, 문서 등 파일이 저장되어 있는 디렉토리로 버전 관리를 위해 저장되는 저장소를 의미한다.
- 개인, 팀 단위로 관리되며 버전 관리 및 추적을 위한 여러 기능이 제공됩니다.

staging area
- 변경사항을 커밋하기 전에 임시로 저장하는 공간, 변경 사항 중 실제 커밋할 파일들을 선택하는 작업을 수행한다.
- staging area를 통해서 변경 파일을 일괄 커밋하지 않고, 변경 사항을 하나씩 검토해서 커밋할 파일을 선택할 수 있습니다.
- 이를 통해 변경 사항을 정밀하게 컨트롤, 변경 내용을 깔끔하게 유지할 수 있게 됩니다.
- staging area 추가 : git add
- staging area 제거 : git reset

working directory
- 개발자가 실제로 작업하는 디렉토리로 코드를 작성하거나 수정하는 곳으로 프로젝트의 모든 파일이 포함됩니다.
- Working directory는 Git repository와는 다르며, 따라서 working directory의 수정사항과 repository를 비교해서 변경 사항 추적이 가능합니다.
- git status 명령어로 working directory에서 파일 변경 사항을 확인 가능합니다.

### 파일 상태
Git의 파일 상태는 크게 Untracked, Modified, Staged, Unmodified 4가지로 나뉩니다.
Git Status 명령어를 통해서 Working Directory와 Staging Area의 파일 상태를 보여주며, 이를 통해 Git이 어떤 파일들을 추적하고 있는지, 어떤 파일들이 변경되었는지, 어떤 파일들이 Staging Area에 추가되었는지 등을 확인할 수 있습니다.
"Changes not staged for commit"라는 메시지와 함께 "modified" 상태의 파일이 나열됩니다.
이어서 "Changes to be committed"라는 메시지와 함께 "staged" 상태의 파일도 나열됩니다.

![img.png](img.png)

untracked
- 변경사항을 추적하지 않는 파일을 의미
- git add로 staging area에 추가되지 않은 파일은 추적되지 않습니다.
- 버전 관리 대상에서 제외하기 위해 .gitignore에서 이름, 확장자 명시가 가능합니다.

unmodified
- 변경사항이 없는 파일을 의미, 이전 버전과 비교해서 변경된 사항이 없으면 Git에서 변경 사항 추적을 하지 않습니다.

modified
- 파일이 수정되어 이전 버전과는 다른 상태이지만, 아직 staging area에 추가되지 않아 커밋할 준비가 되지 않은 상태입니다.

staged
- 파일이 수정되었으며, Staging Area에 추가된 상태를 의미합니다.
- 이전 버전과 비교하여 변경된 사항이 있으며, Git이 해당 파일의 변경사항 추적을 하고 있으며, Staging Area에 추가되어 커밋할 준비가 된 상태입니다.

![img_1.png](img_1.png)

### Git ignore 작성

```
# 확장자가 .a인 파일 무시
*.a

# 윗 라인에서 확장자가 .a인 파일은 무시하게 했지만 lib.a는 무시하지 않음
!lib.a

# 현재 디렉토리에 있는 TODO파일은 무시하고 subdir/TODO처럼 하위디렉토리에 있는 파일은 무시하지 않음
/TODO

# build/ 디렉토리에 있는 모든 파일은 무시
build/

# doc/notes.txt 파일은 무시하고 doc/server/arch.txt 파일은 무시하지 않음
doc/*.txt

# doc 디렉토리 아래의 모든 .pdf 파일을 무시
doc/**/*.pdf
```

### 변경 사항 추적 - git diff
- Working directory와 Staging Area를 비교해서 차이점을 보여준다.
- --staged 옵션을 사용하면 Staging Area와 최근 커밋을 비교해서 보여준다.

### 파일 삭제 - git rm
Git에서 파일을 제거하려면 git rm 명령으로 Tracked 상태의 파일을 삭제한 후에(정확하게는 Staging Area에서 삭제하는 것) 커밋해야 한다.
이 명령은 워킹 디렉토리에 있는 파일도 삭제하기 때문에 실제로 파일도 지워진다.

### 파일 이름 변경 - git mv
이 명령으로 파일 이름을 바꿔도 되고 mv 명령으로 파일 이름을 직접 바꿔도 된다. 단지 git mv 명령은 편리하게 명령을 세 번 실행해주는 것 뿐이다.

```
$ mv README.md README
$ git rm README.md
$ git add README
```

## Git과 내부 객체들
- Git은 history를 위한 자료구조가 별도 존재하지 않는다.
- 대신, 부모 커밋을 참조하고 있기 때문에 재귀적으로 순회하면서 탐색하는 것.

![img_2.png](img_2.png)

![img_3.png](img_3.png)

### Commit
- Git에서 가장 중요한 객체
    - 부모 커밋에 대한 참조
    - 커밋 메시지
    - 트리(Tree)
      일종의 불변 객체, Immutable하다.

### Tree
- 커밋 당 하나 이상의 트리를 포함함
- 파일 시스템의 디렉토리와 유사
- 트리는 서브 트리 또는 BLOB 객체를 포함

### BLOB
- BLOB : Binary Large Object
- 파일 객체
- 일종의 불변 객체
- SHA1 체크섬을 이용해서 파일 내용을 식별 가능

### Branch
- 특정 커밋에 대한 참조

### Stage
- 커밋을 준비하는 공간
- 워킹 트리의 파일을 add 명령으로 스테이지에 개별 추가
- 스테이지 내용 전체를 이용해 트리를 생성
- 생성된 트리로 커밋을 만든다.

