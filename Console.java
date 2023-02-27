import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Console {

    private InputView inputView = new InputView();

    public void run() {
        try {
            String command = inputView.getCommand();
            if (!command.startsWith("mit")) {
                return;
            }
            String[] commands = command.split(" ");
            switch(commands[1]) {
                case "list":
                    list(commands[2]);
                    break;
                case "hash":
                    hash(commands[2]);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void list(String path) {

        File[] files = new File(path).listFiles();
        assert files != null;
        String fileContents = Arrays.stream(files).map(f -> String.format("%s : %d KB%n", f.getName(), f.length())).reduce("", (o, n) -> o + n);
        System.out.println(fileContents);
    }

    private void hash(String path) throws IOException, NoSuchAlgorithmException {
        File[] files = new File(path).listFiles();

        assert files != null;
        for (File file : files) {
            makeFileHash(file);
        }
    }

    private void makeFileHash(File file) throws IOException, NoSuchAlgorithmException {
        InputStream inputStream = new FileInputStream(file);

        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[1024];
        int numRead = -1;

        while ((numRead = inputStream.read(buffer)) != -1) {
            sha.update(buffer, 0, numRead);
        }
        inputStream.close();
        System.out.println(byteToHex(sha.digest()));
    }

    private String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
