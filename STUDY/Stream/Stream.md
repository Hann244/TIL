# 💧STREAM

### 스트림(Stream)이란?

> 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 정의해둠.

⇒ 데이터 소스를 추상화
= 데이터 소스가 무엇이던 간에 같은 방식으로 다룰 수 있게 되었다는 것 + 코드의 재사용성 증가
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

### 스트림의 특성
1. 스트림은 데이터 소스를 변경하지 않음.
2. 스트림은 일회용.

   ⇒ Iterator처럼 일회용! 스트림도 한 번 사용하면 닫혀서 다시 사용할 수 없음. 필요하다면 재생성 필요.

3. 스트림은 작업을 내부 반복으로 처리.
4. 스트림의 연산은 filter-map 기반의 API를 사용하므로 지연 연산(lazy Evaluation)을 통해 성능을 최적화.
  - **지연 연산(lazy Evaluation)** : 불필요한 연산을 피하기 위해 연산을 지연시켜 놓았다가 필요할 때 연산하는 방법.
5. 스트림은 parallelStream() 메소드를 활용해 병렬 처리를 쉽게 하도록 지원함.

---

### 스트림의 연산
💡 스트림 생성 → 스트림 중간 연산 → 스트림 최종 연산
> **중간 연산**
>
> 연산 결과가 스트림인 연산. 스트림에 연속해서 중간 연산할 수 있음.
>> `map()` `flatMap()`

> **최종 연산**
>
> 연산 결과가 스트림이 아닌 연산. 스트림의 요소를 소모하므로 단 한 번만 가능.
>> `reduce()` `collect()`

---

### Stream API를 적용할 수 있는 대상
[[Java] Stream API](https://velog.io/@edgar6bf/Java-Stream-API)

Stream API는 `Collection 인터페이스` 내부에 존재하는 메서드들. 컬렉션 인터페이스를 구현하는 다른 구현체들에서 사용할 수 있음.

참고용 블로그 사진에서 볼 수 있듯, `Map` 은 `Collection 인터페이스` 를 상속 받지 않으므로 `Map` 에서는 Stream API를 사용할 수 없음.
> 하지만 Map 에서도 우회하여 Stream API를 사용하는 방법이 있기는 함.

---

### 스트림 생성
1. 문자열 스트림
   1. `Stream<String> strStream = Stream.of("a", "b", "c");`
   2. `Stream<String> strStream = Stream.of(new String[]{"a", "b", "c"});`
   3. `Stream<String> strStream = Arrays.stream(new String[]{"a", "b", "c"});`
   4. `Stream<String> strStream = Arrays.stream(new String[]{"a", "b", "c"}, 1, 3);`
2. int 기본형 배열을 소스로 하는 스트림
   1. `IntStream IntStream.of(int... valuse)`
   2. `IntStream IntStream.of(int[])`
   3. `IntStream Arrays.stream(int[])`
   4. `IntStream Arrays.stream(int[] array, int startInclusive, int endExclusive)`
3. long 기본형 배열을 소스로 하는 스트림
   1. `LongStream`
4. double 기본형 배열을 소스로 하는 스트림
   1. `DoubleStream`

---

### 스트림에서 가장 많이 사용되는 메서드 3가지
##### 1. forEach - 반복(최종 연산)
`list.stream().forEach(System.out::println);`
for-loop와 stream의 forEach()는 다름.

stream().forEach()에서는 객체의 데이터를 다루지 말고 출력용으로만 사용하는 게 좋음.

1. 굳이 forEach를 사용할 필요가 없음. 조건을 확인할 경우 stream의 filter를 이용하면 됨.
2. stream의 forEach는 모든 요소를 돌기 때문에 비효율적일 수 있음.
3. forEach 내부에 로직이 있으면 동시성 보장이 어려워지고 가독성이 떨어질 위험이 있음.

**참고 :** https://ksabs.tistory.com/236
<br />
<br />

##### 2. filter - 조건에 맞는 요소만 선별(중간 연산)
- 하나의 스트림에 여러 번 적용 가능.
- `Stream<T> filter(Predicate<? super T> predicate)`
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

##### 3. map - 요소를 매핑(중간 연산)
- 하나의 스트림에 여러 번 적용 가능.
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
numbers.stream()
			.map(number -> number * 2)
			.forEach(System.out::println); // 2, 4, 6, 8
```
map() 도 다른 Stream API와 마찬가지로 람다 표현식을 파라미터로 받음.

---

### 참고자료
✅ 자바의 정석(2016)

✅ [Java 스트림 Stream (1) 총정리](https://futurecreator.github.io/2018/08/26/java-8-streams/)

✅ [Java 스트림 Stream (2) 고급](https://futurecreator.github.io/2018/08/26/java-8-streams-advanced/)

✅ [자바 스트림 설명부터 사용하는 이유 파헤쳐보기 #ZEROBASE #스트림](https://zangzangs.tistory.com/171)

✅ [[Java] Stream API](https://velog.io/@edgar6bf/Java-Stream-API)

✅ [[ZEROBASE] Stream()의 filter() 메서드를 활용한 요소 필터링](https://velog.io/@jungmyeong96/JAVA-Stream%EC%9D%98-filter-%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9A%94%EC%86%8C-%ED%95%84%ED%84%B0%EB%A7%81)

✅ [스트림(stream)이란 무엇인가?](https://velog.io/@chamominedev/%EC%8A%A4%ED%8A%B8%EB%A6%BCstream%EC%9D%B4%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80)

✅ [[Java]Lazy Evaluation (지연 연산)](https://velog.io/@minseojo/Java-Lazy-Evaluation-%EC%A7%80%EC%97%B0-%EC%97%B0%EC%82%B0)