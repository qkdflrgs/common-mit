import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Mit {

    public static void list(String userPath) {
        String defaultPath = System.getProperty("user.dir");
        String path = defaultPath + userPath;
        File file = new File(path);
        String[] ls = file.list();
        Arrays.stream(ls).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (!(command = scanner.nextLine()).isEmpty()) {
            if (command.startsWith("mit list")) {
                String[] commandTokens = command.split(" ");
                String path = "";
                if (commandTokens.length > 2) {
                    path = commandTokens[2];
                }
                list(path);
            }
        }
    }
}
