package STUDY.Thread;

// 13-2 일시정지 상태로 만드는 것이 아닌 것은?(모두 고르시오)
// ② resume(), ⑥ notify() -> 쓰레드를 깨우는 메서드

// 13-3 다음 코드의 실행결과로 옳은 것은?
// ①번
// -> 오답 ②번이 맞는 답
// run() 메서드와 start() 메서드의 차이로 인해 위와 같은 답이 나옴
class ThreadPractice2 {
    public static void main(String[] args) {
        Thread2 t1 = new Thread2();
        t1.run(); // 현재 스레드(여기서는 메인 스레드)에서 실행하게 만듦
        // 즉, t1.run()을 호출하면 새 호출 스택이 생성되는 게 아님
        // t1.start()였다면 새 호출 스택을 만들어 쓰레드를 통한 run()을 동작시킴
        // t1.run()은 메인 스레드에서 Thread2의 run() 메서드를 실행하는 것이므로, 이 run() 메서드는 0부터 299까지 출력

        // run() 메서드가 끝난 후 메인 스레드에서 아래의 for문을 출력
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
        }
    }
}

class Thread2 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(i);
        }
    }
}
