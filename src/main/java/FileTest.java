import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/mybatis-config.xml");
        File file2 = new File("classpath:/mybatis-config.xml");
        System.out.println(file.exists());
        System.out.println(file2.exists());
        System.out.println(file.getAbsolutePath());
        System.out.println(file2.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
    }
}
