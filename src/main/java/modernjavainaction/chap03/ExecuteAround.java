package modernjavainaction.chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

    private static final String FILE = ExecuteAround.class.getResource("./data.txt").getFile();

    public static void main(String... args) throws IOException {
        // 더 유연하게 리팩토링할 메서드
        //1단계. 동작 파라미터화를 기억하라!
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLines);

        String threeLine = processFile((BufferedReader b) -> b.readLine() + b.readLine() + " " + b.readLine());
        System.out.println(threeLine);
    }

    public static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return br.readLine();
        }
    }

    //람다를 전달할 수 있다.
    /**
     람다 표현식으로 함수형 인터페이스의 추상메서드 구현을 직접 전달할 수 있으며 전달된 코드는 함수형 인터페이스의
     인스턴스로 전달된 코드와 같은 방식으로 처리한다.
    **/
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return p.process(br);
        }
    }

    //2단계: 함수형 인터페이스를 통해 전달.
    //BufferedReader -> String과 IOException을 던질 수 있는 시그니처와 일치하는 함수형 인터페이스를 만든다.
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

}
