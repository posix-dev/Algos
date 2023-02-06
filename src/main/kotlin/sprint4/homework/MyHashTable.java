package sprint4.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Реализовал свою хеш таблицу в рамках данной задачи.
 * <p>
 * Я смотрел информацию по данной задаче по ресурсам:
 * https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/98794/topics/618173c7-3c0e-4955-b88b-d7146f9ffe2e/lessons/db4e40bc-75c2-4302-95ae-e9cc04f86546/
 * Исходники java hashmap;
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Сделал реализацию с массивом bucket-ов и методом цепочек для нахождения эл-та в случае коллизий.
 * При поиске прохожу цепочку bucket-ов с помощью next пока не найду bucket с нужным ключем;
 * При вставке пытаюсь найти(если возможно) эл-т, если он есть и ключ совпадает переписываю value,
 * иначе добавляю в найденный bucket в next наши данные;
 * При удалении прохожу bucket, где должна лежать запись и запоминаю preBucket(корзина до найденной), чтобы при нахождении
 * корректного bucket переписать цепочку. 1->2->3(delete 2) 1->3;
 * Для вычисления хэша пользовался информацией из практикума.
 * 1) Взял большое простое число(для размера и дальнейших расчетов);
 * 2) Умножил на оптимальную константу;
 * 3) Взял целочисленную часть и отнял от double(остается остаток в конечном счете);
 * 4) Остаток умножаем на размер нашего массива, это и будет наш индекс;
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Тк все bucket-ы лежат в массиве, то поиск по индексу равен O(1);
 * В лучшем случае вставка, удаление и нахождение эл-та будет происходит за O(1);
 * В среднем O(1 + ALPHA), где ALPHA = кол-во эл-ов в таблице/кол-во bucket-ов;
 * В худшем O(n);
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Для реализации данной хэш таблицы был выделяется массив с размером, который основан на кол-ве комманд
 * и берем ближайшее большее простое число с помощью функции findClosestPrimeSize;
 * Обозначу здесь этот размер F (большее чем n, но не больше, чем 103_591);
 * Тогда наш алгоритм будет занимать O(F) памяти;
 */

// Id: 81559796
// Link: https://contest.yandex.ru/contest/24414/run-report/81559796/

public class MyHashTable {

    private static final String GET = "get";
    private static final String PUT = "put";
    private static final String DELETE = "delete";

    // не смог найти нормально расписанные простые числа, так бы прописал побольше, чтобы корректный размер взять
    // учитывать по времени не буду, тк массив совсем небольшой.
    // Единственное не понял, зачем брать меньший размер, я ведь как раз хотел бы их избежать в большей мере, тк меньше
    // коллизий = лучше производительность, тк проход по списку O(n) как раз
    static int[] primeArr = {
            5, 7, 11, 17, 37, 53, 59, 61, 97, 127, 173, 211, 241, 271, 307, 337, 367, 397, 431, 463, 499, 541, 599, 647, 719, 761, 811, 853, 887, 947, 997,
            1069, 1657, 2711, 3539, 4421, 6133, 7841, 9973, 103_591
    };

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int commandsCount = Integer.parseInt(reader.readLine());
            int size = findClosestPrimeSize(commandsCount);
            CustomHashTable table = new CustomHashTable(size);
            for (int i = 0; i < commandsCount; ++i) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                boolean isPut = tokenizer.countTokens() > 2;
                String command = tokenizer.nextToken();
                int key = Integer.parseInt(tokenizer.nextToken());
                int value = 0;
                if (isPut) value = Integer.parseInt(tokenizer.nextToken());

                switch (command) {
                    case GET:
                        printCommand(table.get(key));
                        break;
                    case PUT:
                        table.put(key, value);
                        break;
                    case DELETE:
                        printCommand(table.delete(key));
                        break;
                }
            }
        }
    }

    // нахожу size, который поможет, по возможности, избежать коллизий
    private static int findClosestPrimeSize(int size) {
        if (size > primeArr[primeArr.length - 2]) return primeArr[primeArr.length - 1];

        int left = 0;
        int right = primeArr.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;

            if (primeArr[mid] > size) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            if (size < primeArr[right] && size > primeArr[left]) {
                return primeArr[right];
            }
        }

        // если что то пошло не по плану в данном моменте отдам большой размер
        // До этой строчки, по идее, никогда не дойдем
        return primeArr[primeArr.length - 1];
    }

    private static void printCommand(int value) {
        if (value == Integer.MIN_VALUE) {
            System.out.println("None");
        } else {
            System.out.println(value);
        }
    }
}

class CustomHashTable {
    private static final double ALPHA = 0.6180339887;

    private final Bucket[] buckets;

    public CustomHashTable(int size) {
        this.buckets = new Bucket[size];
    }

    public int delete(int key) {
        int index = hash(key);
        Bucket bucket = buckets[index];

        if (bucket == null || bucket.next == null) return Integer.MIN_VALUE;

        Bucket preBucket = null;

        while (bucket.next != null && bucket.key != key) {
            preBucket = bucket;
            bucket = bucket.next;
        }

        if (preBucket != null) {
            preBucket.next = preBucket.next.next;
        }

        return bucket.value;
    }

    public int get(int key) {
        Bucket bucket = findBucket(key);
        if (bucket == null) return Integer.MIN_VALUE;
        return bucket.value;
    }

    public void put(int key, int value) {
        int index = hash(key);
        Bucket bucket = findBucket(key);

        if (bucket == null) {
            Bucket next = new Bucket(index, key, value, null);
            Bucket head = new Bucket(index, Integer.MIN_VALUE, Integer.MIN_VALUE, next);
            buckets[index] = head;
        } else {
            if (bucket.key == key) {
                bucket.value = value;
            } else {
                bucket.next = new Bucket(index, key, value, null);
            }
        }
    }

    private Bucket findBucket(int key) {
        int index = hash(key);
        Bucket bucket = buckets[index];

        if (bucket == null) return null;

        while (bucket.next != null && bucket.key != key) {
            bucket = bucket.next;
        }

        if (bucket.key == Integer.MIN_VALUE && bucket.value == Integer.MIN_VALUE) return null;

        return bucket;
    }

    private int hash(int key) {
        double temp = ALPHA * key;
        int integer = (int) temp;
        double decimal = temp - integer;
        int index = (int) (decimal * buckets.length);
        return Math.abs(index);
    }

    static class Bucket {
        int hash;

        int key;
        int value;
        Bucket next;

        public Bucket(int hash, int key, int value, Bucket next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public boolean equals(Object o) {
            Bucket bucket = (Bucket) o;
            if (this == bucket) return true;
            if (bucket == null) return false;
            return getKey() == bucket.getKey() && getValue() == bucket.getValue();
        }

        public int hashCode() {
            return Objects.hash(getKey(), getValue());
        }
    }
}
