package STUDY.Lambda;

import java.util.function.Supplier;

public class Lam2_4 {
    public static void main(String[] args) {
        // 기본 나이 생성기
        Supplier<Integer> ageCreate = () -> 20;
        System.out.println("기본 나이 = " + ageCreate.get());
    }
}
