package modernjavainaction.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @author sskim
 */
public class PracticeCodeByMyself {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 질의 1: 2011년부터 발생한 모든 거래를 찾아 값으로 정렬(작은 값에서 큰 값).
        List<Transaction> quiz1 = transactions.stream()
                .filter(a -> a.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println("quiz1 = " + quiz1);

        // 질의 2: 거래자가 근무하는 모든 고유 도시는? //고유라는건 distinct
        List<String> quiz2 = transactions.stream()
                .map(a -> a.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println("quiz2 = " + quiz2);

        // 질의 3: Cambridge의 모든 거래자를 찾아 이름으로 정렬.
        List<Trader> quiz3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(a -> a.getCity().contains("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println("quiz3 = " + quiz3);

        // 질의 4: 알파벳 순으로 정렬된 모든 거래자의 이름 문자열을 반환
        List<String> quiz4 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(toList());

        //reduce 사용, 문자열을 반환
        String stringQuiz4 = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println("quiz4 = " + quiz4);
        System.out.println("stringQuiz4 = " + stringQuiz4);

        // 질의 5: Milan에 거주하는 거래자가 있는가?
        List<Trader> quiz5 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().contains("Milan"))
                .distinct()
                .collect(toList());
        System.out.println("quiz5 = " + quiz5);

        //!! findAny 검색필터 사용
        boolean milan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().contains("Milan"));
        System.out.println("milan = " + milan);

        // 질의 6: Cambridge에 사는 거래자의 모든 거래내역 출력. (정렬까지 해보기)
        transactions.stream()
                .filter(a -> a.getTrader().getCity().contains("Cambridge"))
                .map(Transaction::getValue)
                .sorted()
                .forEach(System.out::println);

        // 질의 7: 모든 거래에서 최고값은 얼마인가?
        Integer maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println("maxValue = " + maxValue);

        Optional<Transaction> max = transactions.stream()
                .max(comparing(Transaction::getValue));
        System.out.println("max.get().getValue() = " + max.get().getValue());

        // 가장 작은 값을 가진 거래 탐색
        Optional<Transaction> smallestTransaction = transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println("smallestTransaction = " + smallestTransaction.orElse(new Transaction(new Trader("sskim", "seoul"), 2021, 10000)));

        // 거래가 없을 때 기본 문자열을 사용할 수 있도록발견된 거래가 있으면 문자열로 바꾸는 꼼수를 사용함(예, the Stream is empty)
        System.out.println(smallestTransaction.map(String::valueOf).orElse("No transactions found"));
    }

}
