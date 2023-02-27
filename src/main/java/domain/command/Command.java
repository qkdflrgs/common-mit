package domain.command;

import java.io.File;

public class Command {

    private final Menu menu;
    private final File[] files;

    public Command(String inputCommand) {
        validate(inputCommand);
        this.menu = Menu.of(getFewCommand(inputCommand, 1));
        this.files = getDirectory(getFewCommand(inputCommand, 2)).listFiles();
    }

    public Menu getMenu() {
        return menu;
    }

    public File[] getFiles() {
        return files;
    }

    public boolean isNotQuit() {
        return this.menu.isNotQuit();
    }

    private void validate(String inputCommand) {
        if (Menu.isQuit(inputCommand)) {
            return;
        }

        String[] splitInput = inputCommand.split(" ");
        validateCommandLength(splitInput);
        validateProgramName(splitInput[0]);
        validateDirectoryFiles(splitInput[2]);
    }

    private void validateCommandLength(String[] splitInput) {
        if (splitInput.length != 3) {
            throw new IllegalArgumentException("명령 형식이 잘못 되었습니다.");
        }
    }

    private void validateProgramName(String programName) {
        if (!programName.equals("mit")) {
            throw new IllegalArgumentException("올바른 프로그램 이름이 아닙니다.");
        }
    }

    private void validateDirectoryFiles(String filePath) {
        File directory = getDirectory(filePath);

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("디렉토리 경로가 아닙니다.");
        }
    }

    private File getDirectory(String filePath) {
        String home = System.getProperty("user.home");
        return new File(String.format("%s%s", home, filePath));
    }

    private String getFewCommand(String inputCommand, int index) {
        if (Menu.isQuit(inputCommand)) {
            return inputCommand;
        }

        String[] split = inputCommand.split(" ");
        return split[index];
    }
}