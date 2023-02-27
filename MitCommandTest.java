import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class MitCommandTest {
    private String directory;

    @BeforeEach
    void init() {
        directory = "/Users/newp/study/codesquad-masters/course1_cs16/common-mit";
        System.out.println("--- test start ---");
    }

    @AfterEach
    void after() {
        System.out.println("--- test end ---");
        System.out.println();
    }

    @Test
    void hash_test() throws IOException, NoSuchAlgorithmException {
        MitCommand.HASH.run(directory);
    }

    @Test
    void list_test() throws IOException, NoSuchAlgorithmException {
        MitCommand.LIST.run(directory);
    }

    @Test
    void file_test () throws IOException {
        File dir = new File(directory);

        for (File file : dir.listFiles()) {
            System.out.println(file.getName() + " " + Files.size(file.toPath())/1024+"kb");
        }
    }
}