CS16. git

- 다음과 같은 명령어를 지원하는 mit를 구현한다.
    - list : 디렉터리를 탐색해서 파일을 출력한다 (1단계)
    - hash : 디렉터리에 포함된 파일들 hash를 출력한다 (2단계)
    - zlib : 디렉터리에 포함된 파일들을 zlib로 압축한다 (3단계)

DirectoryScanner.java (list 구현)

Masters 폴더안에 있는 파일의 이름과 크기를 출력한다.

SHA256FileNameHasher.java (hash 구현)

Masters 폴더안에 존재하는 파일의 sha256 해시값을 얻어낸다.

ZlibFileZipper.java (zlib 구현)

Masters 폴더안에 존재하는 파일을 압축한다.
