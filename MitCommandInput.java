import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MitCommandInput {
    private final BufferedReader br;

    public MitCommandInput() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String input(){
        while(true){
            try {
                System.out.print("> ");
                String text = br.readLine();
                if(!MitCommandInputValidator.validate(text)){
                    throw new InvalidMitCommand();
                }
                return text;
            } catch (IOException | InvalidMitCommand e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
