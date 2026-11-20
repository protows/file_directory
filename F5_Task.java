package file_directory;

//Вам нужно создать файловый менеджер. Приложение должно уметь:
//просматривать содержимое директории;
//создавать файлы и директории;
//переименовывать файлы или директории;
//полностью перемещать файлы или директории;
//удалять файлы или директории.
//Пользователю необходимо выбрать, какую операцию он хочет выполнить с файлом или папкой, а затем ввести путь к нужному файлу.
//
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
public class F5_Task {
    private static final String HOME = System.getProperty("user.home");
    public static void main(String[] args) throws IOException{
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        String separatorSlash = FileSystems.getDefault().getSeparator();
        String addPath =  "Videos" + separatorSlash + "testFolder";
        Scanner scanner = new Scanner(System.in);
	while_loop:
        while (true) {
            printMenu();
           
            String command = scanner.nextLine();
            if(command.equals("Выход")){
                 System.out.println("Выход.");
                 break while_loop;
            }
          
            System.out.println("Введите путь к файлу/директории: ");
            String enteredPath = scanner.nextLine();
            if(enteredPath.equals("1")){
                enteredPath  = addPath;
            }else{
                 enteredPath  = addPath + separatorSlash + enteredPath;
            }
            // Объявите переменную path с типом Path.
            Path path;
            try{
               path = Paths.get(HOME, enteredPath);
            }catch(InvalidPathException e){
                System.out.println("Incorect path");
                continue;
            }

            // Выполните действия в зависимости от введённой команды.
            switch (command) {
                case "ls":
                       // выведите список элементов директории
                        File[] items = path.toFile().listFiles();
                        System.out.println("path " + path);
                    if (items == null) {
                        System.out.println("Caanot read context of directory");
                        break;
                    }
                    for(File item: items){
                        System.out.println((item.isDirectory() ? "Catalog\n" : "file\n") + item.getName());
                    }
                    break;
                case "mkdir":
                    try {
                        // создайте директорию
                        //create dicrectory
                        if(Files.exists(path)){
                            System.out.println("Directory has created!");
                        }else{
                            Files.createDirectory(path);
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при создании директории.");
                        e.printStackTrace();
                    }
                    break;
                case "touch":
                    try {
                        // создайте файл
                        //create file
                        if(Files.exists(path)){
                            System.out.println("File alredy exists");
                        }else{
                            Files.createFile(path);
                            
                        } //create file
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при создании файла.");
                        e.printStackTrace();
                    }
                    break;
                case "rename":
                    System.out.println("Введите новое имя файла/директории: ");
                    String newName = scanner.nextLine();
                    try {
                        // переименуйте файл
                        //create file
                        Path testFile = Paths.get(HOME, addPath, newName);
                        if(Files.exists(testFile)){
                            System.out.println("File alredy exists");

                        }else{
                            testFile = Files.createFile(testFile);
                            if(Files.exists(testFile)){
                                System.out.println("Files has created!");
                            }
                        } //create file
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при переименовании файла/директории.");
                        e.printStackTrace();
                    }
                    break;
                case "rm_file":
                    try {
                        if (!Files.isDirectory(path)) {
                            // удалите файл
                             //delete file
                           
                            if(Files.exists(path)){
                                 Files.delete(path); 
                                System.out.println("the file has  removed");
                            } //delete file
                        }
                        else {
                            System.out.println("С помощью этой команды можно удалить только файл!");
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при удалении файла.");
                        e.printStackTrace();
                    }
                    break;
                case "rm_directory":
                    try {
                        if (!Files.isDirectory(path)) {
                            Files.delete(path); 
                            if(!Files.exists(path)){
                                System.out.println("the Directory has already been removed");
                            } 
                        }
                        else {
                            System.out.println("С помощью этой команды можно удалить только Directory!");
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при удалении файла.");
                        e.printStackTrace();
                    }
                    break; 
               case "move__file":
                    //move
                    path = Files.move( path, path.resolve("testFile_v2.txt"), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Перемещён: " + path);
                    break;
                case "move_directory":
                    //move
                    path = Files.move( path, path.resolve("direct2"), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Перемещён: " + path);
                    break;
                default:
                    System.out.println("Извините, такой команды пока нет.");
            }

        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("ls - посмотреть содержимое директории.");
        System.out.println("mkdir - создать директорию.");
        System.out.println("touch - создать файл.");
        System.out.println("rename - переименовать директорию/файл.");
        System.out.println("rm_file - удалить файл.");
        System.out.println("rm_directory - удалить диретории.");
        System.out.println("move__file - перемещений файла");
        System.out.println("move_directory - перемещений диреткориии");
        System.out.println("exit - выход.");
    }

}
