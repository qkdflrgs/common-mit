import java.io.File;

public class MitCommand {
    public void controller(String input){
        String[] splitInput = input.split(" ");

        String command = splitInput[0];

        File dir = new File(splitInput[1]);

        switch (command){
            case "list":
                mit_list(dir);
        }

        System.out.println();
    }

    public void mit_list(File dir){
        File[] files = dir.listFiles();
        for (File file : files){
            System.out.println(file.getName() + " " + String.format("%.1f", (double)file.length()/1024) + "KB");
        }
    }
}
