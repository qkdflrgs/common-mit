package org.example;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MitTest {

    @Test
    void list() {
        Mit mit = new Mit();
        List<String> list = mit.listOfPath(
            "/Users/Albert/Documents/codesquad/common-mit/java/src/test/java/org/example/fileList");

        Assertions.assertThat(list).contains("test.java 145byte");
    }
}
