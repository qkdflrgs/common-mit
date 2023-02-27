package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        Mit mit = new Mit();
        List<String> result;
        while (true) {
            Command command = inputView.requestCommand();
            if (command.getCommandType() == CommandType.CLOSE) {
                break;
            }
            switch (command.getCommandType()) {
                case LIST:
                    result = mit.listOfPath(command.getDir());
                    break;
                default:
                    result = new ArrayList<>();
            }
            print(result);
        }
    }

    private static void print(List<String> result) {
        result.forEach(System.out::println);
        System.out.println();
    }
}
