package STUDY.Thread;

class ThreadExample4 implements Runnable {
    static boolean autoSave = false;

    // main 쓰레드(일반 쓰레드)
    public static void main(String[] args) {
        Thread t = new Thread(new ThreadExample4()); // Thread(Runnable r) Runnable 인터페이스 구현

        t.setDaemon(true); // 이 부분이 없으면 종료되지 않음.
        t.start();

        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            System.out.println(i);

            if (i == 5) autoSave = true;
        }
        System.out.println("프로그램을 종료합니다.");
    }

    // 데몬 쓰레드(보조) -> 일반 쓰레드가 끝나면 할 게 없음. 따라서 일반 쓰레드가 하나도 없을 때 종료됨.
    public void run() {
        while (true) { // 이 쓰레드는 무한루프
            try {
                Thread.sleep(3 * 1000); // 3초마다
            } catch (InterruptedException e) {}

            // autoSave의 값이 true이면 autoSave()를 호출함.
            if (autoSave) autoSave();
        }
    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장되었습니다.");
    }
}
