import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

public class input {
    public input() throws IOException, NoSuchAlgorithmException {
        while (true){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(isMit(st.nextToken())){
                String[] str= {st.nextToken(),st.nextToken()};
                control(str);
            }else{
                System.out.println("탈락");
            }
        }
    }

    boolean isMit(String str) {
        return str.equals("mit") ? true : false;
    }

    void control(String[] str) throws NoSuchAlgorithmException, IOException {
        File directory = new File("."+str[1]);
        if(directory.isDirectory()){
            switch (str[0]) {
                case "list":
                    DirectoryScanner scanner = new DirectoryScanner(directory);
                    break;
                case "hash":
                    SHA256FileNameHasher hasher = new SHA256FileNameHasher(directory);
                    break;
                case "zlib":
                    ZlibFileZipper zlibFileZipper = new ZlibFileZipper(directory);
                    break;
                default:
                    System.out.println("잘못된 명령어");
                    break;
            }
        }else{
            System.out.println("디렉토리 이상함");
        }
    }
}
