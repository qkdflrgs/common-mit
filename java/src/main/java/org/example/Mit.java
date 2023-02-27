package org.example;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Mit {

    public List<String> listOfPath(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        return Arrays.stream(Objects.requireNonNull(files))
            .map(Mit::makeFileFormat)
            .collect(Collectors.toList());
    }

    private static String makeFileFormat(File file) {
        return file.getName() + " " + (file.length()) + "byte";
    }
}
