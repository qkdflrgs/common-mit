package org.example;

import java.io.File;

public class Command {

    private final CommandType commandType;
    private final File dir;

    public Command(CommandType commandType, File dir) {
        this.commandType = commandType;
        this.dir = dir;
    }

    public static Command createNew(String command, String path) {
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        return new Command(commandType, new File(path));
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public File getDir() {
        return dir;
    }
}
