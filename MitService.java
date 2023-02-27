import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MitService {
    File files;

    public MitService(String diretory) {
        this.files = new File(diretory);
    }

    public void printFileInfo() {
        char idx = 'a';

        for (File file : files.listFiles()) {
            if (file.isDirectory()) continue; // 폴더 생략
            if (file.isHidden()) continue;  // 숨김 파일은 출력 생략

            System.out.printf("%s. %s %dkb%n",idx++, file.getName(), file.length()/1024);
        }
    }
        }
    }
}
