# 🏭프로세스와 쓰레드(process & thread)

### 프로세스

- 실행 중인 프로그램, 자원(resources)과 쓰레드로 구성
    - 여기서 자원이란 메모리, CPU 등 컴퓨터 디바이스들

### 쓰레드

- 프로세스 내에서 실제 작업을 수행.
- 모든 프로세스는 최소한 하나의 쓰레드를 가지고 있다.

⇒ 쓰레드가 없으면 작업을 수행할 수 없음
> 프로세스 : 쓰레드 = 공장 : 일꾼

> **싱글 쓰레드 프로세스**
> 
> = 자원 + 쓰레드
> 
> ⇒ 프로세스(공장)
> 
> ⇒ 일꾼이 1명

> **멀티 쓰레드 프로세스**
> 
> = 자원 + 쓰레드 + 쓰레드 + ··· + 쓰레드
> 
> ⇒ 일꾼이 n명
> 
> ⇒ 여러 작업을 나눠서 동시에 수행 가능
> 
> ⇒ 작업을 보다 효율적으로 처리 가능

💡 “하나의 새로운 프로세스를 생성하는 것보다 하나의 새로운 쓰레드를 생성하는 것이 더 적은 비용이 든다.”

### 멀티쓰레드의 장단점
⇒ 대부분의 프로그램이 멀티쓰레드로 작성되어 있음. 그러나 멀티쓰레드 프로그래밍이 장점만 있는 것은 아님.

##### 장점

- 시스템 자원을 보다 효율적으로 사용 가능.
- 사용자에 대한 응답성(responseness)이 향상됨.
- 작업이 분리되어 코드가 간결해짐.

##### 단점

- 동기화(synchronization)에 주의해야 함.
- 교착상태(dead-lock)가 발생하지 않도록 주의해야 함.
    - 기아(starvation) 현상이 발생하기도 함(계속 lock을 얻지 못하고 오랫동안 기다리는 것)
- 각 쓰레드가 효율적으로 고르게 실행될 수 있게 해야 함.

---

### 쓰레드의 구현과 실행
> 클래스를 상속 받든 인터페이스를 구현하든 run() 메서드는 구현해야 함.

##### 쓰레드의 구현
> **1. Thread 클래스를 상속**
>

⇒ 자바는 단일 상속

```java
class MyThread extends Thread {
	public void run() { // Thread 클래스의 run()을 오버라이딩
		/* 작업 내용 */
	}
}
```

```java
MyThread t1 = new MyThread(); // 쓰레드의 생성
t1.start(); // 쓰레드의 실행
```

> **2. Runnable 인터페이스를 구현**
>

⇒ 좀 더 나은 방법(여러 개를 상속 받을 수 있어서)

```java
class MyThread2 implements Runnable {
	public void run() { // Runnable 인터페이스의 추상메서드 run()을 구현
		/* 작업 내용 */
	}
}
```

```java
public interface Runnable {
	public abstract void run();
}
```

```java
Runnable r = new MyThread2();
Thread t2 = new Thread(r); // Thread(Runnable r)
// 위의 두 줄을 한 줄로 적으면
// Thread t2 = new Thread(new MyThread2());
t2.start();
```

##### 쓰레드의 실행 - start()

> 쓰레드를 생성한 후에 start()를 호출해야 쓰레드가 작업을 시작한다.
>

```java
ThreadEx1_1 t1 = new ThreadEx1_1(); // 쓰레드 t1을 생성한다.
ThreadEx1_1 t2 = new ThreadEx1_1(); // 쓰레드 t2을 생성한다.

t1.start(); // 쓰레드 t1을 실행시킨다.
t2.start(); // 쓰레드 t2을 실행시킨다.
```

start했다고 쓰레드가 바로 실행되는 건 아님.

t1을 먼저 시작했으므로 먼저 실행할 확률은 높지만, 꼭 그런 건 아님.

언제 실행될 지는 OS 스케줄러가 실행순서를 결정함

- 윈도우라면 윈도우 스케줄러가 있음.
- 얘네들 중에 어떤 순서로, 누가 먼저, 얼마의 시간 동안 실행될 지를 결정해주는 게 OS스케줄러
- 자바 버츄얼 머신에서 OS스케줄러에 맘대로 이래라 저래라 할 수 없음
- 즉, OS에 종속적


> 1. 스타트 했다고 즉시 실행되는 게 아님
> 2. 먼저 스타트했다고 먼저 실행되는 게 아님

---

### start()와 run()

```java
class ThreadTest {
	public static void main(String args[]) {
		MyThread t1 = new MyThread(); // 1. 생성
		t1.start(); // 2. 실행
	}
}
```

```java
class MyThread extends Thread {
	public void run() {
		//...
	}
}
```
run()은 그냥 하나의 쓰레드만 실행이 되는 것.

따라서 반드시 start()를 실행해야 스택이 생성됨.

---

### main 쓰레드

- main메서드의 코드를 수행하는 쓰레드
- 쓰레드는 ‘사용자 쓰레드’와 ‘데몬 쓰레드’ 두 종류가 있음.
    - 데몬 쓰레드는 사용자 쓰레드가 하는 역할을 보조함.




> 💡 실행 중인 사용자 쓰레드가 하나도 없을 때 프로그램은 종료됨.
> - 데몬 쓰레드는 보조 쓰레드라서 실행되고 있어도 종료됨.
> - 사용자 쓰레드가 2개 이상인 경우 main 쓰레드가 종료되어도 다른 쓰레드가 실행 중이면 프로그램은 종료되지 않음.

