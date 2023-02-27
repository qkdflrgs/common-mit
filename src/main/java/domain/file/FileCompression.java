package domain.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileCompression {

    private final File zipFile;

    public FileCompression(File file) {
        this.zipFile = compress(file);
    }

    public File getZipFile() {
        return zipFile;
    }

    private File compress(File file) {
        String filePath = file.getPath()
            .replaceAll(file.getName(), "");
        String fileName = file.getName()
            .replaceAll(".[\\w]+$", ".zip");
        File zipFile = new File(filePath, fileName);
        FileContent fileContent = new FileContent(file);

        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
            ZipEntry zipEntry = new ZipEntry(file.getName());
            out.putNextEntry(zipEntry);
            out.write(fileContent.getContent());
            out.closeEntry();
            return zipFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
