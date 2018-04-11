import java.io.*;
import java.util.*;

/**
 * Created by 1g0resha on 05.04.2018.
 */

public class Searching {
    public final static String PATH = "D:\\Users\\Admin\\Desktop\\Searcher\\searching\\";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">");
            String text = scanner.nextLine();

            HashSet<String> results = new HashSet<>();
            Map<String, String> index = index();
            Porter porter = new Porter();

            if (text.isEmpty()) {
                System.out.println("No results");
            }

            //преобразование слов в массив лемметированных слов
            String words[] = text.split(" ");
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].replaceAll("\\,|\\.|\\?|\\-|\\=|\\+|\\(|\\)|\\*|\\/|\\!|\"", "");
                words[i] = porter.stem(words[i]) + ".txt";
            }

            //создание списка с именами файлов
            ArrayList<String> filesList = new ArrayList<>();
            File myFolder = new File(PATH + "invert");
            File[] files = myFolder.listFiles();
            for (File file : files) {
                filesList.add(file.getName());
            }

            //если слово всего одно
            if (words.length == 1) {
                ArrayList<String> list = new ArrayList<>();
                if (filesList.contains(words[0])) {
                    String name = PATH + "invert\\" + words[0];
                    String line = getFileContent(name);
                    String lineData[] = line.split(" ");
                    for (String number : lineData) {
                        String name1 = number + ".txt";
                        if (index.containsKey(name1)) {
                            list.add(index.get(name1));
                        }
                    }
                    for (String res : list) {
                        System.out.println(res);
                    }
                } else {
                    System.out.println("No results");
                }
            }

            //если слов много
            else if (words.length > 1) {
                ArrayList<String> resOfCon = new ArrayList<>(); //Лист с конъюнкцией
                List<String> firstList = new ArrayList<>(); //Первый лист для сравнения
                if (filesList.contains(words[0])) {
                    String name = PATH + "invert\\" + words[0];
                    String line = getFileContent(name);
                    String lineData[] = line.split(" ");
                    firstList = Arrays.asList(lineData);
                }

                //работа со следующим словом
                for (int i = 1; i < words.length; i++) {
                    if (filesList.contains(words[i])) {
                        String name = PATH + "invert\\" + words[i];
                        String line = getFileContent(name);
                        String lineData[] = line.split(" ");
                        List<String> list = Arrays.asList(lineData);

                        ArrayList<String> buf = new ArrayList<String>();
                        //проверка одинакового содержимого
                        if (i == 1) {
                            for (String elem : list) {
                                if (firstList.contains(elem)) {
                                    resOfCon.add(elem);
                                }
                            }
                        }
                        if (i > 1 && !resOfCon.isEmpty()) {
                            for (String elem : list) {
                                if (resOfCon.contains(elem)) {
                                    buf.add(elem);
                                }
                            }
                            resOfCon = buf;
                        }
                    }
                }
                if (!resOfCon.isEmpty()) {
                    //заполнение листа с URL-ами
                    for (String number : resOfCon) {
                        String name1 = number + ".txt";
                        if (index.containsKey(name1)) {
                            results.add(index.get(name1));
                        }
                    }
                    for (String res : results) {
                        System.out.println(res);
                    }
                } else {
                    System.out.println("No results");
                }

            }


        }
    }

    public static Map<String, String> index() throws IOException {
        Map<String, String> index = new HashMap<String, String>();
        Scanner scanner = new Scanner(new File(PATH + "docs\\index.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String lineData[] = line.split(" ");
            index.put(lineData[0], lineData[1]);
        }
        return index;
    }

    private static String getFileContent(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath) {
                    }, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } finally {
            if (br != null) try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}