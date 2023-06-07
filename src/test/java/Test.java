import com.felixhua.codebook.util.CodeUtil;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;


public class Test {
    public static void main(String[] args) {
        System.out.println(Locale.getDefault());
        String filename = "codebook-icon-300px.png";
        int last = filename.lastIndexOf(".");
        System.out.println(last);
        System.out.println(filename.substring(0, last) + "_" + Locale.getDefault() + filename.substring(last));
    }
}
