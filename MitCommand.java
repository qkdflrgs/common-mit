import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * /Users/nino/CS16_2
 */
public class MitCommand {

    public static void main(String[] args) {
        MitCommand mitCommand = new MitCommand();
        mitCommand.printCommand();
        String path = mitCommand.validInput(mitCommand.input());
        mitCommand.scanDir(path);
    }

    File file;

    public String input(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printCommand(){
        System.out.print("$ ");
    }

    public String validInput(String input){
        String[] paths = input.split(" ");
        String path = paths[2];
        return path;
    }

    // 디렉터리를 탐색해서 파일을 출력한다
    public void scanDir(String path){
        file = new File(path);
        String[] fileNames = file.list();
        for(String fileName : fileNames){
            System.out.println(fileName);
        }
    }

}
