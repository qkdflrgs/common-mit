package src;

import java.util.Map;

public class Output {
    public void printFileInformation(Map<String, Long> information) {
        for(String key : information.keySet()) {
            System.out.println(key + ": " + information.get(key) + "kb");
        }
    }
}
