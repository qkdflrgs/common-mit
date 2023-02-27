package src;

import java.util.Map;

public class Application {
    public static void main(String[] args) {
        List list = new List();
        Output output = new Output();
        Map information;

        String directory = Input.readCommand();
        if (directory.equals("default")) {
            information = list.getFileInformation();
        } else {
            information = list.getFileInformation(directory);
        }
        output.printFileInformation(information);
    }
}
