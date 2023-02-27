# CS16

## 버전관리와 PR 🎯

### 체크리스트

1. git 명령어 공부
2. pull request 실습
3. 구현 (일단은 gpt 코드로! git과 pull request가 더 중요하다)

### 학습정리

# VCS CVCS DVCS

## 형상관리란?

- 버전 관리
- 파일의 변화를 시간에 따라 기록해두었다가 특정 시점 버전을 나중에 다시 꺼내올 수 있다

## local VCS

- 아주 간단한 DB를 사용해서 파일의 변경 정보를 관리
- RCS(Revision Control System)은 기본적으로 Patch Set(파일에서 변경되는 부분)을 관리하며, 이 Patch Set은 특별한 형식의 파일로 저장
- 일련의 Patch Set을 적용해서 모든 파일을 특정 시점으로 되돌릴 수 있다

## CVCS

- CVS, SVN, Perforce 등이 있다
- 파일을 관리하는 서버가 별도로 있고 클라이언트가 중앙 서버에서 파일을 받아서 사용(checkout)한다.
- 만약 서버가 한 시간 동안 다운되면 그동안 아무도 다른 사람과 협업할 수 없고 사람들이 하는 일을 백업할 방법도 없다.
- 중앙 DB가 있는 하드디스크에 문제가 생기면 프로젝트의 모든 히스토리를 잃는다.

## DVCS

- GIT, Mecurial 등이 있다.
- 클라이언트가 단순히 파일의 마지막 스냅샷을 checkout하지 않는다. 그냥 저장소를 히스토리와 더불어 전부 복제한다.
- 서버에 문제가 생기면 이 복제물로 다시 작업을 시작할 수 있다.
- 클라이언트 중에서 아무거나 골라도 서버를 복원할 수 있다.
- Clone은 모든 데이터를 가진 진정한 백업이다.
- 대부분의 DVCS 환경에서는 리모트 저장소가 존재한다.

# git 기본적인 명령어

[http://rogerdudler.github.io/git-guide/index.ko.html](http://rogerdudler.github.io/git-guide/index.ko.html)

저장소 만들기

- git init

**저장소 받아오기**

로컬 저장소 복제

- git clone /로컬/저장소/경로

원격 서버 저장소 복제

- git clone 사용자명@호스트:/원격/저장소/경로

**작업의 흐름**

- Working directory 실제 파일들
- Index (stage): 준비 영역의 역할
- Head: 최종 확정(commit)본

**추가와 확정**

변경된 파일은 add를 통해 인덱스에 추가

- git add <파일 이름>

변경 내용을 확정

- git commit -m “이번 확정본에 대한 설명”

**변경 내용 발행(push)하기**

commit된 변경 내용은 로컬 저장소의 HEAD에 있다. 이 변경 내용을 원격 서버로 발행

- git push origin master

만약 기존에 있던 원격 저장소를 복제한 것이 아니라면, 원격 서버의 주소를 git에게 알려줘야 한다.

- git remote add origin <원격 서버 주소>

**가지(branch)치기**

저장소를 새로 만들면 기본으로 master 가지가 만들어짐. 이제 다른 가지를 이용해서 개발을 진행하고, 나중에 개발이 완료되면 master 가지로 돌아와 병합하면 된다.

“feature_x”라는 이름의 가지를 만들고 가라타기

- git checkout -b feature_x

master 가지로 돌아오기

- git checkout master

가지 삭제하기

- git branch -d feature_x

새로 만든 가지를 원격 저장소로 전송하기 전까지 다른 사람들이 접근할 수 없다

- git push origin <가지이름>

**갱신과 병합(merge)**

로컬 저장소를 원격 저장소에 맞춰 갱신하기. 원격 저장소의 변경 내용이 로컬 작업 디렉토리에 받아지고(fetch), 병합(merge)된다.

- git pull

다른 가지에 있는 변경 내용을 현재 가지에 병합하기

- git merge <가지 이름>

충돌(conflicts)이 발생할 수 있다. 충돌을 직접 수정해서 아까의 파일을 병합하라고 git에게 알려주기

- git add <파일 이름>

**꼬리표(tag) 달기**

확정본 식별자 얻기

- git log

확정본에 꼬리표 1.0.0 달기

- git tag 1.0.0 <확정본 식별자>

**로컬 변경 내용 되돌리기**

로컬의 변경 내용을 변경 전 상태(HEAD)로 되돌리기. 다만, 이미 인덱스에 추가된 변경 내용과 새로 생성한 파일은 그대로 남는다.

- git checkout -- <파일 이름>

**로컬에 있는 모든 변경 내용과 확정본을 포기하고 원격 저장소의 최신 이력을 가져와 로컬 master가지가 그 이력을 가리키도록 할 수 있다.**

- git fetch origin
- git reset --hard origin/master
