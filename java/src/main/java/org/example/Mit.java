package org.example;

import com.google.common.hash.Hashing;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.zeroturnaround.zip.ZipUtil;

public class Mit {

    public List<String> listOfPath(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("파일이 없습니다.");
            return Collections.emptyList();
        }
        return Arrays.stream(files)
            .filter(File::isFile)
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
            .filter(File::isFile)
            .map(Mit::makeHashListCommandFormat)
            .collect(Collectors.toList());
    }

    public List<String> zlibOfPath(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("파일이 없습니다.");
            return Collections.emptyList();
        }
        for (File file : files) {
            zipFile(file);
        }

        return listOfPath(dir).stream()
            .filter(s -> s.split(" ")[0].endsWith(".z"))
            .collect(Collectors.toList());


    }

    private static void zipFile(File file) {
        if (file.isFile()) {
            ZipUtil.packEntry(file, new File(file.getPath() + ".z"));
        } else if (file.isDirectory()) {
            ZipUtil.pack(file, new File(file.getPath() + ".z"));
        }

    }

    private static String makeFileListCommandFormat(File file) {
        return file.getName() + " " + (file.length()) + "byte";
    }

    private static String makeHashListCommandFormat(File file) {

        return file.getName() + " = " + Hashing.sha256().hashString(file.getName(), StandardCharsets.UTF_8);
    }
}
