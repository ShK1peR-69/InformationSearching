import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by 1g0resha on 11.03.2018.
 */

public class Lemmatization {
    public static void main(String[] args) throws FileNotFoundException {
        Porter porter = new Porter();
        for (int i = 1; i <= 120; i++) {
            Scanner scanner = new Scanner(new File("D:\\Users\\Admin\\Desktop\\Searcher\\searching\\tokens\\" + i + ".txt"));
            PrintWriter writer = new PrintWriter(i + ".txt");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String lineData[] = line.split(" ");
                for (int j = 0; j < lineData.length; j++) {
                    String word= porter.stem(lineData[j]);
                    writer.print(word+" ");
                }
            }
            writer.close();


        }
    }
}
