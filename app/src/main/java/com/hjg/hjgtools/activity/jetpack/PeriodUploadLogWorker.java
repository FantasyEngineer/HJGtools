package com.hjg.hjgtools.activity.jetpack;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.hjg.base.util.D;
import com.hjg.base.util.NotificationUtils;
import com.hjg.base.util.RandomUtils;
import com.hjg.hjgtools.R;

/**
 * 周期上传的worker
 */
public class PeriodUploadLogWorker extends Worker {
    public PeriodUploadLogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
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
        D.showShort("执行了周期任务，并且收到了值" + data);
        NotificationUtils.testSendNotification(getApplicationContext(), "我是周期任务", "我是周期任务的内容");
        return Result.success();
    }
}
