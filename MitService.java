import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MitService {
    File files;

    public MitService(String diretory) {
        this.files = new File(diretory);
    }

    public void printHashes() throws IOException, NoSuchAlgorithmException {
        char idx = 'a';

        for (File file : files.listFiles()) {
            if (file.isDirectory()) continue;   // 폴더일 경우 내용 없어서 넘기기
            if (file.isHidden()) continue;      // 숨김파일 출력하지 않기

            FileInputStream fileInputStream = new FileInputStream(file);
            System.out.printf("%s. %s = %s%n", idx++, file.getName(), sha256(fileInputStream.readAllBytes()));
        }
    }

    public void printFileInfo() {
        char idx = 'a';

        for (File file : files.listFiles()) {
            if (file.isDirectory()) continue; // 폴더 생략
            if (file.isHidden()) continue;  // 숨김 파일은 출력 생략

            System.out.printf("%s. %s %dkb%n",idx++, file.getName(), file.length()/1024);
        }
    }

    /**
     * SHA-256으로 해싱하는 메소드
     */
    public static String sha256(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes);

        return bytesToHex(md.digest());
    }

    /**
     * 바이트를 헥스값으로 변환하는 메소드
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
