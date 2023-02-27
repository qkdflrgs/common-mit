import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class ZlibFileZipper {

    public ZlibFileZipper(File directory) throws IOException {
        for (File file : directory.listFiles()) {
            String compressedFileName = file.getName() + ".z";
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 FileOutputStream fileOutputStream = new FileOutputStream(new File(directory, compressedFileName));
                 DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(fileOutputStream)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    deflaterOutputStream.write(buffer, 0, bytesRead);
                }
            }
            System.out.println(compressedFileName);
        }
    }
}
