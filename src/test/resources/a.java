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

    public byte[] getContent1() {
        return content;
    }

    public byte[] getContent2() {
        return content;
    }

    public byte[] getContent3() {
        return content;
    }

    public byte[] getContent4() {
        return content;
    }

    public byte[] getContent5() {
        return content;
    }

    public byte[] getContent6() {
        return content;
    }

    public byte[] getContent7() {
        return content;
    }

    public byte[] getContent8() {
        return content;
    }
}
