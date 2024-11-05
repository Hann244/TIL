# 🔔메서드 참조(method reference)
### 메서드 참조(method reference) = 클래스 이름::메서드 이름
| 종류               | 람다                        | 메서드 참조             |
|------------------|---------------------------|--------------------|
| static 메서드 참조    | (x) → ClassName.method(x) | ClassName::method  |
| 인스턴스메서드 참조       | (obj, x) → obj.method(x)  | ClassName::method  |
| 특정 객체 인스턴스메서드 참조 | (x) → obj.method(x) | obj::method|
- 특정 객체 인스턴스메서드 참조는 거의 사용하지 않음. 몰라도 된다!

---

### static 메서드 참조

> **기존에 작성하는 방식**
>

```java
Integer method(String s) { // 그저 Integer.parseInt(String s)만 호출
	return Integer.parseInt(s);
}

// 위의 메서드를 호출한 것.
int result = obj.method("123");

// 아래처럼 작성해도 됨. (int result = obj.method("123");과 동일한 값)
int result = Integer.parseInt("123");
```

> **람다식으로 변경**
>
> ```java
> Function<String, Integer> f = (String s) -> Integer.parseInt(s); // 람다식
> ```

> **메서드 참조**
>
> ```java
> Function<String, Integer> f = Integer::parseInt; // 메서드 참조
> ```

---

## 생성자의 메서드 참조

### **생성자와 메서드 참조 - 입력값이 없는 경우**

> **람다식**
>

`Supplier<MyClass> s = () -> new MyClass();`  ⇒ 입력이 아니라 출력. MyClass 객체를 줌.

Supplier는 주기만 함. 입력은 없고, 객체를 생성해서 주는 것.

> **메서드 참조**
>

`Supplier<MyClass> s = MyClass::new;`  ⇒ 입력이 없으므로 입력 부분은 생략.

---

### 입력값이 있는 경우

> **람다식**
>

`Function<Integer, MyClass> s = (i) -> new MyClass(i);`

> **메서드 참조**
>

`Function<Integer, MyClass> s = MyClass::new;`

> 📌 **매개변수가 2개인 경우?**
> 
> `BiFunction`을 사용.
>
> `BiFunction<T, U, R>` ⇒ T, U가 입력.
> 
> ✅ [BiFunction 정리](https://velog.io/@yshjft/BiFunction-%EC%A0%95%EB%A6%AC)

---

### 배열과 메서드 참조

```java
Function<Integer, int[]> f = x -> new int[x]; // 람다식
Function<Integer, int[]> f2 = int[]::new; // 메서드 참조
// Integer = 배열의 길이. 그 길에 해당하는 배열을 만들어줌.
```

### 참고자료
💌 [[자바의 정석 - 기초편] ch14-13,14 메서드 참조, 생성자의 메서드 참조](https://www.youtube.com/watch?v=I55ALQndw50&list=PLW2UjW795-f6xWA2_MUhEVgPauhGl3xIp&index=162)
