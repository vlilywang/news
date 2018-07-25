package com.sc.wll;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GatherMain {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int page = 1; page <= 100; page++) {
            executorService.submit(new PageGether(page));
        }
        executorService.shutdown();
    }
}
