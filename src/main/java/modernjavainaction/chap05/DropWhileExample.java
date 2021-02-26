package modernjavainaction.chap05;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author sskim
 */
public class DropWhileExample {
    public static void main(String[] args) {

        //정렬되어있을 때
        List<Integer> orderList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        orderList.stream()
                .dropWhile(numberValue(6))
                .forEach(System.out::println);

        System.out.println("========================================");

        //정렬안됨.
        List<Integer> unOrderList = List.of(5, 2, 7, 10, 1, 6, 3, 8, 9, 4);

        //takeWhile과 dropWhile은 선수 조건이 정렬이 되어있어야 한다는 것인데
        //만약 정렬이 안되어있으면 sorted 정렬을 하고 dropWhile을 해도 적용이된다는 점을 확인함.

        unOrderList.stream()
                .sorted(Comparator.naturalOrder()) //또는 Integer::compareTo
                .dropWhile(numberValue(6))
                .forEach(System.out::println);
    }

    public static Predicate<? super Integer> numberValue(int num) {
        return a -> a.intValue() < num;
    }
}
