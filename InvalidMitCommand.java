public class InvalidMitCommand extends RuntimeException{
    private static final String message = "유효하지 않은 명령어입니다. 형식 : mit list|hash|zlib 디렉터리명";
    public InvalidMitCommand(){
        super(message);
    }
}
