import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class FindIndexHtmlFiles {

    public static void main(String[] args) {
        String directoryPath = "../../rugbyindonesia.or.id";
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            searchFile(directory, directoryPath);
        } else {
            System.out.println("Invalid directory");
        }
    }

    public static void searchFile(File dir, String rootPath) {
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                searchFile(file, rootPath);
            } else if (file.isFile() && file.getName().equalsIgnoreCase("index.html")) {
                if (filterNewsPage(file)) {
                    saveToTXT(file, rootPath);
                }
            }
        }
    }

    public static boolean filterNewsPage(File file) {
        try {
            Document doc = Jsoup.parse(file, "UTF-8");
            Element body = doc.body();
            return body.hasClass("single-post");
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return false;
        }
    }

    public static void saveToTXT(File file, String rootPath) {
        try (PrintStream out = new PrintStream(new FileOutputStream("news.txt", true))) {
            String relativePath = file.getAbsolutePath().replaceFirst("^.*" + rootPath.replace("../", ""), rootPath);
            out.println(relativePath);
        } catch (IOException e) {
            System.err.println("Error writing to output file for: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
