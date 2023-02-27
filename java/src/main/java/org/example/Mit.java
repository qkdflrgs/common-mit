package org.example;

import com.google.common.hash.Hashing;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Mit {

    public List<String> listOfPath(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("파일이 없습니다.");
            return Collections.emptyList();
        }
        return Arrays.stream(files)
            .map(Mit::makeFileListCommandFormat)
            .collect(Collectors.toList());
    }

    public List<String> hashOfPath(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("파일이 없습니다.");
            return Collections.emptyList();
        }
        return Arrays.stream(files)
            .map(Mit::makeHashListCommandFormat)
            .collect(Collectors.toList());
    }

    private static String makeFileListCommandFormat(File file) {
        return file.getName() + " " + (file.length()) + "byte";
    }

    private static String makeHashListCommandFormat(File file) {

        return file.getName() + " = " + Hashing.sha256().hashString(file.getName(), StandardCharsets.UTF_8).toString();
    }
}
