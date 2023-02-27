package view;

import domain.command.Command;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static Command command() {
        try {
            System.out.print("\n$ ");
            return new Command(SCANNER.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.printf("%s\n\n", e.getMessage());
            return command();
        }
    }
}