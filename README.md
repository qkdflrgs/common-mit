## 깃(Git)

### 저장소 관련

- remote: 로컬 저장소와 연결된 원격 저장소
- local: 컴퓨터 내부에 있는 저장소
- init: 새로운 깃 저장소를 만들 때 사용하는 명령어
- clone: 원격 저장소를 로컬 컴퓨터로 복제할 때 사용하는 명령어

### 상태 관리

- git repository: 깃이 관리하는 모든 파일과 버전 정보가 저장되는 장소
- staging area: 커밋을 수행하기 전에 변경 사항을 모아두는 장소
- working directory: 실제 파일들이 존재하는 디렉토리

### 파일 관련

- Untrack: 깃이 파일을 추적하지 않도록 설정한 상태
- Unmodified: 깃이 파일을 추적하고 있지만, 변경되지 않은 상태
- Modified: 깃이 파일을 추적하고 있고, 변경되었지만, 아직 Staging Area로 이동하지 않은 상태
- Staged: 깃이 파일을 추적하고 있고, 변경되었으며, Staging Area로 이동한 상태

![https://git-scm.com/book/en/v2/images/lifecycle.png](https://git-scm.com/book/en/v2/images/lifecycle.png)

이미지는 Git에서 파일의 생명주기를 보여줍니다.

- Working Directory: 프로젝트 디렉토리에서 작업하고 있는 파일들
- Staging Area: 커밋 대기중인 변경사항들
- Local Repository: 컴퓨터 내부에 있는 깃 저장소
- Remote Repository: 원격 저장소

커밋을 할 때, Working Directory에서 변경된 파일은 Staging Area로 이동된 후, Staging Area에서 커밋 대기중인 파일은 Local Repository로 이동됩니다. Local Repository에서 변경사항을 원격 저장소로 push할 수 있습니다.

저장소 관련 명령어 중에서 `remote`는 로컬 저장소와 원격 저장소를 연결시키는 명령어입니다. `local`은 로컬 컴퓨터 내부에 있는 저장소를 의미합니다. `init`은 새로운 깃 저장소를 만들 때 사용하는 명령어이고, `clone`은 원격 저장소를 로컬 컴퓨터로 복제할 때 사용합니다.

Git에서는 파일의 상태를 `Untrack`, `Unmodified`, `Modified`, `Staged`로 구분합니다. `Untrack`은 Git이 파일을 추적하지 않도록 설정한 상태입니다. `Unmodified`는 Git이 파일을 추적하고 있지만, 변경되지 않은 상태를 의미합니다. `Modified`는 Git이 파일을 추적하고 있고, 변경되었지만 아직 Staging Area로 이동하지 않은 상태입니다. `Staged`는 Git이 파일을 추적하고 있고, 변경되었으며, Staging Area로 이동한 상태입니다.

파일의 생명주기와 파일 상태를 이해하면 Git을 효과적으로 사용할 수 있습니다.

Git에서 상태를 관리하는 요소 중 하나는 `git repository`입니다. 이 요소는 Git이 관리하는 모든 파일과 버전 정보가 저장되는 장소입니다. 다른 요소로는 `staging area`와 `working directory`가 있습니다. `staging area`는 커밋을 수행하기 전에 변경 사항을 모아두는 장소이며, `working directory`는 실제 파일들이 존재하는 디렉토리입니다.

Git을 사용할 때, 파일의 상태를 파악하고 버전을 관리하는 것이 중요합니다. Git으로 버전 관리를 하면 파일을 효과적으로 관리할 수 있고, 코드의 변경사항도 추적할 수 있습니다.

위에서 설명한 내용을 바탕으로 Git을 사용할 때 주의할 점은 다음과 같습니다.

1. 파일의 상태를 파악하고 언제 어떤 변경사항을 커밋할 것인지 결정해야 합니다.
2. 커밋을 수행하기 전에 변경사항을 모아두는 `staging area`를 활용해야 합니다.
3. 원격 저장소와 로컬 저장소를 적절하게 관리하고, 변경사항을 효과적으로 push하고 pull해야 합니다.

다음은 Git을 사용할 때 유용한 명령어입니다.

- `git add`: 파일을 `staging area`에 추가합니다.
- `git commit`: `staging area`에 있는 변경사항을 `local repository`에 커밋합니다.
- `git push`: `local repository`의 변경사항을 원격 저장소로 push합니다.
- `git pull`: 원격 저장소에서 변경사항을 pull합니다.

이 외에도 다양한 명령어가 있습니다. Git을 사용할 때는 각 명령어의 사용방법을 잘 파악해야 합니다.

Git을 사용할 때 유용한 팁 중 하나는 `.gitignore` 파일을 사용하는 것입니다. 이 파일을 사용하면 Git이 추적하지 않아도 되는 파일들을 지정할 수 있습니다. 예를 들어, `.pyc` 파일이나 `.DS_Store` 파일 등은 Git이 추적하지 않아도 되므로 `.gitignore` 파일에 추가할 수 있습니다.

또한, Git에서는 브랜치를 사용해 코드를 관리할 수 있습니다. 브랜치를 사용하면 여러 명이 동시에 작업할 수 있고, 코드를 병합할 때 충돌을 최소화할 수 있습니다.

Git은 다양한 기능을 제공하므로, Git을 사용할 때는 잘 파악해두는 것이 중요합니다. Git을 사용하면 코드를 효과적으로 관리할 수 있으므로, 프로젝트를 진행할 때 Git을 적극적으로 활용해보세요.

---

## 학습 키워드

#commit

Commit은 Git 버전 관리 시스템에서 변경 사항을 저장하는 작업입니다. 이전 커밋 이후 변경된 파일들의 상태가 새로운 커밋에 기록되고, 이 커밋은 고유한 식별자인 SHA-1 해시 값으로 표시됩니다.

#SHA

SHA는 Secure Hash Algorithm의 약자로, 메시지 인증 코드 생성 등에 사용되는 암호학적 해시 함수입니다. Git에서는 SHA-1 해시 값을 사용하여 커밋을 식별합니다.

#git

Git은 분산 버전 관리 시스템으로, 소스 코드 관리뿐만 아니라 프로젝트 관리에도 사용됩니다. Git은 변경 이력을 추적하고, 다수의 개발자가 동시에 작업하는 것을 지원합니다.

#zlib

zlib은 데이터를 압축하기 위한 라이브러리로, 파일의 크기를 줄이는데 사용됩니다. zlib은 gzip, PNG, HTTP 등 다양한 프로토콜에서 사용되며, 오픈소스로 배포됩니다.

#Pull Request

Pull Request는 Git에서 사용되는 기능으로, 다른 개발자에게 자신이 작업한 코드를 검토해달라고 요청하는 기능입니다. Pull Request를 보낸 개발자는 코드 변경 사항을 설명하고, 코드 변경 내용을 비롯한 관련 파일들을 함께 보냅니다.

#file

File은 컴퓨터 시스템에서 데이터를 저장하는 데 사용되는 단위입니다. 파일은 이름과 확장자로 식별되며, 일반적으로 디렉토리 내부에 저장됩니다. 파일은 텍스트, 이미지, 비디오, 오디오 등 다양한 종류의 데이터를 포함할 수 있습니다.