package Mit.src.view;

import Mit.src.domain.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Terminal {
    private BufferedReader br;
    private final String TAG = "mit";
    private final String EXIT = "exit";

    public Terminal() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public Map<Command, String[]> readUserInput() throws IOException {
        System.out.print("> ");
        String[] strings = br.readLine().split(" ");

        if (isMit(strings) && isValidDirectory(strings[2])) {
            return Map.of(Command.MIT, strings);
        }

        if (isExit(strings)) {
            return Map.of(Command.EXIT, strings);
        }

        System.out.println("다시 입력하세요.");
        return readUserInput();
    }

    private boolean isValidDirectory(String directory) {
        if (directory.charAt(0)!='/' && directory.charAt(1)!='/') {
            System.out.println("[Exception] 유효하지 않은 파일 경로입니다. 다시 입력하세요.");
            return false;
        }
        return true;
    }

    private boolean isExit(String[] strings) {
        return strings[0].equalsIgnoreCase(EXIT);
    }

    private boolean isMit(String[] strings) {
        return strings[0].equalsIgnoreCase(TAG);
    }
}
