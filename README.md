# common-mit
## CS16 공통 프로젝트 저장소
### 1. 디렉토리 내 모든 파일 및 디렉토리 출력하기
- `printListOfFiles(String directoryPath)` : 디렉토리 탐색 후 내부 파일 및 디렉토리 출력
    1. `File` 생성자에 경로를 매개변수로 넣어 `File`객체를 생성 후, `listFiles()` 메소드를 통해 내부 파일 및 디렉토리들을 `File`객체로 배열에 넣음.

        ```java
        File[] files = new File(directoryPath).listFiles();
        ```

    2. for 반복문으로 File객체 요소를 탐색하며 정해진 포멧에 맞게 출력함
        1. `file.isFile()` 메소드로 파일 또는 디렉토리 여부를 확인함
        2. `file.getName()` 메소드로 파일 또는 디렉토리의 이름을 구함
        3. `file.length()` 메소드로 파일 또는 디렉토리의 크기를 구함. byte 크기로 계산되기 때문에 KB로 변환하기 위해서 1,000을 나눔

            ```java
            for (File file : files) {
                String kind = file.isFile() ? "파일" : "디렉토리";
                System.out.printf("<%s> %s %dKB\n", kind, file.getName(), file.length() / 1000);
            }
            ```

    3. 올바른 경로가 아니여서 File 객체가 `null`인 경우 예외처리를 함

### 2. 디렉토리 내 모든 파일 및 디렉토리 해시값 출력하기

### 3. 디렉토리 내 모든 파일 및 디렉토리 압축하기