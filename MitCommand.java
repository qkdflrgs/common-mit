import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MitCommand {
    public void controller(String input){
        String[] splitInput = input.split(" ");

        String command = splitInput[0];

        File dir = new File(splitInput[1]);

        switch (command){
            case "list":
                mit_list(dir);
                break;
            case "hash":
                mit_hash(dir);
                break;
            case "zlib":

                break;
        }

        System.out.println();
    }

    public void mit_list(File dir){
        File[] files = dir.listFiles();
        for (File file : files){
            System.out.println(file.getName() + " " + String.format("%.1f", (double)file.length()/1024) + "KB");
        }
    }

    public void mit_hash(File dir){
        File[] files = dir.listFiles();
        for(File file : files){
            String hash = sha256(file);
            if(hash.length() > 100){
                hash = hash.substring(0, 100) + "....";
            }
            System.out.println(file.getName() + " = " + hash);
        }
    }

    public String sha256(File file){
        byte[] fileByte;
        try {
            fileByte = Files.readAllBytes(file.toPath());

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(fileByte);

            StringBuilder sb = new StringBuilder();
            for(byte b : fileByte){
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
