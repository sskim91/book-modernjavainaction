package modernjavainaction.chap04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static modernjavainaction.chap04.Dish.menu;

/**
 * @author sskim
 */
public class Quiz4 {
    public static void main(String[] args) {

        //Quiz 4-1 기존 내부반복과 외부반복 활용 JAVA8 이전
        List<String> highCaloricDishes = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if (dish.getCalories() > 300) {
                highCaloricDishes.add(dish.getName());
            }
        }

        //자바8이후
        //filter 패턴 사용
        menu.stream()
                .filter(d -> d.getCalories() > 300)
                .collect(toList());

        //Quiz 4-2 중간연산과 최종연산
        //filter와 distinct, limit은 중간연산으로서 스트림을 반환해서 서로 연결할  수 있다.
        long count = menu.stream()
                .filter(dish -> dish.getCalories() > 300)   //중간연산
                .distinct() //중간연산
                .limit(3)   //중간연산
                .count();   //최종연산
    }
}
