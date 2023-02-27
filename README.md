# common-mit

CS16 공통 프로젝트 저장소

### 깃 개념


|               명칭               | 개념                                                                                                          |
|:------------------------------:|:------------------------------------------------------------------------------------------------------------|
|    워킹트리 <br>(working tree)     | 워크트리, 워킹 디렉토리, 작업 디렉토리, 작업 폴더 모두 같은 뜻으로 사용. 일반적으로 사용자가 파일과 하위 폴더를 만들고, 작업 결과물을 저장하는 곳을 Git 에서는 워킹트리 라고 부른다. |
| 로컬 저장소 <br> (local repository) | Git init 명령으로 생성되는 .git 폴더가 로컬저장소이다.                                                                        |
| 원격 저장소 <br>(remote repository) | 로컬저장소를 업로드 하는 곳. 우리가 사용하는 GitHub 저장소가 원격 저장소이다.                                                             |
|Git 저장소| Git 명령으로 관리할 수 있는 폴더 전체를 일반적으로 Git 저장소라고 부른다.                                                               |   
    
---  

### checkout vs switch??  
git checkout 명령어가 여러가지 기능을 수행하기 때문에 기능별로 나누게 되었다.  
기존에는 다음 두 가지 기능을 포함하고 있었다.  
1. 브랜치 변경하기  
2. 파일 수정 복원하기  
  
```gitexclude
git checkout birdie
```  
위 명령어는 birdie 브랜치로 변경하고 싶을 때 사용한다.  

```gitexclude
git checkout -- README.md   
```
위의 명령어는 README.md의 수정사항을 취소하고 이전으로 변경하고 싶을 때 사용했다.  

이처럼 같은 명령어로 다른 기능을 수행하다보니, checkout 명령어를 나누게 되었다.  
기존의 브랜치를 변경하는 기능은 switch로, 수정사항을 취소하는 부분은 restore으로 변경하였다.  
  
```gitexclude
git switch birdie 
git restore -- README.md
```  
  
---  
  
### Git 전역 옵션 설정  
```gitexclude
git config --global user.name "유저네임"
git config --global user.email "유저이메일"
```  
<img width="651" alt="스크린샷 2023-02-27 오후 5 31 41" src="https://user-images.githubusercontent.com/115435784/221513110-7770bc35-c2b6-4002-a46e-56dced6d7856.png">
<br>
<img width="682" alt="스크린샷 2023-02-27 오후 5 32 16" src="https://user-images.githubusercontent.com/115435784/221513145-3cbd1a83-c2a1-4d31-a485-ba9bfe73c771.png">
  
