package Mit.src.domain;


import Mit.src.service.MitService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

public enum MitCommand {
    LIST("list"){
        @Override
        public void run(String directory) throws IOException {
            // 디렉토리에서 전체 파일 목록을 탐색하고, 파일 크기와 파일명을 출력한다
            MitService service = new MitService(directory);
            service.printFileInfo();
        }
    },
    HASH("hash"){
        @Override
        public void run(String directory) throws NoSuchAlgorithmException, IOException {
            // 디렉토리에서 전체 파일 목록을 탐색하고, 각 파일 내용에 대한 sha256 해시 값을 출력한다.
            MitService service = new MitService(directory);
            service.printHashes();
        }
    },
    ZLIB("zlib"){
        @Override
        public void run(String directory) throws IOException {
            // 디렉토리에서 전체 파일 목록을 탐색하고, 각 파일을 zlib로 압축해서 .z를 붙여서 저장한다.
            MitService service = new MitService(directory);
            service.compress();
            service.printFileInfo("z");
        }
    };

    private String command;

    MitCommand(String command) {
        this.command = command;
    }

    public static Optional<MitCommand> of (String str) {
        return Arrays.stream(values()).filter(e -> e.command.equals(str)).findFirst();
    }

    public abstract void run(String directory) throws IOException, NoSuchAlgorithmException;
}
