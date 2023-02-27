import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MitCommands {

    static void list(File file){
        File[] files = file.listFiles();
        for (File f : files){
            System.out.println(f.getName());
        }

    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        while(true){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken() + " " + st.nextToken();
            String fileDir = st.nextToken();
            File file = new File(fileDir);


            switch (cmd){
                case "mit list": list(file); break;
                case "mit hash": hash(file); break;
                case "mit zlib": zlib(file); break;
            }
        }


    }

    private static void hash(File file) throws NoSuchAlgorithmException {
        String algorithm = "SHA-256"; // the hashing algorithm to use
        File[] files = file.listFiles();
        for (File f : files){
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = md.digest(file.getName().getBytes(StandardCharsets.UTF_8));
            String hash = bytesToHex(hashBytes);
            System.out.println(f.getName() " = " + hash);
        }

    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }


}
