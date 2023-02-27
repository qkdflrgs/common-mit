import java.util.regex.Pattern;

public class MitCommandInputValidator {
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(mit [list|hash|zlib]+ .+)|exit");

    public static boolean validate(String text){
        return COMMAND_FORMAT.matcher(text).matches();
    }
}
