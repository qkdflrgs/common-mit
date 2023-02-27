package view;

import java.io.File;

public class OutputView {

    public static void printFilesInfomation(File file) {
        System.out.printf("%s %.2fKB\n", file.getName(), file.length() / 1024.0);
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
