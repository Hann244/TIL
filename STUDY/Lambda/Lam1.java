package STUDY.Lambda;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lam1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine(); // 입력을 받을 때는 띄어쓰기 없이 받아야 함

        // 연산자만 추출
        OperCheck operCheck = (expression) -> {
            String[] result = Arrays.stream(expression.split(""))  // 문자열을 쪼개고
                    .filter(c -> c.equals("+") || c.equals("-"))  // 연산자만 필터링
                    .toArray(String[]::new);
            return result;
        };

        String[] nums = str.split("[+-]"); // 숫자 배열만 담음
        String[] opers = operCheck.getOper(str); // 연산자만 담음

        int num1, num2 = 0;

        // 첫 번째 수
        num1 = Integer.parseInt(nums[0].equals("") ? opers[0] + nums[1] : nums[0]); // 첫 번째 수가 플러스인지 확인

        for (int i = 0; i < opers.length; i++) {
            if (num1 < 0 && i == 0) {
                continue;
            }
            num2 = Integer.parseInt(nums[i + 1]);
            num1 = calculator(opers[i]).calc(num1, num2);
        }
        System.out.println(num1);

    }

    // 계산식
    public static MyMath calculator(String oper) {
        MyMath math = null;
        switch (oper) {
            case "+":
                math = (num1, num2) -> num1 + num2;
                break;
            case "-":
                math = (num1, num2) -> num1 - num2;
                break;
        }
        return math;
    }

    @FunctionalInterface
    public interface MyMath {
        public int calc(int a, int b);
    }

    @FunctionalInterface
    public interface OperCheck {
        public String[] getOper(String expression);
    }
}
