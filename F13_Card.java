//Компания «Игры для всех» переносит популярные карточные игры в мета-пространство. 
//На очереди любимец всех вечеринок — Алиас. В этой игре количество участников не ограничено.
//Участники должны объяснить членам своей команды слова, указанные на виртуальных карточках. 
//Осталось написать модуль для распределения слов по карточкам.
//База всех слов для игры хранится в файле words.txt — каждое слово записано на новой строке.
//Этот файл можно дополнить и обновить. На вход приложение должно принимать количество участников, 
//а на выходе генерировать файл-карточку для каждого участника со словами в случайном порядке.
//https://github.com/practicetasks/java_tasks/blob/main/files/task_2/README.md
package file_directory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class F13_Card {
    public static void main(String[] args)  throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество участников: ");
        int playersNumber = scanner.nextInt();
        String fileName = "words.txt";
        List<String> words = readWordsFromFile(fileName);

        // Если слов меньше, чем участников, то выведите сообщение:
        // "Недостаточно слов в файле. Добавьте слова и обновите файл."
        // и завершите выполнение программы
        if(countFileWords(fileName) < playersNumber){
            System.out.println("Недостаточно слов в файле. Добавьте слова и обновите файл.");
            return;
        }

        // воспользуйтесь статическим методом Collections.shuffle(List<?> list),    
        // чтобы поменять порядок слов случайным образом
        Collections.shuffle(words);
        int wordsNumber = words.size() / playersNumber;
        for (int i = 0; i < playersNumber; i++) {
            String filename = String.format("player%s.txt", i + 1);
            List<String> subList = words.subList(i * wordsNumber, (i + 1) * wordsNumber);
            writeListToFile(subList, filename);
        }
        System.out.println("Карточки готовы!");
    }

    private static List<String> readWordsFromFile(String filename2) throws IOException {
        // добавьте построчное чтение из файла с помощью BufferedReader
        // в случае ошибки выведите сообщение: "Произошла ошибка во время чтения файла."
      List<String> words2 = new ArrayList<>();
        try( FileReader reader = new FileReader(readFileSimple(filename2));
        BufferedReader br = new BufferedReader(reader)){
            String data; 
            while ((data = br.readLine()) !=null){
            words2.add(data);
        }
             br.close();
        reader.close();
        }catch(IOException e){
            System.out.println("Произошла ошибка во время чтения файла." + e );
        }
        return words2;
    }

    private static void writeListToFile(List<String> list, String filename) throws IOException{
        // добавьте запись слов в файл с помощью FileWriter
        // в случае ошибки выведите сообщение: "Произошла ошибка во время записи файла."
         final String HOME = System.getProperty("user.home");
         String separatorSlash = FileSystems.getDefault().getSeparator();
        String addPath =  HOME + separatorSlash + "Videos" + separatorSlash + "testFolder" + separatorSlash + "fileRead" + separatorSlash + filename;
        try( Writer fileWriter= new FileWriter(addPath, true)){
             for(String word: list){
                 fileWriter.write(word + System.lineSeparator());
             }
        }catch(IOException e){
            System.out.println("Произошла ошибка во время записи файла." + e);
        }
    }
    
    public static int countFileWords(String fileName) throws IOException{
        int count  = 0;
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        FileReader reader = new FileReader(readFileSimple(fileName));
        BufferedReader br = new BufferedReader(reader);
        while (br.ready()){
            String line = br.readLine();
            count++;
        }
        br.close();
        reader.close();
        return count;
    }
    
    public static String readFileSimple(String fileName){
        final String HOME = System.getProperty("user.home");
        String separatorSlash = FileSystems.getDefault().getSeparator();
        String addPath =  HOME + separatorSlash + "Videos" + separatorSlash + "testFolder" + separatorSlash + "fileRead" + separatorSlash +  fileName;
        return addPath;
    }
}
