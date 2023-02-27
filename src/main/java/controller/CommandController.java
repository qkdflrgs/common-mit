package controller;

import domain.command.Command;
import domain.file.FileContent;
import domain.hashcode.HashCode;
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
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }

            FileContent fileContent = new FileContent(file);
            HashCode hashCode = new HashCode(fileContent.getContent(), "SHA-256");
            OutputView.printHash(file.getName(), hashCode.getCode());
        }
    }

    private void zlib(File[] files) {

    }

    private void quit() {
        OutputView.printQuit();
    }
}
