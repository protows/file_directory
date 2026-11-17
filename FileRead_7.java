package file_directory;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.FileSystems;

public class FileRead_7 {
    public static void main(String[] args) throws IOException{
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        System.out.println("writeFile()" + writeFile());
        Reader fileReader = new FileReader(writeFile());
        int data = fileReader.read();
        while(data !=-1){
            System.out.println((char) data);
            data = fileReader.read();
        }
        fileReader.close();
     }
    
    public static String writeFile() throws IOException{
        final String HOME = System.getProperty("user.home");
        String separatorSlash = FileSystems.getDefault().getSeparator();
        String addPath =  HOME + separatorSlash + "Videos" + separatorSlash + "testFolder" + separatorSlash + "fileRead" + separatorSlash;
        Writer fileWriter= new FileWriter(addPath + "filewriter.txt");
        fileWriter.write("new revord\n");
        fileWriter.write("adding text");
        fileWriter.close();
        return addPath + "filewriter.txt";
    }
   
}
