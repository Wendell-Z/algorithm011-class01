package week01;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 用Deque新API改写代码
 *
 * @author Wendell
 * @Date 2020-6-26  19:07:36
 */
public class NewDeque {
    public void useNewAPI() {
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);

        deque.addLast("d");
        deque.addLast("e");
        deque.addLast("f");
        System.out.println(deque);

        String str = deque.getFirst();
        System.out.println(str);
        str = deque.getLast();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.print(deque.pollFirst() + " ");
        }
        System.out.println();
        System.out.println(deque);
    }

    public void originalCode() {
        Deque<String> deque = new LinkedList<>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);

        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.print(deque.pop() + " ");
        }
        System.out.println();
        System.out.println(deque);
    }

    public static void main(String[] args) {
        NewDeque deque = new NewDeque();
        deque.originalCode();
        System.out.println("----------------------------------");
        deque.useNewAPI();
    }
}
