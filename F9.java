//В компании «Профессиональные программы» решили устроить голосование, чтобы
//выбрать, чем заняться на летнем корпоративе. Варианты:
//A — поехать на базу отдыха;
//B — пойти в поход на байдарках;
//C — отправиться на экскурсию в Дагестан;
//D — устроить вечеринку в офисе.
//Результаты голосования представлены в файле result.txt. Обработайте результат и
//выведите количество проголосовавших для каждого варианта.
package file_directory;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.charset.StandardCharsets;

public class F9 {
    public static void main(String[] args) throws IOException{
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        int[] res = readFile();
        System.out.println("count A is:" + res[0]);
        System.out.println("count B is::" + res[1]);
        System.out.println("count C is::" + res[2]);
        System.out.println("count D is::" + res[3]);

    }
    
    public static int[] readFile() throws IOException{
        final String HOME = System.getProperty("user.home");
        String separatorSlash = FileSystems.getDefault().getSeparator();
        String addPath =  HOME + separatorSlash + "Videos" + separatorSlash + "testFolder" + separatorSlash + "fileRead" + separatorSlash;
   
        FileReader readFile = new FileReader(addPath + "result.txt", StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(readFile);
        int res_A =0,  res_B =0, res_C =0,  res_D =0;
        int data;
        while((data = br.read()) != -1){
            char ch =(char)data;
            if(ch == 'A'){
                res_A++;
            }else if(ch == 'B'){
                res_B++;
            }else if(ch == 'C'){
                res_C++;
            }else if(ch == 'D'){
                res_D++;
            }
        }
        br.close();
        readFile.close();
        int[] res = {res_A, res_B, res_C, res_D};
        return res;
    }
}
