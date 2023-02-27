import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface MitCommand {
    Optional<List<File>> list(String directoryName);
    Optional<List<String>> hash(String directoryName);
    Optional<List<String>> zlib(String directoryName);

    Consumer<List<File>> outputList = (files->{
        files.stream()
                .map(file->String.format("%s %.2fKB", file.getName(), (double) file.length() / 1024))
                .forEach(System.out::println);
    });
}
