import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        enterCommand();

    }

    private static void enterCommand() {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split(" ");

        if(tokens[0].equals("mit") && tokens[1].equals("list")) {
            printList(tokens[2]);
        }
    }

    private static void printList(String path) {
        String[] tokens = path.split("/|\\\\"); // OS마다 정책이 다르기 때문에 File seperator를 위해 구분
        if(tokens[0].equals("~")) {
            tokens[0] = System.getProperty("user.home"); // ~ home 디렉토리로 변경
        }
        path = Arrays.stream(tokens)
                .reduce((o1, o2) -> o1 + File.separator + o2)
                .orElse("");
        System.out.println(path);
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file.getName() + " - " + file.length() / 1024 + "KB");
        }
    }


}
