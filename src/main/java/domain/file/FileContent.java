package domain.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileContent {

    private final byte[] content;

    public FileContent(File file) {
        this.content = getFileContent(file);
    }

    private byte[] getFileContent(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getContent() {
        return content;
    }
}
