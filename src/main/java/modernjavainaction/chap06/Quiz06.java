package modernjavainaction.chap06;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;
import static modernjavainaction.chap06.Dish.menu;

public class Quiz06 {

    public static void main(String[] args) {

        //QUIZ 6-2

        Map<Boolean, Map<Boolean, List<Dish>>> collect = menu.stream()
                .collect(
                        partitioningBy(Dish::isVegetarian,
                                partitioningBy(d -> d.getCalories() > 500)));

        System.out.println("collect = " + collect);

        //partitioningBy는 불리언을 반환하는 함수, 즉 프레디케이트를 요구하므로 컴파일 안됨.
        //Dish::getType이 불리언이 아님
//        menu.stream().collect(
//                partitioningBy(Dish::isVegetarian,
//                        partitioningBy(Dish::getType))
//        );

        //분할된 각 항목의 개수를 계산하는 코드
        Map<Boolean, Long> collect1 = menu.stream()
                .collect(
                        partitioningBy(Dish::isVegetarian,
                                counting()
                        ));
        System.out.println("collect1 = " + collect1);

    }

    //QUIZ 6-3 자바 8로 takeWhile 흉내내기
    public static <A > List < A > takeWhile(List < A > list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {    //리스트의 현재 항목이 프레디케이트를 만족하는지 확인
                return list.subList(0, i);  //만족하지 않으면 현재 검사한 항목의 이전 항목 하위 리스트를 반환
            }
            i++;
        }
        return list;
    }
}
