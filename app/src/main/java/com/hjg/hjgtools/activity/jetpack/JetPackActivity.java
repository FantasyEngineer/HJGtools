package com.hjg.hjgtools.activity.jetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityJetPackBinding;

import java.util.concurrent.TimeUnit;


/**
 * 需要云同步的项目，感觉非常合适
 * 或者在某个时间点弹出一个通知。
 */
public class JetPackActivity extends HJGDatabindingBaseActivity<ActivityJetPackBinding> {

    private OneTimeWorkRequest uploadWorkRequest;

    @Override
    protected int getContentID() {
        return R.layout.activity_jet_pack;
    }

    @Override
    protected void initViewAction() {


        //workmanager
//        工作约束
//        使用工作约束明确定义工作运行的最佳条件。（例如，仅在设备采用 Wi-Fi 网络连接时、当设备处于空闲状态或者有足够的存储空间时运行。）
//        强大的调度
//        WorkManager 允许您使用灵活的调度窗口调度工作，以运行一次性或重复工作。您还可以对工作进行标记或命名，以便调度唯一的、可替换的工作以及监控或取消工作组。已调度的工作存储在内部托管的 SQLite 数据库中，由 WorkManager 负责确保该工作持续进行，并在设备重新启动后重新调度。此外，WorkManager 遵循低电耗模式等省电功能和最佳做法，因此您在这方面无需担心。
//        灵活的重试政策
//        有时工作会失败。WorkManager 提供了灵活的重试政策，包括可配置的指数退避政策。
//        工作链
//        对于复杂的相关工作，您可以使用流畅自然的接口将各个工作任务串联起来，这样您便可以控制哪些部分依序运行，哪些部分并行运行。

        //设置触发条件
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build();

        //设置一次OneTimeWorkRequest还是周期PeriodicWorkRequest
//        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadLogWorker.class)
//                .setConstraints(constraints)//设置触发条件
//                .build();

        //设置延迟执行任务。假设你没有设置触发条件，或者当你设置的触发条件符合系统的执行要求，
        //此时，系统有可能立刻执行该任务，但如果你希望能够延迟执行，那么可以通过setInitialDelay()方法，延后任务的执行。
//        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadLogWorker.class)
//                .setInitialDelay(10, TimeUnit.SECONDS)//符合触发条件后，延迟10秒执行
//                .build();

//        设置指数退避策略。假如Worker线程的执行出现了异常，比如服务器宕机，那么你可能希望过一段时间，重试该任务。那么你可以在Worker的doWork()方法中返回Result.retry()，系统会有默认的指数退避策略来帮你重试任务，你也可以通过setBackoffCriteria()方法，自定义指数退避策略。
        uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadLogWorker.class)
                .setInputData(new Data.Builder().putString("data", "你好，我是任务发起时传入的参数").build())//设置参数
                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)//设置指数退避算法
                .setConstraints(constraints)//设置触发条件
                .setInitialDelay(10, TimeUnit.SECONDS)//符合触发条件后，延迟10秒执行
                .build();


//        为任务设置Tag标签。设置Tag后，你就可以通过该抱歉跟踪任务的状态WorkManager.getWorkInfosByTagLiveData(String tag)或者取消任务WorkManager.cancelAllWorkByTag(String tag)。
//        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadLogWorker.class)
//                .addTag("UploadTag")
//                .build();




        //使用liveData监听，监听单次执行成功的返回
//        如果发出这类通知，则表明任务彻底结束，而周期任务不会彻底终止，会一直执行下去，所以我们在使用LiveData观察周期任务时，不会收到Success这类的通知。这也是我们需要注意的地方。
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    String outputData = workInfo.getOutputData().getString("data");
                    D.showShort(outputData);
                }
            }
        });
    }


    public void oneTimeWorkRequest(View view) {
        //将任务交给系统执行
        WorkManager.getInstance(this).enqueue(uploadWorkRequest);

        D.showLong("开启任务，网络必须连接，10秒之后开始执行");

    }


    public void periodTimeWorkRequest(View view) {

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)//网络连接
                .setRequiresCharging(true)//充电状态
                .setRequiresBatteryNotLow(false)//非低电量模式
                .build();

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(PeriodUploadLogWorker.class, 15, TimeUnit.MINUTES)
                .setInputData(new Data.Builder().putString("data", "你好，我是周期任务发起时传入的参数").build())//设置参数
                .setConstraints(constraints)
                .addTag("period")
                .build();


        WorkManager.getInstance(this).enqueue(periodicWorkRequest);
        D.showLong("开启任务，网络必须连接，开始执行");

    }

    public void cancelAll(View view) {

        D.showShort("取消所有任务");
        WorkManager.getInstance(this).cancelAllWork();
//        WorkManager.getInstance(this).cancelAllWorkByTag("period");


        //任务链。如果你有一系列的任务需要顺序执行，那么可以利用WorkManager.beginWith().then().then()...enqueue()方法。例如：我们在上传数据之前，需要先对数据进行压缩。
        //WorkManager.getInstance(this).beginWith(compressWorkRequest).then(uploadWorkRequest).enqueue();


        //假设在上传数据之前，除了压缩数据，还需要更新本地数据。压缩与更新本地数据二者没有顺序，但与上传数据存在先后顺序。
//        WorkManager.getInstance(this).beginWith(compressWorkRequest, updateLocalWorkRequest).then(uploadWorkRequest).enqueue();


//        假设有更复杂的任务链，你还可以考虑使用WorkContinuation.combine()方法，将任务链组合起来。

        //WorkContinuation workContinuation1 =  WorkManager.getInstance(this).beginWith(WorkRequestA).then(WorkRequestB);
        //WorkContinuation workContinuation2 =  WorkManager.getInstance(this).beginWith(WorkRequestC).then(WorkRequestD);
        //List<WorkContinuation> taskList = new ArrayList<>();
        //taskList.add(workContinuation1);
        //taskList.add(workContinuation2);
        //WorkContinuation.combine(taskList).then(WorkRequestE).enqueue();


    }
}