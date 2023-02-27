import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MitCommand mitCommand = new MitCommand();

        while(true){
            try {
                System.out.print("$ ");
                String input = br.readLine();
                if(isNotMit(input)) continue;

                mitCommand.controller(input.substring(4));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean isNotMit(String input){
        if(!input.startsWith("mit ")){
            System.out.println("mit 명령어를 입력해주세요.");
            return true;
        }
        return false;
    }
}
