package STUDY.Lambda;

import java.util.function.Consumer;

public class Lam2_3 {
    public static void main(String[] args) {
        // 출력기
        Consumer<String> namePrint = name -> System.out.println(name);
        namePrint.accept("John");
    }
}
