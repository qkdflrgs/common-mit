import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


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
                System.out.println(subfile.getName() + " : " + sb);
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    public static void zlib(String userPath) {
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
                //InputStream
                InputStream in = new FileInputStream(subfile);
                byte[] data = in.readAllBytes();
                in.close();

                Deflater compress = new Deflater();
                compress.setInput(data);
                compress.setLevel(Deflater.BEST_COMPRESSION);
                compress.finish();

                ByteArrayOutputStream compressedStream = new ByteArrayOutputStream(data.length);
                byte[] compressedBuffer = new byte[1024];
                while (!compress.finished()) {
                    int compressedDataLength = compress.deflate(compressedBuffer);
                    compressedStream.write(compressedBuffer, 0, compressedDataLength);
                }
                compress.end();

                byte[] compressedData = compressedStream.toByteArray();

                String compressedFile = subfile.getName() + ".z";
                OutputStream out = new FileOutputStream(compressedFile);
                out.write(compressedData);
                out.close();

                System.out.println(compressedFile + " " + new File(compressedFile).length() / 1024 + "KB");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void decompress(String path) {
        Inflater inflater = new Inflater();
        File file = new File(path);

        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = fis.readAllBytes();
            ByteArrayOutputStream out = new ByteArrayOutputStream(data.length);
            inflater.setInput(data);
            byte[] buffer = new byte[1024];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                out.write(buffer,0,count);
            }
            byte[] decompressedData = out.toByteArray();

            FileOutputStream fos = new FileOutputStream("decompressed");
            fos.write(decompressedData);
            fos.close();
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
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

            if (command.startsWith("mit zlib")) {
                String[] commandTokens = command.split(" ");
                if (commandTokens.length > 2) {
                    path = commandTokens[2];
                }
                zlib(path);
            }
            if (command.startsWith("mit decomp")) {
                String[] commandTokens = command.split(" ");
                if (commandTokens.length > 2) {
                    path = commandTokens[2];
                }
                decompress(path);
            }
        }
    }
}
