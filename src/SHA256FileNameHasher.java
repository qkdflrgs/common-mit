import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA256FileNameHasher{
    public SHA256FileNameHasher(File directory) throws NoSuchAlgorithmException {
        Hasher(directory);
    }

    void Hasher(File directory) throws NoSuchAlgorithmException {
        File[] fileList = directory.listFiles();

        for(File file: fileList){
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(file.getName().getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            System.out.println(file.getName()+" = "+sb);
        }
        System.out.println();
    }
}
