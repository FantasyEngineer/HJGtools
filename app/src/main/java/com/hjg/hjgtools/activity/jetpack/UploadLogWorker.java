package com.hjg.hjgtools.activity.jetpack;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.hjg.base.util.D;
import com.hjg.base.util.NotificationUtils;

/**
 * 上传日志的功能
 */
public class UploadLogWorker extends Worker {
    public UploadLogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    /**
     * 耗时任务写在这里面
     *
     * @return* 执行成功返回Result.success()
     * 执行失败返回Result.failure()
     * 需要重新执行返回Result.retry()  系统会有默认的指数退避策略来帮你重试任务，你也可以通过setBackoffCriteria()方法，自定义指数退避策略。
     */
    @NonNull
    @Override
    public Result doWork() {

        String data = getInputData().getString("data");
        NotificationUtils.testSendNotification(getApplicationContext(), "我是单次任务", "我是单次任务的内容");


        Data data1 = new Data.Builder().putString("data", "单次执行成功返回的").build();
        return Result.success(data1);
    }
}
