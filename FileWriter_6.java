package file_directory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileSystems;

public class FileWriter_6 {
    private static final String HOME = System.getProperty("user.home");
    public static void main(String[] args) throws IOException{
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        String separatorSlash = FileSystems.getDefault().getSeparator();
        String addPath =  HOME + separatorSlash + "Videos" + separatorSlash + "testFolder" + separatorSlash + "fileRead" + separatorSlash;
        Writer fileWriter= new FileWriter(addPath + "filewriter.txt");
        fileWriter.write("new revord\n");
        fileWriter.write("adding text");
        fileWriter.close();
    }
}
