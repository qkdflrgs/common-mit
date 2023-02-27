import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Console {

    private InputView inputView = new InputView();

    public void run() {
        try {
            String command = inputView.getCommand();
            if (!command.startsWith("mit")) {
                return;
            }
            String[] commands = command.split(" ");
            switch(commands[1]) {
                case "list":
                    list(commands[2]);
                    break;
                case "hash":
                    hash(commands[2]);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void list(String path) {

        File[] files = new File(path).listFiles();
        String fileContents = Arrays.stream(files).map(f -> String.format("%s : %d KB%n", f.getName(), f.length())).reduce("", (o, n) -> o + n);
        System.out.println(fileContents);
    }

    private void hash(String path) {

    }
}
