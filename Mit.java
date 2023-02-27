import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.security.MessageDigest;

public class Mit {

    public static void list(String userPath) {
        String defaultPath = System.getProperty("user.dir");
        String path = defaultPath + userPath;
        File file = new File(path);
        String[] ls = file.list();
        for (String fileName : ls) {
            String pathName = path + "\\" + fileName;
            File subfile = new File(pathName);
            if (subfile.isDirectory()) {
                System.out.println(subfile.getName() + "/ " + subfile.length() / 1024 + "KB");
            } else {
                System.out.println(subfile.getName() + " " + subfile.length() / 1024 + "KB");
            }
        }
    }

    public static void hash(String userPath) {
        String defaultPath = System.getProperty("user.dir");
        String path = defaultPath + userPath;


        File file = new File(path);
        String[] ls = file.list();
        for (String fileName : ls) {
            String pathName = path + "\\" + fileName;
            try {
                File subfile = new File(pathName);
                if (subfile.isDirectory()) {
                    continue;
                }
                FileInputStream fis = new FileInputStream(subfile);
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] dataBuffer = new byte[1024];
                int bytesRead = 0;

                while ((bytesRead = fis.read(dataBuffer)) > 0) {
                    md.update(dataBuffer, 0, bytesRead);
                }
                byte[] hash = md.digest();

                StringBuilder sb = new StringBuilder();
                for (byte b : hash) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                System.out.println(subfile.getName() +" : " + sb);
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (!(command = scanner.nextLine()).isEmpty()) {
            String path = "";
            if (command.startsWith("mit list")) {
                String[] commandTokens = command.split(" ");
                if (commandTokens.length > 2) {
                    path = commandTokens[2];
                }
                list(path);
            }
            if (command.startsWith("mit hash")) {
                String[] commandTokens = command.split(" ");
                if (commandTokens.length > 2) {
                    path = commandTokens[2];
                }
                hash(path);
            }
        }
    }
}
