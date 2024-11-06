package STUDY.Stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class StreamPractice {
    public static void main(String[] args) {
        // 문제 14-4 -> 블로그 https://velog.io/@idkwhattodo/Java-Lambda-Stream)
        // 두 개의 주사위를 굴려서 나온 눈의 합이 6인 경우를 모두 출력
        System.out.println("문제 14-4");
        IntStream.rangeClosed(1, 6) // 주사위1 생성
                .boxed() // IntStream을 Stream<Integer>로 변환 -> 람다식을 사용해 dice1을 객체 타입으로 사용 가능
                .flatMap(dice1 -> IntStream.rangeClosed(1, 6) // 주사위2
                        .filter(dice2 -> dice1 + dice2 == 6) // 합이 6인 경우 필터링
                        .mapToObj(dice2 -> new int[]{dice1, dice2})) // 필터링된 dice2값을 통해 새로운 배열을 생성
                .forEach(result -> System.out.println(Arrays.toString(result))); // 합이 6일 때마다 반복해서 출력

        System.out.println();

        // 문제 14-5
        // 문자열 배열 strArr의 모든 문자열의 길이를 더한 결과를 출력
        System.out.println("문제 14-5");
        String[] strArr = {"aaa", "bb", "c", "dddd"};
        // String 클래스에서 바로 stream() 메서드 호출 불가
        int count = (int) Arrays.stream(strArr) // Stream<String>
                .flatMap(str -> Arrays.stream(str.split(""))) // 각 문자열을 문자로 분리 -> 배열에 담아 스트림으로 변경
                .count(); // 스트림의 모든 문자 수를 카운트
        System.out.println("sum=" + count);

        System.out.println();

        // 문제 14-6
        // 문자열 배열 strArr의 문자열 중에서 가장 긴 것의 길이를 출력
        System.out.println("문제 14-6");
        int text = Arrays.stream(strArr)
                .mapToInt(n -> n.length()) // 각각의 문자열의 길이를 확인
                .max() // 가장 긴 값을 체크
                .orElse(0); // 빈 배열일 경우 0으로 전달
        System.out.println(text);

        System.out.println();

        // 문제 14-7 -> 처음에 IntStream.rangeClosed 썼다가 실패.
        // 임의 로또번호(1~45)를 정렬해서 출력
        System.out.println("문제 14-7");
        new Random().ints(1, 46) // 1~45까지의 숫자를 생성
                .distinct() // 중복 제거
                .limit(6) // 6개 출력
                .sorted() // 오름차순 정렬
                .forEach(System.out::println);
    }
}
