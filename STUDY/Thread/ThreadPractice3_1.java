package STUDY.Thread;

class ThreadPractice3_1 {
    static boolean stopped = false;

    public static void main(String[] args) {
        Thread7 th1 = new Thread7();
        th1.start();
        long startTime = System.currentTimeMillis();

        try {
            Thread.sleep(6 * 1000);
        } catch (Exception e) {}

        stopped = true; // 쓰레드를 정지시킨다.
        th1.interrupt();
        System.out.println("stopped");
        System.out.println("소요시간1 : " + (System.currentTimeMillis() - startTime));
    }
}

class Thread7 extends Thread {
    public void run() {
        // Exercise13_5.stopped의 값이 false인 동안 반복함.
        for (int i = 0; !ThreadPractice3_1.stopped; i++) {
            System.out.println(i);

            try {
                Thread.sleep(3 * 1000);
            } catch (Exception e) {}
        }
    } // run()
}
