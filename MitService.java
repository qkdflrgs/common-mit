import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.zip.Deflater;
import java.util.zip.DeflaterInputStream;
import java.util.zip.DeflaterOutputStream;

public class MitService {
    File files;

    public MitService(String diretory) {
        this.files = new File(diretory);
    }

    public void compress() throws IOException {
        for (File file : Objects.requireNonNull(files.listFiles())) {
            if (file.isDirectory()) continue;
            if (file.isHidden()) continue;

            Deflater compressor = new Deflater();
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath() + ".z");
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(fileOutputStream, compressor);

            compressor.setLevel(Deflater.BEST_COMPRESSION);
            compressor.setInput(readFile(file));

            byte[] output = new byte[512];

            while (fileInputStream.read(output) != -1) {  // 읽어서
                compressor.deflate(output);
                deflaterOutputStream.write(output);     // 출력
            }

            compressor.finish();
            fileInputStream.close();
            deflaterOutputStream.finish();
            deflaterOutputStream.close();
        }
    }

    public void printHashes() throws NoSuchAlgorithmException, IOException {
        char idx = 'a';

        for (File file : Objects.requireNonNull(files.listFiles())) {
            if (file.isDirectory()) continue;   // 폴더일 경우 내용 없어서 넘기기
            if (file.isHidden()) continue;      // 숨김파일 출력하지 않기

            System.out.printf("%s. %s = %s%n", idx++, file.getName(), sha256(readFile(file)));
        }
    }

    private byte[] readFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return fileInputStream.readAllBytes();
    }

    public void printFileInfo() {
        char idx = 'a';

        for (File file : files.listFiles()) {
            if (file.isDirectory()) continue; // 폴더 생략
            if (file.isHidden()) continue;  // 숨김 파일은 출력 생략

            System.out.printf("%s. %s %dkb%n",idx++, file.getName(), file.length()/1024);
        }
    }

    public void printFileInfo(String filter) {
        char idx = 'a';

        for (File file : files.listFiles()) {
            if (file.getName().contains("."+filter)) {
                System.out.printf("%s. %s %dkb%n",idx++, file.getName(), file.length()/1024);
            }
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
