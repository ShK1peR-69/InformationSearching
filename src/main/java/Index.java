import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by 1g0resha on 28.02.2018.
 */

public class Index {
    public static void main(String[] args) throws IOException {

        Map<String, TreeSet<Integer>> results = new LinkedHashMap<String, TreeSet<Integer>>();

        for (int i = 1; i <= 120; i++) {
            String name = "D:\\Users\\Admin\\Desktop\\Searcher\\searching\\lemm\\" + i + ".txt";
            System.out.println(name);
            Scanner scanner = new Scanner(new File(name));

            String line = scanner.nextLine().trim(); //удаляет невидимые символы
            String lineData[] = line.split(" ");

            for (String aLineData : lineData) {
                if (results.containsKey(aLineData)) { //если map сожержит такой ключ
                    TreeSet<Integer> hasResults = results.get(aLineData);  //возврат значений ключа
                    hasResults.add(i);
                    results.put(aLineData, hasResults);

                } else {
                    TreeSet<Integer> hasResults = new TreeSet<Integer>();
                    hasResults.add(i);
                    results.put(aLineData, hasResults);
                }
            }
        }

        for (Map.Entry<String, TreeSet<Integer>> entry : results.entrySet()) {
            String name = "D:\\Users\\Admin\\Desktop\\Searcher\\searching\\invert\\" + entry.getKey() + ".txt";
            PrintWriter writer = new PrintWriter(name);
            String value = "";
            for (Integer number : entry.getValue()) {
                value += number + " ";
            }
            System.out.println("1");
            writer.println(value);
            writer.close();
        }
    }
}
