import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MitCommandInput {
    private final BufferedReader br;

    public MitCommandInput() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String input(){
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
