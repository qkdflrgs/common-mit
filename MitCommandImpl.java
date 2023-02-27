import java.io.File;
import java.util.*;

public class MitCommandImpl implements MitCommand{

    @Override
    public Optional<List<File>> list(String directoryName) {
        if(directoryName == null){
            System.out.printf("적절하지 않은 입력입니다. : %s", directoryName);
            return Optional.empty();
        }

        File folder = new File(directoryName);
        List<File> result = new ArrayList<>();

        if(folder == null || !folder.isDirectory()){ // 디렉토리가 아닌 경우
            System.out.printf("입력하신 디렉토리명은 디렉토리가 아닙니다. : %s%n", directoryName);
            return Optional.empty();
        }else if(notExistFiles(folder)){ // 디렉토리안에 파일이 존재하지 않는 경우
            System.out.printf("디렉토리가 비어있습니다. : %s%n", folder.getName());
            return Optional.empty();
        }

        Collections.addAll(result, folder.listFiles());
        return Optional.of(result);
    }

    private boolean notExistFiles(File folder){
        if(folder == null || folder.listFiles() == null){
            return true;
        }

        return Arrays.stream(folder.listFiles()).allMatch(File::isDirectory);
    }

    @Override
    public Optional<List<File>> hash(String directoryName) {
        return null;
    }

    @Override
    public Optional<List<File>> zlib(String directoryName) {
        return null;
    }
}
