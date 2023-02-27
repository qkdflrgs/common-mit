import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private BufferedReader br;

    public InputView() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getInput() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
