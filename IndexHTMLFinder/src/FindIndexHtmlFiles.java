import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class FindIndexHtmlFiles {

    public static void main(String[] args) {
        String directoryPath = "../../rugbyindonesia.or.id";
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            searchFile(directory, directoryPath, "single-post", "news.txt");
        } else {
            System.out.println("Invalid directory");
        }
    }

    public static void searchFile(File dir, String rootPath, String filter, String fileName) {
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                searchFile(file, rootPath, filter, fileName);
            } else if (file.isFile() && file.getName().equalsIgnoreCase("index.html")) {
                if (filterPage(file, filter)) {
                    saveToTXT(file, rootPath, fileName);
                }
            }
        }
    }

    public static boolean filterPage(File file, String filter) {
        try {
            Document doc = Jsoup.parse(file, "UTF-8");
            Element body = doc.body();
            return body.hasClass(filter);
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return false;
        }
    }

    public static void saveToTXT(File file, String rootPath, String fileName) {
        try (PrintStream out = new PrintStream(new FileOutputStream(fileName, true))) {
            String absolutePath = file.getAbsolutePath();
            String adjustedRootPath = new File(rootPath).getParentFile().getAbsolutePath();
            String relativePath = absolutePath.replace(adjustedRootPath, "..");
            out.println(relativePath);
        } catch (IOException e) {
            System.err.println("Error writing to output file for: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

}
