package STUDY.Thread;

// 13-5 몇 초가 지난 후 정지하는 이유 설명 & 지체없이 바로 정지되도록 코드 개선
/*
    이 코드에서 사용한 방식인 boolean 변수는 쉽게 바뀌는 변수임.
    컴퓨터에서 원본은 RAM에 저장을 하고 CPU는 복사본을 저장함.
    false에서 true로 변경되면 복사본인 CPU 역시 그렇게 바뀌어야 되는데,
    바뀌지 않는 경우가 발생할 수도 있음.
    따라서 원래 코드가 몇 초가 지난 후 정지가 되는 이유임.
    원본은 바뀌었으나 복사본은 아직 값이 변경되지 않았기 때문에.
    따라서 volatile 키워드를 추가해 필요할 때마다 원본을 직접 가서 읽고 오도록 처리함.
 */
class ThreadPractice3 {
    volatile static boolean stopped = false;

    public static void main(String[] args) {
        Thread5 th1 = new Thread5();
        th1.start();

        try {
            Thread.sleep(6 * 1000);
        } catch (Exception e) {}

        stopped = true; // 쓰레드를 정지시킨다.
        System.out.println("stopped");
    }
}

class Thread5 extends Thread {
    public void run() {
        // Exercise13_5.stopped의 값이 false인 동안 반복함.
        for (int i = 0; !ThreadPractice3.stopped; i++) {
            System.out.println(i);

            try {
                Thread.sleep(3 * 1000);
            } catch (Exception e) {}
        }
    } // run()
}
