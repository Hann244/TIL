package STUDY.Thread;

// 13-1 Runnable 인터페이스 구현
class ThreadPractice1 {
    public static void main(String[] args) {
        Runnable r = new Thread1();
        Thread t1 = new Thread(r);

        t1.start();
    }
}

class Thread1 implements Runnable {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print('-');
        }
    }
}
