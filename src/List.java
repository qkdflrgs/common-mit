package src;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class List {
    private String pwd = "C:/DevTools/workspace/common-mit/files";

    public Map<String, Long> getFileInformation() {
        File dir = new File(pwd);
        File[] fileList = dir.listFiles();
        Map<String, Long> fileInformation = new HashMap<>();

        for(File file : fileList) {
            fileInformation.put(file.getName(), file.length());
        }
        return fileInformation;
    }


    public static void main(String[] args) {
        List list = new List();
        Map information = list.getFileInformation();
        System.out.println(information);
    }


}
