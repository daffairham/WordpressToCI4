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
        Path controllerPath = Paths.get("../../CI4/app/Controllers/");
        File controllerFile = new File("../../CI4/app/Controllers/News.php");
        File routerFile = new File("../../CI4/app/Config/Routes.php");
        String controllerTemplate = "<?php\r\n" + //
                                "\r\n" + //
                                "namespace App\\Controllers;\r\n" + //
                                "\r\n" + //
                                "class News extends BaseController\r\n" + //
                                "{";
        if(!controllerFile.exists()){
            controllerFile.createNewFile();
        }
        FileWriter writer = new FileWriter(controllerFile);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.append(controllerTemplate);
        FileWriter routerWriter = new FileWriter(routerFile, true);
        
        BufferedWriter bwRouter = new BufferedWriter(routerWriter);
        while (txtReader.hasNextLine()) {
            String data = txtReader.nextLine();
            String folderName = data.substring(24);
            String folderName2 = folderName.replace("\\index.html", "");
            String formattedFolderName = folderName2.replace("-", "_");
            String functionTemplate = "public function page_" + formattedFolderName + "(){" + //
                                "     return view('news/" + folderName2 + "/index');\r\n" + //
                                "}";
            bufferedWriter.append(functionTemplate);
            
            String pagesRoutes = "$routes->get(" + "'/"  + folderName2 + "'," + "'News::page_" + formattedFolderName + "');\r\n";
            
            bwRouter.append(pagesRoutes);
            
            Path newsViewPath = Paths.get("../../CI4/app/Views/news/" + folderName2);
            
            Files.createDirectory(newsViewPath);
            String sourceContentPath = "D:/Kuliah/Proyek Informatika/rugbyindonesia.or.id/"; //MASUKKIN DIRECTORY RUGBY DISINI
            Path sourceHtmlPath = Paths.get( sourceContentPath + folderName2 + "/index.html");
            Path destination = Paths.get("../../CI4/app/Views/news/" + folderName2 + "/index.php");
            Files.copy(sourceHtmlPath, destination);
          }
          txtReader.close();
          bufferedWriter.append("}");
          bufferedWriter.close();
          bwRouter.close();
    }
}
