package com.hjg.hjgtools.activity.task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityJobSchedulerBinding;

import java.util.List;

public class JobSchedulerActivity extends HJGDatabindingBaseActivity<ActivityJobSchedulerBinding> {


    private JobScheduler scheduler;
    private int jobId = -1;
    private long periodicTime = 15 * 60 * 1000;//最少15分钟

    @Override
    protected int getContentID() {
        return R.layout.activity_job_scheduler;
    }

    @Override
    protected void initViewAction() {
        scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        databinding.tvAttation.setText(new TextSpanUtils.Builder("注意：任务执行期间，应用可以被杀死，不影响任务的执行;").append("任务运行严格按照创建规则来执行；").setUnderline().append("任务执行的service需要在manifest中注册。").create());


        //获得所有的jobinfo
        List<JobInfo> jobInfos = scheduler.getAllPendingJobs();


    }


    public void startJob(View view) {
        jobId = jobId + 1;
        //开启job
        ComponentName jobService = new ComponentName(this, JobSchedulerService.class);

        //传输数据
        PersistableBundle extras = new PersistableBundle();
        extras.putInt("jobId", jobId);

        JobInfo jobInfo = new JobInfo.Builder(jobId, jobService)
                .setExtras(extras)
//                .setMinimumLatency(5 * 1000)// 任务最少延迟时间
//                .setOverrideDeadline(60 * 000)// 任务deadline，设置最大的延迟时间，一旦设置了这个属性，不管其他条件怎么样，jobinfo到了时间就一定会执行。
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)// 设置在什么样的网络下启动jobinfo，有三种
                // NETWORK_TYPE_ANY , 任意网络都可以
                // NETWORK_TYPE_NONE. 默认的，有没有网络都执行
                // NETWORK_TYPE_UNME_TERED ，无线网络接入
//                .setRequiresCharging(false)// 设置需要在充电的情况下才会启动jobinfo
                .setPeriodic(periodicTime)//循环周期，最小15分钟,这里不允许时间过于小
//                .setRequiresDeviceIdle(false)//设备需要在空闲的时候，才会启动job
                .setPersisted(true) //设备重启后是否继续执行
//                .setTriggerContentMaxDelay(15 * 1000)//首次执行前最大延迟时间
                .setBackoffCriteria(3000, JobInfo.BACKOFF_POLICY_LINEAR) //设置退避/重试策略
                .build();
        scheduler.schedule(jobInfo);
    }


    public void stopJob(View view) {
        D.showShort("停止任务");
        scheduler.cancelAll();

    }
}