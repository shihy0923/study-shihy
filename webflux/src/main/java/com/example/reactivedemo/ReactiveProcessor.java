package com.example.reactivedemo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.Stream;

/**
 * Note:
 * Date: 2024/1/6 23:41
 * Author: shihy
 */
public class ReactiveProcessor extends SubmissionPublisher implements Flow.Subscriber {
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println( Thread.currentThread().getName() +  " Reactive processor establish connection ");
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Object item) {
        System.out.println(Thread.currentThread().getName() + " Reactive processor receive data: "+ item);
        this.submit(item.toString().toUpperCase());
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Reactive processor error ");
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println(Thread.currentThread().getName() + " Reactive processor receive data complete ");
    }

    public static void main(String[] args) {

//        Flux.just(1,2,3,4,5).subscribe(System.out::println);
//        Flux.range(1,20).subscribe(System.out::println);
//        Flux.fromArray(new String[]{"a1","a2","a3","a4","a5","a6"}).skip(2).subscribe(System.out::println);
//        Flux.fromIterable(Arrays.asList(1,2,3,4,5,6,7)).subscribe(System.out::println);
//        Flux.fromStream(Stream.of(Arrays.asList(1,2,3,4,5,6,7))).subscribe(System.out::println);




        // 创建一个Flux对象，包含三个整数元素
//        Flux<Integer> integerFlux = Flux.just(1, 2, 3).concatMap(t -> {
//            return Flux.just(t * 2);
//        });

//        Mono<Integer> map = Flux.just(1, 2, 3).concatMap(route -> Mono.just(route).filterWhen(r -> {
//                    // add the current route we are testing
//                    return Mono.just(true);
//                }).doOnError(e -> System.out.println(e))
//                .onErrorResume(e -> Mono.empty())).next()
//                .map(t -> {
//            return t;
//        });

//        Mono<Integer> map = Flux.just(1, 2, 3).concatMap(route -> Mono.just(route).filterWhen(r -> {
//                            // add the current route we are testing
//                            return Mono.just(true);
//                        }).doOnError(e -> System.out.println(e))
//                        .onErrorResume(e -> Mono.empty())).next()
//                .map(t -> {
//                    return t;
//                });

        Flux<Object> objectFlux = Flux.just(1, 2, 3).concatMap(t -> {
            System.out.println(t);
            List<String> objects = new ArrayList<>();
            objects.add("w");
            objects.add("e");
            return Flux.just(objects);

        });




// 订阅这个Mono对象，并打印元素值
        objectFlux.subscribe(System.out::println); // 输出1



//        Flux.just("Ben", "Michael", "Mark").subscribe(new Subscriber<String>() {
//            public void onSubscribe(Subscription s) {
//                s.request(3);
//            }
//
//            public void onNext(String s) {
//                System.out.println("Hello " + s + "!");
//            }

//            public void onError(Throwable t) {
//
//            }
//
//            public void onComplete() {
//                System.out.println("Completed");
//            }
//        });
//
//        Flux.just("Ben", "Michael", "Mark")
//                .doOnNext(s -> System.out.println("Hello " + s + "!"))
//                .doOnComplete(() -> System.out.println("Completed"))
//                .subscribe();



    }
}