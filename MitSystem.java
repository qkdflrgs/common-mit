import java.io.File;
import java.util.Arrays;

public class MitSystem {
    public void printListOfFiles(String directoryPath) throws NullPointerException {
        try {
            File[] files = new File(directoryPath).listFiles();
            for (File file : files) {
                String kind = file.isFile() ? "파일" : "디렉토리";
                System.out.printf("<%s> %s %dKB\n", kind, file.getName(), file.length() / 1000);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("올바른 경로가 아닙니다.");
        }
    }

    public void printHashOfFiles(String directoryPath) {

    }

    public void compressFiles(String directoryPath) {

    }

}
