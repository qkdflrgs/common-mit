package view;

import java.io.File;
import java.util.Arrays;

public class OutputView {

    public static void printFilesInfomation(File[] files) {
        Arrays.stream(files)
            .forEach(file -> {
                if (file.isFile()) {
                    System.out.printf("%s %dKB\n", file.getName(), file.length());
                }
            });
    }

    public static void printQuit() {
        System.out.println("프로그램을 종료합니다.");
    }

    public static void printHash(String fileName, byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }

        System.out.printf("%s = %s\n",fileName, stringBuilder);
    }
}
