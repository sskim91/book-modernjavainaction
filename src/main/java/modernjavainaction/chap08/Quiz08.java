package modernjavainaction.chap08;

import java.util.*;

public class Quiz08 {
    public static void main(String[] args) {

        //QUIZ 8-1
        //UnsupportedOperationException이 발생한다. List.of로 만든 컬렉션은 바꿀 수 없기 때문이다.
        List<String> actors = List.of("Keanu", "Jessica");
        actors.set(0, "Brad");
        System.out.println(actors);

//        List<String> strings = Arrays.asList("test", "sskim");
//        strings.set(0, "1111");
//        System.out.println("strings = " + strings);

        //QUIZ 8-2
        //다음 코드가 어떤 작업을 수행하는지 파악한 다음 코드를 단순화 할 수 있는 방법을 설명
        Map<String, Integer> movies = new HashMap<>();
        movies.put("JamesBond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);

        Iterator<Map.Entry<String, Integer>> iterator = movies.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 10) {
                iterator.remove();
            }
        }

        //정답
        //맵의 항목 집합에 프레디케이트를 인수로 받아 항목을 삭제하는 removeIf 메서드를 사용할 수 있음.
        movies.entrySet().removeIf(entry -> entry.getValue() < 10);
    }
}
