package io.concurrency.chapter01.exam02;
/*
* 컨텍스트 스위칭
*
* 프로세스 혹은 스레드 간의 작업 도중에 I/O 혹은 오버헤드가 발생하는 경우, 다른 작업과의 변환을 진행한다.
*
* 해당 과정에서 기존 PCB 혹은 TCB 정보를 삭제하고 캐시를 비운 후, 새로운 PCB 혹은 TCB 정보로 변경된다.
* */
public class ContextSwitchExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1: " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2: " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 3: " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
