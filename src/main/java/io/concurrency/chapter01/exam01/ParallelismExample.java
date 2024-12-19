package io.concurrency.chapter01.exam01;

import java.util.ArrayList;
import java.util.List;
/*
* 병렬성 - CPU 개수 >= 작업의 개수에 최적화 되어있습니다.
* */
public class ParallelismExample {
    public static void main(String[] args) {
        System.out.println("CPU 개수 : "+Runtime.getRuntime().availableProcessors());

        int cpuCores = Runtime.getRuntime().availableProcessors();

        // CPU 개수만큼 데이터를 생성
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        // CPU 개수만큼 데이터를 병렬로 처리
        long startTime1 = System.currentTimeMillis();

        /*
        * 병렬스트림 진행 (CPU를 전부 사용해서 각각 작업을 하나씩 물기)
        *
        * 분할정복을 이용하는 forkJoinPool 사용
        * */
        long sum1 = data.parallelStream()
        /*
        * 일반스트림 진행 (CPU를 하나만 사용해서 작업을 진행)
        *
        * 순차 작업 진행
        * */
//        long sum1 = data.stream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                        System.out.println(i + ": " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();

        long endTime1 = System.currentTimeMillis();

        System.out.println("CPU 개수만큼 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime1 - startTime1) + "ms");
        System.out.println("결과1: " + sum1);

    }
}
