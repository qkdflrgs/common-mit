package view;

import java.io.File;
import java.util.Arrays;

public class OutputView {

    public static void printFilesInfomation(File[] files) {
        Arrays.stream(files)
            .forEach(file -> System.out.printf("%s %dKB\n", file.getName(), file.length()));
        System.out.println();
    }

    public static void printQuit() {
        System.out.println("프로그램을 종료합니다.");
    }
}
