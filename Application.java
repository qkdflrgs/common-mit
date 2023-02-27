import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Application {
    public static void main(String[] args) {
          MitController mitController = new MitController();

          try {
              mitController.run();
          } catch (IOException e) {
              System.out.println(e.getMessage());
          } catch (NoSuchAlgorithmException e) {
              System.out.println(e.getMessage());
          }

        System.out.println("프로그램을 종료합니다.");
    }
}
