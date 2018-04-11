import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by 1g0resha on 29.03.2018.
 */

public class Tokens {
    public static void main(String[] args) throws FileNotFoundException {
        for (int i = 1; i <= 130; i++) {
            Scanner scanner = new Scanner(new File("D:\\Users\\Admin\\Desktop\\Searcher\\searching\\docs\\" + i + ".txt"));
            PrintWriter writer = new PrintWriter("D:\\Users\\Admin\\Desktop\\Searcher\\searching\\tokens\\" + i + ".txt");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String lineData[] = line.split(" ");
                for (int j = 0; j < lineData.length; j++) {
                    lineData[j] = lineData[j].replace("?", "");
                    lineData[j] = lineData[j].replace("!", "");
                    lineData[j] = lineData[j].replace("(", "");
                    lineData[j] = lineData[j].replace(")", "");
                    lineData[j] = lineData[j].replace(";", "");
                    lineData[j] = lineData[j].replace(":", "");
                    lineData[j] = lineData[j].replace("-", "");
                    lineData[j] = lineData[j].replace("+", "");
                    lineData[j] = lineData[j].replace("=", "");
                    lineData[j] = lineData[j].replace(".", "");
                    lineData[j] = lineData[j].replace(",", "");
                    lineData[j] = lineData[j].replace("|", "");
                    lineData[j] = lineData[j].replace("/", "");
                    lineData[j] = lineData[j].replace("\"", "");
                    lineData[j] = lineData[j].replace("—", "");
                    lineData[j] = lineData[j].replace("»", "");
                    lineData[j] = lineData[j].replace("«", "");
                    lineData[j] = lineData[j].toLowerCase();
                    writer.print(lineData[j] + " ");
                }
            }
            writer.close();

        }


    }
}