package STUDY.MethodReference;

import java.util.function.Function;
import java.util.function.Supplier;

public class MethodExample {
    public static void main(String[] args) {
        /*
        Function<String, Integer> f = (String s) -> Integer.parseInt(s);
        System.out.println(f.apply("100") + 200); // 300
        */

        // Function<String, Integer> f = 클래스이름::메서드이름;
        // 뭐가 입력인지 정보를 안 넣어도 알 수 있음. String s 생략 가능.
        Function<String, Integer> f = Integer::parseInt; // 메서드 참조
        // Function<String, Integer> f = (String s) -> Integer.parseInt(s); // 람다식
        System.out.println(f.apply("100") + 200);


        // Supplier는 입력 X, 출력 O
        Supplier<MyClass> s = () -> new MyClass(); // 람다식
        MyClass mc = s.get();
        System.out.println(mc);
        System.out.println(s.get()); // 또 다른 객체가 만들어짐.

        // 메서드 참조
        Supplier<MyClass2> s2 = MyClass2::new;
        System.out.println(s2.get());


        // 값을 입력할 때
        // 람다식
        Function<Integer, MyClass3> fr = (i) -> new MyClass3(i);
        MyClass3 mc3 = fr.apply(100);
        System.out.println(mc3.iv);
        System.out.println(fr.apply(200).iv);

        // 메서드 참조
        Function<Integer, MyClass3> fr2 = MyClass3::new;
        System.out.println(fr2.apply(250).iv);



        // 배열 => 배열의 길이를 줘야 되므로 꼭 Function을 써야 됨.
        // 람다식
        Function<Integer, int[]> fa = (i) -> new int[i];
        int[] arr = fa.apply(100);
        System.out.println("arr.length = " + arr.length); // 배열 길이가 100.

        // 메서드 참조
        Function<Integer, int[]> fa2 = int[]::new;
        int[] arr2 = fa2.apply(100);
        System.out.println("arr2.length = " + arr2.length);

    }
}

class MyClass {}

class MyClass2 {}

class MyClass3 {
    int iv;
    MyClass3 (int iv) {
        this.iv = iv;
    }
}
