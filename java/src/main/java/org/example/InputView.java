package org.example;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Command requestCommand() {
        System.out.println("Mit 명령어를 사용하세요.");
        String commandAndPath = scanner.nextLine();
        if (commandAndPath.equals(CommandType.CLOSE.name().toLowerCase())) {
            return new Command(CommandType.CLOSE, null);
        }
        if (!isRightCommandFormat(commandAndPath)) {
            System.out.println("명령어를 잘못 입력했습니다.");
            return requestCommand();
        }
        String[] commandParts = commandAndPath.split(" ");
        String command = commandParts[1];
        if (!isRightCommandType(command)) {
            System.out.println("정확한 명령어가 아닙니다.");
            return requestCommand();
        }
        String path = commandParts[2];
        if (!isDirectory(path)) {
            System.out.println("정확한 directory 주소가 아닙니다.");
            return requestCommand();
        }

        return Command.createNew(command, path);
    }

    private boolean isRightCommandType(String command) {
        CommandType[] values = CommandType.values();
        String s = CommandType.LIST.name().toLowerCase();
        return Arrays.stream(values)
            .anyMatch(commandType -> commandType.name().toLowerCase().equals(command));
    }

    protected boolean isRightCommandFormat(String commandAndPath) {
        return Pattern.matches("mit .+ /.+(/.+)*", commandAndPath);
    }

    private boolean isDirectory(String path) {
        File dir = new File(path);
        return dir.isDirectory();
    }


}
