package file_directory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.StandardCopyOption;

public class F3 {
    private static final String HOME = System.getProperty("user.home");
    public static void main(String[] args) throws IOException {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        String separatorSlash = FileSystems.getDefault().getSeparator();
        String addPath =  "Videos" + separatorSlash + "testFolder";
        //create file
        Path testFile = Paths.get(HOME, addPath, "testFile_v1.txt");
        if(Files.exists(testFile)){
            System.out.println("File alredy exists");

        }else{
            testFile = Files.createFile(testFile);
            if(Files.exists(testFile)){
                System.out.println("Files has created!");
            }
        }
        
        //create dicrectory
        Path testDirectory = Paths.get(HOME, addPath, "testDirectory");
        if(Files.exists(testDirectory)){
            System.out.println("Directory has created!");
        }else{
            testDirectory = Files.createDirectory(testDirectory);
            if(Files.isDirectory(testDirectory)){
            System.out.println("Directory has created!");
            }
        }
        
        //move
        testFile = Files.move( testFile, testDirectory.resolve("testFile_v2.txt"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Перемещён: " + testFile);
        
        //delete file
        Files.delete(testFile); 
        if(!Files.exists(testFile)){
            System.out.println("the file has already been removed");
        }
        
        //delete directory
        Files.delete(testDirectory);
        if(!Files.exists(testDirectory)){
            System.out.println("the directory has already been removed");
        }
    }
}
