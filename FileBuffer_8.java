package file_directory;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class FileBuffer_8 {
    public static void main(String[] args) throws IOException{
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        FileRead_7 fileExample = new FileRead_7();
        FileReader reader = new FileReader(fileExample.writeFile());
        BufferedReader br = new BufferedReader(reader);
        
        while (br.ready()){
            String line = br.readLine();
            System.out.println(line);
        }
        br.close();
    }
}

