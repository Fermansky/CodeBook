import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        String test = "HFF@buaa20231228";
        byte[] encode = Base64.getEncoder().encode(test.getBytes());
        String code = new String(encode, StandardCharsets.UTF_8);
        System.out.println(code);
        byte[] decode = Base64.getDecoder().decode(code);
        String plain = new String(decode, StandardCharsets.UTF_8);
        System.out.println(plain);
//        System.out.println(new String(Base64.getDecoder().decode("SEZGQGJ1YWEyMDIzMTIA")));
    }
}
