package sprint4.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал поисковую систему на основе нативной HashMap.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/98794/topics/618173c7-3c0e-4955-b88b-d7146f9ffe2e/lessons/a86d4932-4d43-4a06-83d6-432813affb56/
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * В данной задаче у нас есть док-ты и запросы;
 * Создал изначально HashMap<Слово, HashMap<Номер док-та, Релевантность>>;
 * Первый цикл идет проход по документам, записываю документ и кол-во повторений слова в строке(по дефолту = 1);
 * Далее идёт проход по запросам. Создаю конечную HashMap<Номер док-та, Релевантность> и set для того, чтобы
 * не проходить цикл для повторяющихся слов;
 * При прохождении 2-го цикла, если я нахожу слово и:
 * result пустой, то добавляю релевантность встретивщегося слова в мэпу;
 * Иначе склеиваю result с релевантностью слова;
 * После этого у нас есть result и сортируем его, достаем индекс, добавляем в конечную строку и удаляем;
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Наша программа будет работать от таких факторов, как m = кол-во документов, n = кол-во запросов;
 * M = кол-во слов в документе, N = кол-во слов в запросе;
 * То есть наша программа будет работать за O(m * M + n * N)
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Программа будет содержать в себе в среднем случае O(M*N/m+n) памяти;
 * В худшем случае O(M*N) памяти;
 */

// Id: 81624647
// Link: https://contest.yandex.ru/contest/24414/run-report/81624647/

public class SearchSystem {

    private static final int FINAL_SIZE = 5;

    public static void main(String[] args) throws IOException {
        HashMap<String, HashMap<Integer, Integer>> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int m = Integer.parseInt(reader.readLine());
            fillMapByDocs(m, map, reader);

            int n = Integer.parseInt(reader.readLine());
            HashMap<Integer, Integer> result;
            HashSet<String> existedValues;

            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                result = new HashMap<>();
                existedValues = new HashSet<>();
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();

                    // если встречаются дубли в запросах, мы их игнорируем;
                    if (existedValues.contains(word)) continue;

                    if (map.containsKey(word)) {
                        HashMap<Integer, Integer> docToRel = map.get(word);
                        if (result.isEmpty()) {
                            result.putAll(docToRel);
                        } else {
                            mergeMaps(map.get(word), result);
                        }
                    }
                    existedValues.add(word);
                }
                findAndWriteMax(builder, result);
            }

            System.out.println(builder);
        }
    }

    private static void fillMapByDocs(int m, HashMap<String, HashMap<Integer, Integer>> map, BufferedReader reader) throws IOException {
        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                if (!map.containsKey(word)) {
                    HashMap<Integer, Integer> docToRelevant = new HashMap<>();
                    docToRelevant.put(i, 1);
                    map.put(word, docToRelevant);
                } else {
                    HashMap<Integer, Integer> docToRelevant = map.get(word);
                    if (!docToRelevant.containsKey(i))
                        docToRelevant.put(i, 1);
                    else
                        docToRelevant.put(i, docToRelevant.get(i) + 1);
                }
            }
        }
    }

    private static void findAndWriteMax(StringBuilder builder, HashMap<Integer, Integer> map) {
        for (int i = 0; i < FINAL_SIZE; i++) {
            if (map == null || map.isEmpty()) break;

            int keyMaxRelevance = Collections.max(map.keySet(), (x, y) -> {
                if (Objects.equals(map.get(x), map.get(y))) return Integer.compare(y, x);
                else return Integer.compare(map.get(x), map.get(y));
            });

            builder.append(keyMaxRelevance + 1).append(' ');
            map.remove(keyMaxRelevance);
        }
        builder.append('\n');
    }

    private static void mergeMaps(HashMap<Integer, Integer> current, Map<Integer, Integer> result) {
        // в результате хранятся повторения старых значений, а current добавляет счетчик повторений нового слова
        // для выяснения корректной релевантности
        for (Map.Entry<Integer, Integer> map : current.entrySet()) {
            result.merge(map.getKey(), map.getValue(), Integer::sum);
        }
    }

}
