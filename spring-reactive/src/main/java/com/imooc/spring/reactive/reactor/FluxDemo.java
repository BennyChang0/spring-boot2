package com.imooc.spring.reactive.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxDemo {
    public static void main(String[] args) {
        println("开始运行");

        // 非阻塞
        Flux.just("A", "B", "C") // 发布A B C
                .map(value -> "+" + value) // "A" -> "+A"
                .publishOn(Schedulers.elastic()) // 线程切换
//                .subscribe(
//                        FluxDemo::println, // 数据消费 = onNext
//                        FluxDemo::println, // 异常处理 = onError(Throwable)
//                        () -> println("完成操作！"),// 完成回调 = onComplete
//                        subscription -> {   //背压操作 = onSubscribe(Subscription)
//                            subscription.cancel();  // 1.取消上游传输数据到下游
//                            subscription.request(Integer.MAX_VALUE); // 2.请求的元素数量
//                        });

                .subscribe(new Subscriber<String>() {
                    private Subscription subscription;

                    private int count;

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        subscription.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        if(count == 2)
                            throw new RuntimeException("故意抛异常");

                        println(s);
                        subscription.request(1);
                        count++;
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        println(throwable.getMessage());
                        subscription.cancel();
                    }

                    @Override
                    public void onComplete() {
                        println("完成操作！");
                    }
                });

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[线程：" + threadName + "] " + object);
    }
}
