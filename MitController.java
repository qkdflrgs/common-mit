import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

public class MitController {
    Terminal terminal;
    boolean endFlag;

    public MitController() {
        this.terminal = new Terminal();
        this.endFlag = false;
    }

    public void run() throws IOException, NoSuchAlgorithmException {
        while (!endFlag) {
            Map<Command, String[]> commandMap = terminal.readUserInput();

            if (commandMap.containsKey(Command.EXIT)) break;

            if (commandMap.containsKey(Command.MIT)) {
                Optional<MitCommand> command = MitCommand.of(commandMap.get(Command.MIT)[1]);
                String directory = commandMap.get(Command.MIT)[2];

                if (!isValidCommand(command)) continue;

                command.get().run(directory);
            }
        }
    }

    private boolean isValidCommand(Optional<MitCommand> of) {
        if (of.isEmpty()) {
            System.out.println("[Exception] 유효하지 않은 명령어입니다. (list/hash/zlib) 다시 입력하세요.");
            return false;
        }
        return true;
    }

}
