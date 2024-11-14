package STUDY.Thread;

// 문제 : https://yoonhihi.tistory.com/114
class ThreadPractice4 {
    public static void main(String[] args) {
        // 각 동물의 울음소리를 넘겨줌
        Runnable r1 = new AnimalThread("멍멍");
        Thread dog = new Thread(r1);

        Runnable r2 = new AnimalThread("야옹");
        Thread cat = new Thread(r2);

        Runnable r3 = new AnimalThread("짹짹");
        Thread bird = new Thread(r3);

        // 두 마리의 울음소리를 먼저 실행 -> 섞여서 출력
        dog.start();
        cat.start();

        try { // 두 마리가 모두 울고 나서 다음 동물이 실행됨
            dog.join();
            cat.join();
        } catch (Exception e) {}

        bird.start(); // 두 마리가 끝나면 마지막으로 실행
    }
}

class AnimalThread implements Runnable {
    // sound를 받을 변수 선언
    private String sound;

    // sound를 출력하기 위한 생성자 추가
    public AnimalThread(String sound) {
        this.sound = sound;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(sound);
        }
    } // run()
}
