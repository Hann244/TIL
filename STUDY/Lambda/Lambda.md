# 😎람다식(Lambda Expression)

### 람다식(Lambda Expression)이란?
📌 Java = OOP 언어에 함수형 언어 기능을 포함.
- 함수(메서드)를 간단한 ‘식(expression)’으로 표현하는 방법
    - `(a, b) -> a > b ? a : b
- 익명 함수(이름이 없는 함수, anonymous function)

> **함수와 메서드의 차이**
>
- 근본적으로 동일. 함수는 일반적 용어, 메서드는 객체지향개념 용어
- 함수는 클래스에 독립적, 메서드는 클래스에 종속적
- 자바에서는 클래스 밖에 있을 수 없음. (따라서 자바에는 메서드밖에 없다.)
---
### 람다의 특징
##### 익명 함수 (Anonymous functions)
= 람다 대수는 이름을 가질 필요가 없다.

- 익명함수란 말 그대로 함수의 이름이 없는 함수. 익명함수들은 공통으로 **일급객체(First Class Citizen)라는 특징**을 갖고 있음.
    - 일급 객체 = 일반적으로 다른 객체들에 적용 가능한 연산을 모두 지원하는 개체를 말함.
    - 함수를 값으로 사용할 수도 있고, 파라미터로 전달 및 변수에 대입하기와 같은 연산들이 가능함.

##### 커링 (Curring)
= 두 개 이상의 입력이 있는 함수는 최종적으로 1개의 입력만 받는 람다 대수로 단순화 될 수 있다.

---

### 람다의 장단점
##### 장점
1. 코드의 간결성
2. 지연연산 수행
3. 병렬처리 가능

##### 단점
1. 람다식의 호출이 까다로움
2. 람다 stream 사용 시, 단순 for문이나 while문 사용 시 성능 하락.
3. 불필요하게 많이 사용하면 오히려 가독성을 하락시킴.
4. 람다를 사용해 만든 무명함수는 재사용이 불가.

---

### 람다식 작성하기
1. 메서드의 이름과 반환타입을 제거하고 ‘→’ 블록{} 앞에 추가
2. 반환값이 있는 경우, 식이나 값만 적고 return문 생략 가능(끝에 ’;’ 안 붙임)
3. 매개변수의 타입이 추론 가능하면 생략 가능(대부분의 경우 생략 가능)
> 🚨 **주의사항**
> 1. 매개변수가 하나인 경우, 괄호() 생략가능(타입이 없을 때만)
> 2. 블록 안의 문장이 하나뿐 일 때, 괄호{} 생략가능(끝에 ‘;’ 안 붙임)
>     - 단, 하나뿐인 문장이 return문이면 괄호{} 생략 불가
> ```java
> (int a, int b) -> { return a > b ? a : b;}       // OK
> (int a, int b) -> return a > b ? a : b           // 에러

---

### 람다식의 예
```java
// 1번
(a, b) -> a > b ? a : b;

// 2번
(name, i) -> System.out.println(name + "=" + i);

// 3번
x -> x * x;

// 4번
() -> (int) (Math.random() * 6); // 빈 괄호()는 생략하면 안됨.
```

---

### 람다식은 익명 함수? => 익명 객체
- 람다식은 익명 함수가 아닌 익명 객체.
- 람다식(익명 객체)을 다루기 위한 참조변수가 필요.

    ```java
    Object obj = new Object() {
    	int max(int a, int b) {
    		return a > b ? a : b;
    	}
    }
    ```

    - 참조 변수의 타입 = Object
    - `타입 obj = (a, b) → a > b ? a : b` // 어떤 타입?
    - 타입 obj = 참조 변수 / `(a, b) → a > b ? a : b` = 객체
    - `int value = obj.max(3, 5);` // 에러. Object 클래스에 max()가 없음.
        - 따라서 max()를 호출할 수가 없음. 사용 불가.
        - 함수형 인터페이스가 필요함.


<br />

---

# 🤯함수형 인터페이스
>> **함수형 인터페이스 ⇒ 람다식을 다루기 위해 사용하는 것.**
> 단 하나의 추상 메서드만 선언된 인터페이스
>
> ```java
> interface MyFunction {
> 	public abstract int max(int a, int b);
> }
> ```
>
> 인터페이스를 사용하려면?
> 
> ```java
> MyFunction f = new MyFunction() {
> 	public int max(int a, int b) {
> 		return a > b ? a : b;
> 	}
> };
> ```
>
> 따라서 `int value = f.max(3, 5);` 는 문제 없이 실행할 수 있음. MyFunction에 max()가 있음.

- 함수형 인터페이스 타입의 참조변수로 람다식을 참조할 수 있음.
    - 단, 함수형 인터페이스의 메서드와 람다식의 **매개변수 개수와 반환타입**이 일치할 것.
    - `MyFUnction f = (a, b) -> a > b ? a : b;`
- 람다식에서 Object로 선언해 에러가 났던 max()를 함수형 인터페이스를 활용해 람다식으로 표현하고, max()함수를 사용함.
- 람다식은 이름을 없앴지만 이를 사용하기 위해서는 이름이 있어야 됨.
    - 따라서 함수형 인터페이스에 이름을 붙임.
    - 추상 메서드를 통해 람다식을 호출하는 거라고 생각하면 됨.

---

### 함수형 인터페이스의 예
> **익명 객체를 람다식으로 대체**
>

```java
List<String> list = Arrays.asList("abc", "aaa", "bbb", "ddd", "aaa");
Collections.sort(list, (s1, s2) -> s2.compareTo(s1);

// 함수형 인터페이스
interface Comparator<T> {
	int compare(T o1, T o2);
}
```

---

### 함수형 인터페이스 타입의 매개변수, 반환타입
> **함수형 인터페이스 타입의 매개변수**
>

```java
@FuntionalInterface
interface MyFunction {
	void myMethod();
}

MyFunction f = () -> System.out.println("myMethod()");
aMethod(f);

// 더 간단하게 바꾸면
aMethod(() -> System.out.println("myMethod()"));
```

---

> **함수형 인터페이스 타입의 반환타입**
>

```java
//람다식 반환
MyFunction myMethod() {
	MyFunction f = () -> {};
	return f; // 람다식을 반환
}

// 더 간단하게 바꾸면
MyFunction myMethod() {
	return () -> {};
}
```

---

### 참고자료
💌 [[자바의 정석 - 기초편] ch14-1~4 람다식이란? 람다식 작성하기](https://www.youtube.com/watch?v=3wnmgM4qK30)

💌 [[자바의 정석 - 기초편] ch14-5,6 함수형인터페이스](https://www.youtube.com/watch?v=0Sp9eFRV8gE)

✅ [[JAVA] 람다식(Lambda)의 개념 및 사용법](https://khj93.tistory.com/entry/JAVA-%EB%9E%8C%EB%8B%A4%EC%8B%9DRambda%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B4%EA%B3%A0-%EC%82%AC%EC%9A%A9%EB%B2%95)

✅ [[Java] 람다식(Lambda Expression)과 함수형 인터페이스(Functional Interface) - (2/5)](https://mangkyu.tistory.com/113)