package STUDY.Lambda;

import java.util.function.Function;

public class Lam2_1 {
    public static void main(String[] args) {
        // 나이 변환기
        Function<Integer, String> ageChange = (age) -> "나이는" + age + "입니다";
        System.out.println(ageChange.apply(20));
    }



}
