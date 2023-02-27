package src;

import java.util.Scanner;

public class Input {
    public static String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        scanner.close();
        return command;
    }
}