package com.hjg.hjgtools.activity.task;

import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import com.hjg.base.util.D;
import com.hjg.base.util.NotificationUtils;
import com.hjg.hjgtools.MainActivity;
import com.hjg.hjgtools.R;


/**
 * job任务
 */
public class JobSchedulerService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
//        一般我们的逻辑都是在这里面操作的，但是这个是主线程的，所以我们最好在子线程里面执行耗时操作。
//        返回false的话，jobfinish()中的true属性不在有用，也就是不再重试。
//        只有返回true,  jobfinish(true)才会有用。如果没有调用jobfinish  Service会直接destory掉
        //一般都是返回true,表示还有任务要做。
        D.showShort("执行job");
        int notificationID = params.getExtras().getInt("jobId");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(getBaseContext(), MainActivity.class), PendingIntent.FLAG_ONE_SHOT);
        NotificationUtils.sendNotification(getBaseContext(), "HOUJIGUO", notificationID, "JobScheduler", "job任务" + notificationID + "正在执行中", R.mipmap.ic_launcher, "", "", pendingIntent);
        return false;
    }
//    1 finalvoid  jobFinished(JobParameters params,boolean needsReschedule)
//    告诉JobManager 已经完成了工作，如果第二个参数为false，就是不需要重试这个jobinfo，

    @Override
    public boolean onStopJob(JobParameters params) {
//        这个方法的回调很奇怪，我只验证了一种情况，就是onstartjob()中没有调用jobfinish()，然后把网络关掉，这时候就会回调这个方法。其他的我没去发现，贴上原话！！
//        一般都返回false.
        D.showShort("关闭job");
        return false;
    }
}