### 싱글쓰레드와 멀티쓰레드

##### 싱글쓰레드

```java
class ThreadTest {
	public static void main(String args[]) {
		for (int i = 0; i < 300; i++) {
			System.out.println("-");
		}
		for (int i = 0; i < 300; i++) {
			System.out.println("|");
		}
	} // main
}
```

##### 멀티쓰레드

```java
class ThreadTest {
	public static void main(String args[]) {
		MyThread1 th1 = new MyThread1();
		MyThread2 th2 = new MyThread2();
		th1.start();
		th2.start();
	}
}

class MyThread1 extends Thread {
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.println("-");
		}
	} // run()
}

class MyThread2 extends Thread {
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.println("|");
		}
	}
}

```

---

### 쓰레드의 I/O 블락킹(blocking)

⇒ I/O = Input(입력) / Output(출력) = 입출력

> **싱글 쓰레드의 경우**
>

사용자로부터 입력을 기다리는 구간에 아무 일도 하지 않음.

> **멀티 쓰레드의 경우**
>

사용자로부터 입력을 기다리는 구간에 th2가 수행됨.

⇒ 멀티 쓰레드의 장점

---

## 쓰레드의 우선순위(priority of thread)

- 작업의 중요도에 따라 쓰레드의 우선순위를 다르게 하여 특정 쓰레드가 더 많은 작업시간을 갖게 할 수 있음.
- 우선순위의 차이가 클수록 우선순위 높은 게 빨리 끝날 확률이 높은 것.
  - 그러나 꼭 우선순위대로 되는 건 아니기 때문에 맹신하면 안 됨.
- 윈도우에서는 작업 관리자로 우선순위 설정 가능.
  - 마우스 커서의 우선순위가 높음.
  - 설정한 우선순위는 프로세스가 꺼지면 다시 원래대로 돌아옴.

```java
void setPriority(int newPriority) // 쓰레드의 우선순위를 지정한 값으로 변경
int getPriority() // 쓰레드의 우선순위를 반환

public static final int MAX_PRIORITY = 10 // 최대우선순위
public static final int MIN_PRIORITY = 1 // 최소우선순위
public static final int NORM_PRIORITY = 5 // 보통우선순위 = 기본값
```

> **두 쓰레드의 우선순위가 같은 경우**
>

⇒ 비슷한 시간을 할당 받아서 작업을 진행.

> **A의 우선순위가 높은 경우**
>

⇒ th1이 th2에 비해 더 많은 시간을 할당 받게 됨.

⇒ A 작업이 먼저 종료됨.

---

### 쓰레드 그룹

- 서로 관련된 쓰레드를 그룹으로 묶어서 다루기 위한 것
- 모든 쓰레드는 반드시 하나의 쓰레드 그룹에 포함되어 있어야 함
- 쓰레드 그룹을 지정하지 않고 생성한 쓰레드는 자동적으로 **main쓰레드 그룹**에 속함
  - 우리가 생성하는 쓰레드는 기본적으로 main쓰레드 그룹에 속함(따로 쓰레드 그룹을 만들 수 있음)
- 자신을 생성한 쓰레드(부모 쓰레드)의 그룹과 우선순위를 상속 받음
- 쓰레드 그룹 안에 또 다른 쓰레드 그룹을 만들 수 있음

```java
Thread(ThreadGroup group, String name)
Thread(ThreadGroup group, Runnalbe target)
Thread(ThreadGroup group, Runnalbe target, String name)
Thread(ThreadGroup group, Runnalbe target, String name, long stackSize)
```

```java
// Thread 클래스에 있는 것
ThreadGroup getThreadGroup() // 쓰레드 자신이 속한 쓰레드 그룹을 반환

// ThreadGroup 클래스에 있는 것
void uncaughtException(Thread t, Throwable e) 
// 처리되지 않은 예외에 의해 쓰레드 그룹의 쓰레드가 실행이 종료되었을 때, JVM에 의해 이 메서드가 자동적으로 호출
```

---

### 데몬 쓰레드(daemon thread)

- 일반 쓰레드(non-daemon thread)의 작업을 돕는 보조적인 역할을 수행.
- 일반 쓰레드가 모두 종료되면 자동적으로 종료됨.
- 가비지 컬렉터, 자동저장, 화면 자동갱신 등에 사용됨.
- 무한루프와 조건문을 이용해서 실행 후 대기하다가 특정조건이 만족되면 작업을 수행하고 다시 대기하도록 작성함.
  - ex) while문 안에 if문 조건을 넣음.
  - 무한루프를 넣어도 일반 쓰레드가 종료되면 데몬 쓰레드도 종료되기 때문에 괜찮음.
  - 무한루프이기 때문에 계속 돌아가면 안 되므로 중간에 쉬는 시간을 줌.

```java
boolean isDaemon() // 쓰레드가 데몬 쓰레드인지 확인. 데몬 쓰레드이면 true를 반환.

void setDaemon(boolean on)
// 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경.
// 매개변수 on을 ture로 지정하면 데몬 쓰레드가 됨.
```

- setDaemon(boolean on)은 반드시 start()를 호출하기 전에 실행되어야 함.
  - 그렇지 않으면 IllegalThreadStateException이 발생.
  - start()가 호출된 다음에는 변경이 불가능.

---

### 참고자료
💌 [[자바의 정석 - 기초편] ch13](https://www.youtube.com/watch?v=kNNHaAaFDs8&list=PLW2UjW795-f6xWA2_MUhEVgPauhGl3xIp&index=148)