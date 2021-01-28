package com.hjg.hjgtools.activity.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    static class MyCallable implements Callable<Integer> {
//
//        @Override
//        public Integer call() throws Exception {
//            LogUtil.i(TAG, "call():在这里模拟一些耗时操作...");
//            Thread.sleep(3000);
//            int sum = 0;
//            for (int i = 0; i <= 100; i++) {
//                sum += i;
//            }
//            return sum;
//        }
//    }
//
//    private void calculate() {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Future<Integer> future = executorService.submit(new MyCallable());
//
//        try {
//            LogUtil.i(TAG, "任务运行的结果：" + future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
}