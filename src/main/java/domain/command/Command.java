package domain.command;

import java.io.File;

public class Command {

    private final Menu menu;
    private final File[] files;

    public Command(String inputCommand) {
        String[] commands = inputCommand.split(" ");
        validate(commands);
        this.menu = Menu.of(commands[1]);
        this.files = getDirectory(commands[2]).listFiles();
    }

    public Menu getMenu() {
        return menu;
    }

    public File[] getFilePath() {
        return files;
    }

    private void validate(String[] splitInput) {
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
}