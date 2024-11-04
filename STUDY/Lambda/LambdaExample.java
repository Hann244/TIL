package STUDY.Lambda;

public class LambdaExample {
    public static void main(String[] args) {
//        Object obj = (a, b) -> a > b ? a : b; // 람다식 = 익명객체
//        Object obj = new Object() {
//            int max(int a, int b) {
//                return a > b ? a : b;
//            }
//        };

//        MyFunction2 f = new MyFunction2() {
//            // public abstract이므로 public 필요.
//            // 오버라이딩 - 접근제어자는 좁게 못 바꾼다.(생략하면 default이므로)
//            public int max(int a, int b) {
//                return a > b ? a : b;
//            }
//        };

        // int value = obj.max(3, 5);
        // 에러가 남. 참조변수 obj타입이 Object이므로. max()라는 메서드가 없음.
        // 따라서 객체 안에 만들어도 사용이 불가능하다.
        // 함수형 인터페이스가 필요함.

        // 더 간결한 코드
        // 람다식(익명 객체)을 다루기 위한 참조변수의 타입은 함수형 인터페이스로 한다.
        MyFunction2 f = (a, b)-> a > b ? a : b;
        // 람다식은 이름을 없앴지만 이를 사용하기 위해서는 이름이 있어야 됨.
        // 따라서 함수형 인터페이스에 이름을 붙임.
        // 추상 메서드를 통해 람다식을 호출하는 거라고 생각하면 됨.

        int value = f.max(3, 5);
        System.out.println("value=" + value);
    }

    // 함수형 인터페이스라는 어노테이션
    // 함수형 인터페이스 = 단 하나의 추상 메서드만 가져야 함.
    @FunctionalInterface
    interface MyFunction2 {
        // public abstract int max(int a, int b);
        int max(int a, int b); // public abstract는 생략 가능
    }
}
