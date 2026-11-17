package file_directory;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import java.io.IOException;

public class F4_copy {
private static final String HOME = System.getProperty("user.home");
public static void main(String[] args) throws IOException{
    // исходный файл
    System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
    String separatorSlash = FileSystems.getDefault().getSeparator();
    String addPath =  "Videos" + separatorSlash + "testFolder";
    Path testDirectory = prepare(addPath);
    
    Path sourceFile = Paths.get(HOME, addPath, "testFile_v4s.txt");
    // директория назначения
    Path targetDir =  testDirectory;
    copyFile(sourceFile, targetDir);
}



public static Path prepare( String addPath) throws IOException{
    //create file
    Path testFile = Paths.get(HOME, addPath, "testFile_v4s.txt");
    if(Files.exists(testFile)){
        System.out.println("File already exists");

    }else{
        testFile = Files.createFile(testFile);
        if(Files.exists(testFile)){
            System.out.println("Files has created!");
        }
    }

    //create dicrectory
    Path testDirectory = Paths.get(HOME, addPath, "testDirectory");
    if(Files.exists(testDirectory)){
        System.out.println("Directory already exists!");
    }else{
        testDirectory = Files.createDirectory(testDirectory);
        if(Files.isDirectory(testDirectory)){
        System.out.println("Directory has created!");
        }
    }
    return testDirectory;
}
public static void copyFile(Path sourceFile, Path targetDir) {
    Path targetFile = targetDir.resolve(sourceFile.getFileName());
    try {
        Files.copy(sourceFile, targetFile);
    } catch (FileAlreadyExistsException e) {
        System.out.format("Файл %s уже существует.", targetFile);
    } catch (IOException e) {
        System.err.format("Произошла ошибка во время копирования файла.");
    }
}
}
