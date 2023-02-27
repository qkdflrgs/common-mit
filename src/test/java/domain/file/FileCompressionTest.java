package domain.file;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileCompressionTest {

    @Test
    void create() {
        // given
        File file = new File(String.format("%s/a.java", getPath()));

        // when
        FileCompression fileCompression = new FileCompression(file);

        // then
        assertDoesNotThrow(fileCompression::getZipFile);
        assertNotNull(fileCompression.getZipFile());
    }

    @Test
    @DisplayName("해당 경로에 파일이 없는 경우")
    void create2() {
        // given
        File file = new File(String.format("%s/c.java", getPath()));

        // when

        // then
        assertThrows(RuntimeException.class, () ->  new FileCompression(file));

    }

    private String getPath() {
        String projectPath = System.getProperty("user.dir");
        return String.format("%s/src/test/resources", projectPath);
    }
}