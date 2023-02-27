import java.io.File;
import java.util.Arrays;

public class MitSystem {
    private File directory;

    public void printListOfFiles(String directoryPath) throws NullPointerException {
        directory = new File(directoryPath);
        try {
            String[] files = directory.list();
            for (String file : files) {
                System.out.println(file);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("올바른 경로가 아닙니다.");
        }
    }

    public void printHashOfFiles(String directoryPath) {

    }

    public void compressFiles(String directoryPath) {

    }

    private void searchFiles() {

    }
}
