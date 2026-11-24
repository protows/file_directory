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
    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        String separatorSlash = FileSystems.getDefault().getSeparator();
        //String addPath =  "Videos" + separatorSlash + "testFolder";
        String addPath = "";
        Scanner scanner = new Scanner(System.in);
	while_loop:
        while (true) {
            printMenu();
           
            String command = scanner.nextLine();
            
            if(command.equals("exit")){
                 System.out.println("Выход.");
                 break while_loop;
            }
          
            System.out.println("Введите путь к основному директории: (либо оставть пустым для testFolder)");
            String enteredPath = scanner.nextLine();
            if(enteredPath.equals("")){
                enteredPath  = addPath;
            }else{
                 //enteredPath  = addPath + separatorSlash + enteredPath;
                 enteredPath  = separatorSlash + enteredPath;
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
                            System.out.println("Directory has alreadery been created!");
                        }else{
                            Files.createDirectory(path);
                            System.out.println("Directory has created!");
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при создании директории.");
                        e.printStackTrace();
                    }
                    break;
                case "touch":
                    try {
                        System.out.println("Введите новое имя файла");
                        String nameFile = scanner.nextLine();
                        Files.createDirectories(path.resolve(path));
                        Path dir = path.resolve(path);
                        Path targetFile = dir.resolve(nameFile);
                        System.out.println(targetFile);
                        Files.createFile(targetFile);
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при создании файла.");
                        e.printStackTrace();
                    }
                    break;
                case "rename":
                    System.out.println("Введите текущее имя файла: ");
                    String curName = scanner.nextLine();
                    System.out.println("Введите новое имя файла: ");
                    String newName = scanner.nextLine();
                    Path source = path.resolve(curName);

                    try {
                       Path targetFle = source.resolveSibling(newName);
                       source.toFile().renameTo(targetFle.toFile());     
                        }
                    catch (Exception e) {
                        System.out.println("Произошла ошибка при переименовании файла/директории.");
                        e.printStackTrace();
                    }
                    break;
                case "rm_file":
                    System.out.println("Введите имя файла для удаления");
                    String nameDeletefile = scanner.nextLine();
                    Path targetFile = path.resolve(nameDeletefile);
                    System.out.println(targetFile);
                    try {
                        File file = targetFile.toFile();
                        if(file.isFile()) {
                            Files.delete(targetFile); 
                            System.out.println("the file has  removed");
                            
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
                        if (Files.isDirectory(path)) {
                            if(Files.exists(path)){
                                 Files.delete(path); 
                                System.out.println("the Directory hasd removed");
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
               case "move_file":
                  System.out.println("Введите новую директорию (относительно testFolder)");
                  Path targetDirectory1 = Paths.get(HOME, addPath).resolve(scanner.nextLine());
                  
                   System.out.println("Введите имя файла");
                   String file2 = scanner.nextLine();
                   Path targetFile2 = targetDirectory1.resolve(file2);                   
                   
                   
                   System.out.println("Введите новое директорию");
                   Path targetDirectory = Paths.get(HOME, addPath, scanner.nextLine());

                   try{
                        Files.createDirectories(targetDirectory);
                   }catch(IOException e){
                       System.out.println(e);
                   }
                   Path destination = targetDirectory.resolve(targetFile2.getFileName());
                           
                   try{
                        path = Files.move( targetFile2, destination, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Перемещён: " + path);
                   }catch(IOException e){
                       System.out.println(e);
                   }
                   break;
                case "move_directory":
                   System.out.println("Введите новое пусть директории");
                   Path newDirectory = Paths.get(HOME, addPath, scanner.nextLine());
                     try{
                        path = Files.move( path, path.resolve(newDirectory), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Перемещён: " + path);
                     } catch(IOException e){
                       System.out.println(e);
                   }
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
        System.out.println("move_file - перемещений файла");
        System.out.println("move_directory - перемещений диреткориии");
        System.out.println("exit - выход.");
    }
}
