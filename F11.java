//Перепишите фрагмент кода так, чтобы в нём появилась конструкция try-with-resources.
package file_directory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
public class F11 {
public static void main(String[] args){
    try(Resource4 resource1 = new Resource4(); 
        BufferedReader fileReader = new BufferedReader(new FileReader(readFileSimple()))){
        fileReader.readLine();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static String readFileSimple(){
        final String HOME = System.getProperty("user.home");
        String separatorSlash = FileSystems.getDefault().getSeparator();
        String addPath =  HOME + separatorSlash + "Videos" + separatorSlash + "testFolder" + separatorSlash + "fileRead" + separatorSlash +  "result.txt";
        return addPath;
    }
}

class Resource4 implements AutoCloseable{
    @Override
    public void close(){
        System.out.println("метод close() для Resource1");
    }
    
}
