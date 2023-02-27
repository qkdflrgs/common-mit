import java.io.File;
import java.security.MessageDigest;

public class Mit_Hash {

    public void printHash(String dirName) {

        File dir = new File(dirName);

        File[] fileList = dir.listFiles();

        for(File temp : fileList) {
            String title = temp.getName();

            String hash = getSHA256(title);

             System.out.println(title + " " +hash );
        }

    }

    private String getSHA256(String fileTitle) {
        String SHA = "";
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(fileTitle.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }
}
