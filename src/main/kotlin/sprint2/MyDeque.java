package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 -- ПРИНЦИП РАБОТЫ --
 Я реализовал двухстороннюю очередь на основе массива и кольцевого буфера.

 Я смотрел информацию по данной задаче по ресурсам:
 https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/98792/topics/3fe165ac-9a25-44db-b5bf-106709d1c140/lessons/b5036361-6d27-45be-ac0a-3946d9a0fcde/

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 Свою реализацию я сделал с помощью таких вспомогательных полей, как head, tail, size и сам массив queue.

 Для добавления в начало(pushFront) мы работаем с head позицией. Идёт проверка не полный ли у нас массив, иначе ошибка(error)
 после этого,
 инициализирован ли у нас head(-1 значит, что нет),то есть нам нужно поставить позиции для начала работы для tail & head в 0.
 Если мы дошли до начала массива(0) то ставим ему позицию в конец массива, тк head должен перемещаться на 1 позицию левее.
 Для добавления в конец(pushBack) проверка на head(тот же самый момент с инициализацией).
 Далее если tail дошел до конца ставим ему позицию 0, иначе просто инкрементируем его позицию.

 Для popFront & popBack смотрим изначально не пуст ли массив иначе кидаем ошибку(error).
 Для popFront логика заключается в том, чтобы достать элемент из head, инкрементировать его значение(
 с оговоркой, что мы не приблизились к его лимиту размера, иначе ставим позицию 0) и
 уменьшитьразмер(size) нашего массива.
 Для popBack логика заключается в том, чтобы достать элемент из tail, декрементировать его значение(
 с оговоркой, что мы не приблизились к его началу, иначе ставим позицию queue.length - 1) и уменьшить
 размер(size) нашего массива.

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Добавление и извлечение по индексу происходит за О(1);
В общем случае n операций займут O(n) времени;

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 Алгоритм потребляет O(n) памяти массива в реализации CustomDeque.
 */

// Id: 80331674
// Link: https://contest.yandex.ru/contest/22781/run-report/80331674/

public class MyDeque {
    private static final String PUSH_FRONT = "push_front";
    private static final String PUSH_BACK = "push_back";
    private static final String POP_FRONT = "pop_front";
    private static final String POP_BACK = "pop_back";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int commandsCount = Integer.parseInt(reader.readLine().strip());
            int length = Integer.parseInt(reader.readLine().strip());
            CustomDeque dq = new CustomDeque(length);
            for(int i = 0; i < commandsCount; ++i) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                boolean isPush = tokenizer.countTokens() > 1;
                String command = tokenizer.nextToken();
                int value = 0;
                if(isPush) {
                    value = Integer.parseInt(tokenizer.nextToken());
                }

                switch (command) {
                    case PUSH_FRONT:
                        dq.pushFront(value);
                        break;
                    case PUSH_BACK:
                        dq.pushBack(value);
                        break;
                    case POP_BACK:
                        printCommand(dq.popBack());
                        break;
                    case POP_FRONT:
                        printCommand(dq.popFront());
                        break;
                }
            }
        }
    }
    private static void printCommand(int value) {
        if (value != -1) {
            System.out.println(value);
        }
    }

}

class CustomDeque {

    CustomDeque(int size) {
        this.queue = new int[size + 1];
    }

    private int size = 0;
    private int head = -1;
    private int tail = 0;

    private int[] queue;

    void pushBack(int value) {
        if (isFull()) {
            System.out.println("error");
            return;
        }

        if (head == -1) {
            head = 0;
            tail = 0;
        } else if (tail == queue.length - 1) {
            tail = 0;
        } else {
            tail += 1;
        }

        queue[tail] = value;
        size++;
    }

    void pushFront(int value) {
        if (isFull()) {
            System.out.println("error");
            return;
        }

        if (head == -1) {
            head = 0;
            tail = 0;
        } else if (head == 0) {
            head = queue.length - 1;
        } else {
            head -= 1;
        }

        queue[head] = value;
        size++;
    }

    int popFront() {
        if (isEmpty()) {
            System.out.println("error");
            return -1;
        }

        int value = queue[head];

        if (head == queue.length - 1) {
            head = 0;
        } else {
            head = head + 1;
        }

        size--;
        return value;
    }

    int popBack() {
        if (isEmpty()) {
            System.out.println("error");
            return -1;
        }

        int value = queue[tail];

        if (tail == 0) {
            tail = queue.length - 1;
        } else {
            tail = tail - 1;
        }

        size--;
        return value;
    }

    int getSize() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == queue.length - 1;
    }
}
