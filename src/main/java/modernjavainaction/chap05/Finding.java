package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;

import java.util.Optional;

import static modernjavainaction.chap04.Dish.menu;

public class Finding {

    //특정 속성이 데이터 집합에 있는지 여부를 검색하는 데이터 처리 유틸리티 메서드
    //anyMatch, allMatch, noneMatch, findFirst, findAny 등등..

    public static void main(String... args) {
        if (isVegetarianFriendlyMenu()) {
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());

        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    private static boolean isVegetarianFriendlyMenu() {
        //하나라도 매치
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthyMenu() {
        //전부 다 매치
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    private static boolean isHealthyMenu2() {
        //allMatch의 반대
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegetarianDish() {
        //임의의 요소를 반환.
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }

}
