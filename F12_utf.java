package file_directory;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class F12_utf
{
	public static void main(String[] args) {
System.out.println(Charset.defaultCharset().displayName());

Charset charset1 = Charset.forName("UTF8");
System.out.println(charset1.name());
Charset charset2 = StandardCharsets.US_ASCII;
System.out.println(charset2);
        
        
        }
}

