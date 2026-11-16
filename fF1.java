package file_directory;

import java.io.File;
public class fF1 {
private static final String HOME = System.getProperty("user.home") + File.separator + "NetBeansProjects" + File.separator + "mavenproject1";
public static void main(String[] args) {
    System.out.println("HOME: " + HOME);

// создание объекта директории
File dir = new File(HOME);
// проверка, является ли объект директорией
if (dir.isDirectory()) {
// получение всех вложенных объектов в директории
for (File item : dir.listFiles()) {
if (item.isDirectory()) {
System.out.println("Каталог: " + item.getName());
} else {
System.out.println("Файл: " + item.getName());
}
}
}
}
}
