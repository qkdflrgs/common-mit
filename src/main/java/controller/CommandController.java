package controller;

import domain.command.Command;
import java.io.File;
import view.InputView;
import view.OutputView;

public class CommandController {

    public void run() {
        Command command;

        do {
            command = InputView.command();
            execute(command);
        } while(command.isNotQuit());
    }

    private void execute(Command command) {
        switch (command.getMenu()) {
            case LIST -> list(command.getFiles());
            case HASH -> hash(command.getFiles());
            case ZLIB -> zlib(command.getFiles());
            case QUIT -> quit();
        }
    }

    private void list(File[] files) {
        OutputView.printFilesInfomation(files);
    }

    private void hash(File[] files) {

    }

    private void zlib(File[] files) {

    }

    private void quit() {
        OutputView.printQuit();
    }
}
