import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class mit {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        File directory = new File("./Work/Masters");

        DirectoryScanner scanner = new DirectoryScanner(directory);
        SHA256FileNameHasher hasher = new SHA256FileNameHasher(directory);
    }
}
