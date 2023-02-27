package Mit.test;

import Mit.src.domain.MitCommand;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Test @Order(4)
    void zlib_test() throws IOException, NoSuchAlgorithmException {
        MitCommand.ZLIB.run(directory);
    }

    @Test @Order(3)
    void hash_test() throws IOException, NoSuchAlgorithmException {
        MitCommand.HASH.run(directory);
    }

    @Test @Order(2)
    void list_test() throws IOException, NoSuchAlgorithmException {
        MitCommand.LIST.run(directory);
    }

    @Test @Order(1)
    void file_test () throws IOException {
        File dir = new File(directory);

        for (File file : dir.listFiles()) {
            System.out.println(file.getName() + " " + Files.size(file.toPath())/1024+"kb");
        }
    }
}