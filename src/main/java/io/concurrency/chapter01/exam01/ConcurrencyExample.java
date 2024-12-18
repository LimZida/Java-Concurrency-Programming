package io.concurrency.chapter01.exam01;

import java.util.ArrayList;
import java.util.List;
/*
* 동시성 - CPU 개수 < 작업의 개수에 최적화 되어있습니다.
*
* 만약 CPU 개수보다 작업의 개수가 한개라도 많다면 동시성 처리로 진행된다.
* */
public class ConcurrencyExample {
    public static void main(String[] args) {
        System.out.println("CPU 개수 : "+Runtime.getRuntime().availableProcessors());
        // CPU 개수 * 2
//        int cpuCores = Runtime.getRuntime().availableProcessors() * 2;

        // CPU 개수 + 1
        int cpuCores = 13;

        // CPU 개수를 초과하는 데이터를 생성
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        // CPU 개수를 초과하는 데이터를 병렬로 처리
        long startTime2 = System.currentTimeMillis();
        long sum2 = data.parallelStream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();

        long endTime2 = System.currentTimeMillis();

        System.out.println("CPU 개수를 초과하는 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime2 - startTime2) + "ms");
        System.out.println("결과2: " + sum2);
    }
}
