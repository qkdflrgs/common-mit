import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Mit {
    public void list(String directory) {
        try {
            File dir = new File(directory);
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                System.out.printf("%s %dKB\n", file.getName(), file.length() / 1024);
            }
        } catch (NullPointerException e) {
            System.out.println("입력 경로가 잘못되었습니다");
        }
    }

    public void hash(String directory) {
        try {
            File dir = new File(directory);
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                String hash = sha256(file);
                System.out.printf("%s = %s\n", file.getName(), hash);
            }
        } catch (NullPointerException e) {
            System.out.println("입력 경로가 잘못되었습니다");
        }
    }

    public void zip(String directory) {

    }

    private String sha256(File file) {
        try (
                FileInputStream fis = new FileInputStream(file)
        ) {
            MessageDigest checksumSHA256 = MessageDigest.getInstance("SHA-256");
            byte[] checksum = checksumSHA256.digest(fis.readAllBytes());
            return bytesToHex(checksum);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return "디렉토리입니다";
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
