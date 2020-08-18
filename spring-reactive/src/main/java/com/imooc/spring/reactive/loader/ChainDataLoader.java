package com.imooc.spring.reactive.loader;

import java.util.concurrent.*;

/**
 * 链式数据加载器
 */
public class ChainDataLoader extends DataLoader {
    protected void doLoad() {
        // main -> submit -> ...
        //      sub-thread : F1 -> F2 -> F3
//        CompletableFuture
//                .runAsync(super::loadConfigurations)
//                .thenRun(super::loadUsers)
//                .thenRun(super::loadOrders)
//                .whenComplete((result, throwable) -> { // 完成时回调
//                    System.out.println("[线程 ：" + Thread.currentThread().getName() + "] 加载完成");
//                })
//                .exceptionally(throwable -> {
//                    System.out.println("[线程 ：" + Thread.currentThread().getName() + "] 加载异常");
//                    return null;
//                })
//                .join(); // 等待完成

        ThreadFactory threadFactory = Thread::new;
        new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), threadFactory);
        ExecutorService executorService = Executors.newFixedThreadPool(3, threadFactory);
        CompletableFuture
                .runAsync(super::loadConfigurations, executorService)
                .thenRunAsync(super::loadUsers, executorService)
                .thenRunAsync(super::loadOrders, executorService)
                .whenCompleteAsync((result, throwable) -> System.out.println("[线程 ：" + Thread.currentThread().getName() + "] 加载完成"), executorService)
                .exceptionally(throwable -> {
                    System.out.println("[线程 ：" + Thread.currentThread().getName() + "] 加载异常");
                    return null;
                })
                .join();

        executorService.shutdown();
    }

    public static void main(String[] args) {
        new ChainDataLoader().load();
    }
}
