import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.PrintWriter;

/**
 * Created by 1g0resha on 20.02.2018.
 */

public class Crawler {

    public static void main(String[] args) throws Exception {
        int j = 1;
        String s;
        PrintWriter indexWriter = new PrintWriter("index.txt");
        for (int i = 1; i <= 31; i++) {
            if (i < 10) {
                s = "0" + String.valueOf(i);
            } else {
                s = String.valueOf(i);
            }
            String url = "https://lenta.ru/news/2017/07/" + s + "/";
            Document document = Jsoup.connect(url).get();
            String text = document.select(".g-layout div").text();
            String docName = j + ".txt";
            PrintWriter writer = new PrintWriter(docName);
            writer.println(text);
            writer.close();
            indexWriter.println(j + ".txt " + url);
            System.out.println(j);
            j++;
        }
        indexWriter.close();
    }
}