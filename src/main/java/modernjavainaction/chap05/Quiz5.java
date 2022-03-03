package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

/**
 * @author sskim
 */
public class Quiz5 {
    public static void main(String[] args) {

        //Quiz 5-1 스트림을 이용해서 처음 등장하는 두 고기 요리를 필터링하시오.
        menu.stream()
                .filter(i -> i.getType() == Dish.Type.MEAT)
                .limit(2)
                .forEach(System.out::println);

        //Quiz 5-2 (1) 숫자 리스트가 주어졌을 때 제곱근으로 이루어진 리스트를 반환.
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        List<Integer> square = integers.stream()
                .map(i -> i * i)
                .collect(toList());

        //Quiz 5-2 (2) 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환
        //예 [1,2,3] [4,5]  => [(1,4), (1,5), (2,4), (2,5), (3,4), (3,5)]
        List<Integer> integers1 = List.of(1, 2, 3);
        List<Integer> integers2 = List.of(4, 5);

        List<int[]> collect = integers1.stream()
                .flatMap(i -> integers2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(toList());

        //(3) 이전 문제에서 합이 3으로 떨어지는 쌍만 반환하려면?
        List<int[]> pairDivideThree = integers1.stream()
                .flatMap(i -> integers2
                        .stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList());

        //QUIZ 5-3 리듀스 map과 reduce 메서드를 이용해서 스트림의 요리 개수를 계산
        Integer reduce = menu.stream()
                .map(i -> 1)
                .reduce(0, Integer::sum);

        Integer reduce1 = menu.stream().map(d -> 1)
                .reduce(0, (a, b) -> a + b);


    }
}
