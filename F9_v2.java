package file_directory;
//В компании «Профессиональные программы» решили устроить голосование, чтобы
//выбрать, чем заняться на летнем корпоративе. Варианты:
//A — поехать на базу отдыха;
//B — пойти в поход на байдарках;
//C — отправиться на экскурсию в Дагестан;
//D — устроить вечеринку в офисе.
//Результаты голосования представлены в файле result.txt. Обработайте результат и
//выведите количество проголосовавших для каждого варианта.
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
public class F9_v2 {
    public static void main(String[] args) throws IOException{
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        Map<String, Integer> resFrequencyMap = readFile();
        for(Map.Entry<String, Integer> entry: resFrequencyMap.entrySet()){
            System.out.println("буква:" + entry.getKey() + "; количество: " + entry.getValue()); //<буква>: <количество>
        }
    }
    
    public static  Map<String, Integer>  readFile() throws IOException{
        final String HOME = System.getProperty("user.home");
        String separatorSlash = FileSystems.getDefault().getSeparator();
        String addPath =  HOME + separatorSlash + "Videos" + separatorSlash + "testFolder" + separatorSlash + "fileRead" + separatorSlash;
        Map<String, Integer> frequencyMap = new HashMap<>();
        FileReader readFile = new FileReader(addPath + "result.txt", StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(readFile);
        int res_A =0,  res_B =0, res_C =0,  res_D =0;
        int data;
        while((data = br.read()) != -1){
            char ch =(char)data;
            if(ch == 'A'){
               frequencyMap.put("A", ++res_A);
            }else if(ch == 'B'){
                frequencyMap.put("B", ++res_B);
            }else if(ch == 'C'){
                frequencyMap.put("C", ++res_C);
            }else if(ch == 'D'){
                frequencyMap.put("D", ++res_D);
            }
        }
        br.close();
        readFile.close();
        return frequencyMap;
    }
}
