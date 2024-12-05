import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Scanner;

public class converter {
    
    public static void main(String[] args) throws IOException {
        String txtPath = "../news.txt";
        File txtFile = new File(txtPath);
        Scanner txtReader = new Scanner(txtFile);
        String controllerTemplate = "<?php\r\n" + //
                                "\r\n" + //
                                "namespace App\\Controllers;\r\n" + //
                                "\r\n" + //
                                "class News extends BaseController\r\n" + //
                                "{";
        while (txtReader.hasNextLine()) {
            String data = txtReader.nextLine();
            String folderName = data.substring(24);
            String folderName2 = folderName.replace("\\index.html", "");
            Path newsViewPath = Paths.get("../../CI4/app/Views/news/" + folderName2);
            
            Path sourceHtmlPath = Paths.get("D:/Kuliah/Proyek Informatika/rugbyindonesia.or.id/" + folderName2 + "/index.html");
            Path destination = Paths.get("../../CI4/app/Views/news/" + folderName2 + "/index.php");
            Files.move(sourceHtmlPath, destination);
          }
          txtReader.close();
    }
}
