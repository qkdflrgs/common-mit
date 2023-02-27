import java.io.File;
import java.util.stream.Stream;

public class DirectoryScanner {

    public DirectoryScanner(File directory){
        directoryScanner(directory);
    }
    void directoryScanner(File directory) {
        File[] fileList = directory.listFiles();
        Stream.of(fileList).forEach(f ->
                System.out.println(f.getName() + "  "+sizeToKb(f.length()) +"KB"));
        System.out.println();
    }

    long sizeToKb(long size){
        return size/1024;
    }
}
