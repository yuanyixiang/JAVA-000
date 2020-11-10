package Thread;

import java.util.concurrent.*;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/9 7:38 下午
 */
public class ThreadMethod {
    private static volatile int result;

    public static void main(String[] args) {
        demo7();
    }
    private static void  demo1(){
        Thread thread = new Thread(() -> {
            result = sum();
        });
        thread.start();
        while(result == 0){ };
        System.out.println(result);
    }

    private static void demo2(){
        Thread thread = new Thread(()->{
            result = sum();
        });
        thread.start();
        try {
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(result);
    }

    private static CountDownLatch countDownLatch  = new CountDownLatch(1);
    private static void demo3(){
        Thread thread = new Thread(()->{
            result = sum();
            countDownLatch.countDown();
        });
        thread.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(1,()->{
        System.out.println(result);
    });
    private static void demo4(){
        Thread thread = new Thread(()->{
            result = sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private static void demo5(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Future<Integer> futrue = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        try {
            System.out.println(futrue.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void demo6(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        executorService.submit(futureTask);
        executorService.shutdown();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void demo7(){
        CompletableFuture.supplyAsync(() -> sum())
                .thenAccept(v -> {System.out.println(""+v);});
    }

    private static int sum() {
        return fibo(36);
    }
    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
