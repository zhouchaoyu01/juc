package com.atguigu.completable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class CompletableFuture2 {
    /**
     * 没有返回值的异步任务 runAsync
     *
     * @param args
     */
//    public static void main(String[] args) throws Exception {
//        System.out.println("主线程开始");
//        //运行一个没有返回值的异步任务
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            try {
//                System.out.println("子线程启动干活");
//                Thread.sleep(5000);
//                System.out.println("子线程完成");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        //主线程阻塞
//        future.get();
//        System.out.println("主线程结束");
//    }


    /**
     * 有返回值的异步任务 supplyAsync
     *
     * @param args
     */
//    public static void main(String[] args) throws Exception{
//        System.out.println("主线程开始");
//        //运行一个有返回值的异步任务
//        CompletableFuture<String> future =
//                CompletableFuture.supplyAsync(() -> {
//                    try {
//                        System.out.println("子线程开始任务");
//                        Thread.sleep(5000);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return "子线程完成了!";
//                });
//        //主线程阻塞
//        String s = future.get();
//        System.out.println("主线程结束, 子线程的结果为:" + s);
//    }

    /*
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
     */

    private static Integer num = 10;

    /**
     * thenApply
     * 先对一个数加 10,然后取平方
     */
//    public static void main(String[] args) throws Exception {
//        System.out.println("主线程开始");
//        CompletableFuture<Integer> future =
//                CompletableFuture.supplyAsync(() -> {
//                    try {
//                        System.out.println("加 10 任务开始");
//                        num += 10;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return num;
//                }).thenApply(integer -> {
//                    return num * num;
//                });
//        Integer integer = future.get();
//        System.out.println("主线程结束, 子线程的结果为:" + integer);
//    }

    /*
     thenAccept 消费处理结果, 接收任务的处理结果，并消费处理，无返回结果。
     */
//    public static void main(String[] args) throws Exception {
//        System.out.println("主线程开始");
//        CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println("加 10 任务开始");
//                num += 10;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return num;
//        }).thenApply(integer -> {
//            return num * num;
//        }).thenAccept(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println("子线程全部处理完成,最后调用了 accept,结果为:" +
//                        integer);
//            }
//        });
//    }

    /*
        exceptionally 异常处理,出现异常时触发
     */
//    public static void main(String[] args) throws Exception {
//        System.out.println("主线程开始");
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            int i = 1 / 0;
//            System.out.println("加 10 任务开始");
//            num += 10;
//            return num;
//        }).exceptionally(ex -> {
//            System.out.println(ex.getMessage());
//            return -1;
//        });
//        System.out.println(future.get());
//    }

    /*
        handle 类似于 thenAccept/thenRun 方法,是最后一步的处理调用,但是同时可以处理异常
     */
//    public static void main(String[] args) throws Exception {
//        System.out.println("主线程开始");
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("加 10 任务开始");
//            num += 10;
//            return num;
//        }).handle((i, ex) -> {
//            System.out.println("进入 handle 方法");
//            if (ex != null) {
//                System.out.println("发生了异常,内容为:" + ex.getMessage());
//                return -1;
//            } else {
//                System.out.println("正常完成,内容为: " + i);
//                return i;
//            }
//        });
//        System.out.println(future.get());
//    }

    /*
    thenCompose 合并两个有依赖关系的 CompletableFutures 的执行结果
     */
//    public static void main(String[] args) throws Exception {
//        System.out.println("主线程开始");
////第一步加 10
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("加 10 任务开始");
//            num += 10;
//            return num;
//        });
////合并
//        CompletableFuture<Integer> future1 = future.thenCompose(i ->
////再来一个 CompletableFuture
//                CompletableFuture.supplyAsync(() -> {
//                    return i + 1;
//                }));
//        System.out.println(future.get());
//        System.out.println(future1.get());
//    }

    /*
    thenCombine 合并两个没有依赖关系的 CompletableFutures 任务
     */
//    public static void main(String[] args) throws Exception {
//        System.out.println("主线程开始");
//        CompletableFuture<Integer> job1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("加 10 任务开始");
//            num += 10;
//            return num;
//        });
//        CompletableFuture<Integer> job2 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("乘以 10 任务开始");
//            num = num * 10;
//            return num;
//        });
////合并两个结果
//        CompletableFuture<Object> future = job1.thenCombine(job2, new
//                BiFunction<Integer, Integer, List<Integer>>() {
//                    @Override
//                    public List<Integer> apply(Integer a, Integer b) {
//                        List<Integer> list = new ArrayList<>();
//                        list.add(a);
//                        list.add(b);
//                        return list;
//                    }
//                });
//        System.out.println("合并结果为:" + future.get());
//    }

    /*
    合并多个任务的结果 allOf 与 anyOf

    allOf: 一系列独立的 future 任务，等其所有的任务执行完后做一些事情
    anyOf: 只要在多个 future 里面有一个返回，整个任务就可以结束，而不需要等到每一个future 结束
     */

//    public static void main(String[] args) throws Exception{
//        System.out.println("主线程开始");
//        List<CompletableFuture> list = new ArrayList<>();
//        CompletableFuture<Integer> job1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("加 10 任务开始");
//            num += 10;
//            return num;
//        });
//        list.add(job1);
//        CompletableFuture<Integer> job2 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("乘以 10 任务开始");
//            num = num * 10;
//            return num;
//        });
//        list.add(job2);
//        CompletableFuture<Integer> job3 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("减以 10 任务开始");
//            num = num - 10;
//            return num;
//        });
//        list.add(job3);
//        CompletableFuture<Integer> job4 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("除以 10 任务开始");
//            num = num / 10;
//            return num;
//        });
//        list.add(job4);
////多任务合并
//        List<Integer> collect =
//                list.stream().map(CompletableFuture<Integer>::join).collect(Collectors.toList());
//        System.out.println(collect);
//    }


    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        CompletableFuture<Integer>[] futures = new CompletableFuture[4];
        CompletableFuture<Integer> job1 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(5000);
                System.out.println("加 10 任务开始");
                num += 10;
                return num;
            }catch (Exception e){
                return 0;
            }
        });
        futures[0] = job1;
        CompletableFuture<Integer> job2 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(2000);
                System.out.println("乘以 10 任务开始");
                num = num * 10;
                return num;
            }catch (Exception e){
                return 1;
            }
        });
        futures[1] = job2;
        CompletableFuture<Integer> job3 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(3000);
                System.out.println("减以 10 任务开始");
                num = num - 10;
                return num;
            }catch (Exception e){
                return 2;
            }
        });
        futures[2] = job3;
        CompletableFuture<Integer> job4 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(4000);
                System.out.println("除以 10 任务开始");
                num = num / 10;
                return num;
            }catch (Exception e){
                return 3;
            }
        });
        futures[3] = job4;
        CompletableFuture<Object> future = CompletableFuture.anyOf(futures);
        System.out.println(future.get());
    }

}
