# 💧STREAM
### 스트림(Stream)이란?
- Java 8 API에 새로 추가된 기능
- 람다를 활용할 수 있는 기술 중 하나
- 선언형으로 컬렉션 데이터를 처리할 수 있음 → 가독성 상승
---
> **데이터 처리 연산**을 지원하도록 **소스**에서 추출된 **연속된 요소**
- 데이터 연산
    - 스트림은 함수형 프로그래밍 언어에서 일반적으로 지원하는 연산과 데이터베이스와 비슷한 연산을 지원.
- 소스
    - 스트림은 컬렉션, 배열, I/O 자원 등의 데이터 제공 소스로부터 데이터를 소비.
- 연속된 요소
    - 컬렉션과 마찬가지로 스트림은 특정 요소 형식으로 이루어진 연속된 값 집합의 인터페이스를 제공.

---

### Stream API를 적용할 수 있는 대상
[[Java] Stream API](https://velog.io/@edgar6bf/Java-Stream-API)

Stream API는 `Collection 인터페이스` 내부에 존재하는 메서드들. 컬렉션 인터페이스를 구현하는 다른 구현체들에서 사용할 수 있음.

참고용 블로그 사진에서 볼 수 있듯, `Map` 은 `Collection 인터페이스` 를 상속 받지 않으므로 `Map` 에서는 Stream API를 사용할 수 없음.
> 하지만 Map 에서도 우회하여 Stream API를 사용하는 방법이 있기는 함.

---

### 스트림에서 가장 많이 사용되는 메서드 3가지
##### 1. forEach - 반복
`list.stream().forEach(System.out::println);`
for-loop와 stream의 forEach()는 다름.

stream().forEach()에서는 객체의 데이터를 다루지 말고 출력용으로만 사용하는 게 좋음.

1. 굳이 forEach를 사용할 필요가 없음. 조건을 확인할 경우 stream의 filter를 이용하면 됨.
2. stream의 forEach는 모든 요소를 돌기 때문에 비효율적일 수 있음.
3. forEach 내부에 로직이 있으면 동시성 보장이 어려워지고 가독성이 떨어질 위험이 있음.

**참고 :** https://ksabs.tistory.com/236
<br />
<br />

##### 2. filter - 조건에 맞는 요소만 선별
`Stream<T> filter(Predicate<? super T> predicate)`
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

List<Integer> evenNumbers = numbers.stream()
                                   .filter(n -> n % 2 == 0)
                                   .collect(Collectors.toList());

System.out.println(evenNumbers); // [2, 4, 6, 8, 10]
```
> 
> ```java
> filter(integer -> {
>		System.out.println(integer);
>		return integer.equals(40);
>	})
>	.findAny()
>	.get();
> ```
> `filter()` 다음 `findAny()` 라는 메서드가 붙어 있는데, 이는 경우 조건에 맞는 요소가 아무거나 하나 발견되면 그 다음 요소는 반복을 실행하지 않음. 찾고자 하는 요소인 40을 찾으면 실행이 끝나지만, 이어서 Stream API가 한 번 더 오면 그때에는 그냥 전부 실행시킨다!

<br />

##### 3. map - 요소를 매핑
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
numbers.stream()
			.map(number -> number * 2)
			.forEach(System.out::println); // 2, 4, 6, 8
```
map() 도 다른 Stream API와 마찬가지로 람다 표현식을 파라미터로 받음.

---

### 참고자료
✅ [Java 스트림 Stream (1) 총정리](https://futurecreator.github.io/2018/08/26/java-8-streams/)

✅ [Java 스트림 Stream (2) 고급](https://futurecreator.github.io/2018/08/26/java-8-streams-advanced/)

✅ [자바 스트림 설명부터 사용하는 이유 파헤쳐보기 #JAVA #스트림](https://zangzangs.tistory.com/171)

✅ [[Java] Stream API](https://velog.io/@edgar6bf/Java-Stream-API)

✅ [[JAVA] Stream()의 filter() 메서드를 활용한 요소 필터링](https://velog.io/@jungmyeong96/JAVA-Stream%EC%9D%98-filter-%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9A%94%EC%86%8C-%ED%95%84%ED%84%B0%EB%A7%81)