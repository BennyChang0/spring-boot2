package com.imooc.spring.reactive.reactor;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;

public class RxJavaDemo {
    public static void main(String[] args) {
        Flowable.just("Hello World").subscribe(System.out::println);

        Observable.range(1, 5).subscribe(
                System.out::println,
                error -> System.out.println("error"),
                () -> System.out.println("completed")
        );

        Integer[] numbers = {1, 2, 3};
        Observable<Integer> numbersObservable = Observable.fromArray(numbers);
        numbersObservable.subscribe(System.out::println);

        List<String> list = Arrays.asList("Red", "green", "blue", "brown", "yellow", "white");
        Observable<String> listsObservable = Observable.fromIterable(list);

        listsObservable.subscribe(s -> System.out.println(s.toLowerCase()));

        listsObservable.subscribe(s -> System.out.println(s.toUpperCase()));


        Observable<Point> points = Observable.just(new Point(1, 2), new Point(3, 4));
        points.subscribe(System.out::println);


        Observer<String> sampleObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                System.out.println("Observer complete");
            }

            @Override
            public void onNext(String s) {
                System.out.println("MyObserver onNext(): " + s);
            }

            @Override
            public void onError(Throwable e) {
            }
        };

        Subscriber<String> sampleSubscriber = new Subscriber<String>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable e) {
                subscription.cancel();
            }
        };

    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
