package org.example;

import java.io.File;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MitTest {

    @Test
    void list() {
        Mit mit = new Mit();
        File file = new File("/Users/Albert/Documents/codesquad/common-mit/java/src/test/java/org/example/fileList");
        List<String> list = mit.listOfPath(file
            );

        Assertions.assertThat(list).contains("test.java 145byte");
    }
}
