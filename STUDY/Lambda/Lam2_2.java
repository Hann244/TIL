package STUDY.Lambda;

import java.util.function.Predicate;

public class Lam2_2 {
    public static void main(String[] args) {
        // 성인 여부 검사기
        Predicate<Integer> adult = age -> age >= 18;
        System.out.println(adult.test(17) ? "18세 이상입니다." : "18세 미만입니다.");
    }
}
