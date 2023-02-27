package domain.hashcode;

import static org.junit.jupiter.api.Assertions.*;

import domain.file.FileContent;
import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HashCodeTest {

    @Test
    void create() {
        // given
        File file = new File(String.format("%s/a.java", getPath()));
        FileContent fileContent = new FileContent(file);

        // when
        HashCode hashCode = new HashCode(fileContent.getContent(), "SHA-256");

        // then
        assertDoesNotThrow(hashCode::getCode);
        assertNotNull(hashCode.getCode());
    }

    @Test
    @DisplayName("해당 알고리즘이 없는 경우")
    void create2() {
        // given
        File file = new File(String.format("%s/a.java", getPath()));
        FileContent fileContent = new FileContent(file);

        // when

        // then
        assertThrows(IllegalArgumentException.class
            , () -> new HashCode(fileContent.getContent(), "test"));

    }

    private String getPath() {
        String projectPath = System.getProperty("user.dir");
        return String.format("%s/src/test/resources", projectPath);
    }

}