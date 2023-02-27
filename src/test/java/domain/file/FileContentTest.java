package domain.file;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileContentTest {

    @Test
    void create() {
        // given
        File file = new File(String.format("%s/a.java", getPath()));

        // when
        FileContent fileContent = new FileContent(file);

        // then
        assertDoesNotThrow(fileContent::getContent);
        assertNotNull(fileContent.getContent());
    }

    @Test
    @DisplayName("해당 경로에 파일이 없는 경우")
    void create2() {
        // given
        File file = new File(String.format("%s/c.java", getPath()));

        // when

        // then
        assertThrows(RuntimeException.class, () ->  new FileContent(file));

    }

    private String getPath() {
        String projectPath = System.getProperty("user.dir");
        return String.format("%s/src/test/resources", projectPath);
    }
}