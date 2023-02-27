import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Mit {
    public void list(String directory) {
        try {
            File dir = new File(directory);
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                System.out.printf("%s %dB\n", file.getName(), file.length());
            }
        } catch (NullPointerException e) {
            System.out.println("입력 경로가 잘못되었습니다");
        }
    }

    public void hash(String directory) {
        try {
            File dir = new File(directory);
            if (!dir.isDirectory()) {
                System.out.println("디렉토리명을 입력하세요");
                return;
            }
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (!file.isFile()) {
                    System.out.println(file.getName());
                    continue;
                }
                String hash = sha256(fileToBytes(file));
                System.out.printf("%s = %s\n", file.getName(), hash);
            }
        } catch (NullPointerException e) {
            System.out.println("입력 경로가 잘못되었습니다");
        }
    }

    public void zip(String directory) {
        try {
            File dir = new File(directory);
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (!file.isFile()) {
                    System.out.println(file.getName());
                    continue;
                }
                File zipFile = zip(file);
                System.out.printf("%s %dB\n", zipFile.getName(), zipFile.length());
            }
        } catch (NullPointerException e) {
            System.out.println("입력 경로가 잘못되었습니다");
        }
    }

    private byte[] fileToBytes(File file) {
        try (
                FileInputStream fis = new FileInputStream(file)
        ) {
            return fis.readAllBytes();
        } catch (IOException e) {
            throw new IllegalArgumentException("파일이 아닙니다");
        }
    }

    private String sha256(byte[] bytes) {
        try {
            MessageDigest checksumSHA256 = MessageDigest.getInstance("SHA-256");
            byte[] checksum = checksumSHA256.digest(bytes);
            return bytesToHex(checksum);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    private File zip(File file) {
        try {
            String filePath = file.getPath();
            String zipPath = filePath + ".z";
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath));
            zipOut.putNextEntry(new ZipEntry(filePath));
            zipOut.write(fileToBytes(file));
            zipOut.close();
            return new File(zipPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
