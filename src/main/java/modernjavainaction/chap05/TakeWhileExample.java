package modernjavainaction.chap05;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @author sskim
 */
public class TakeWhileExample {
    public static void main(String[] args) {

        //정렬되어있을 때
        List<Integer> orderList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        orderList.stream()
                .takeWhile(numberValue(6))
                .forEach(System.out::println);

        System.out.println("========================================");

        List<Integer> unOrderList = List.of(5, 2, 7, 10, 1, 6, 3, 8, 9, 4);

        unOrderList.stream()
                .takeWhile(numberValue(6))
                .forEach(System.out::println);

        System.out.println("========================================");

        IntStream
                .iterate(1, n -> n + 1)
                .takeWhile(n -> n < 10)
                .forEach(System.out::println);
    }

    public static Predicate<? super Integer> numberValue(int num) {
        return a -> a.intValue() < num;
    }

}
