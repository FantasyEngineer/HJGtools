package com.hjg.hjgtools.activity.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.base.view.MyDividerItemDecoration;
import com.hjg.base.view.flyco.dialog.entity.DialogMenuItem;
import com.hjg.base.view.flyco.dialog.listener.OnOperItemClickL;
import com.hjg.base.view.flyco.dialog.widget.ActionSheetDialog;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityJobSchedulerBinding;

import java.util.ArrayList;
import java.util.List;

public class JobSchedulerActivity extends HJGDatabindingBaseActivity<ActivityJobSchedulerBinding> {


    private JobScheduler scheduler;
    private int jobId = -1;
    private long periodicTime = 15 * 60 * 1000;//最少15分钟
    private BaseAdapter<JobInfo> baseAdapter;

    @Override
    protected int getContentID() {
        return R.layout.activity_job_scheduler;
    }

    @Override
    protected void initViewAction() {
        scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        databinding.tvAttation.setText(new TextSpanUtils.Builder("注意：任务执行期间，应用可以被杀死，不影响任务的执行;").append("任务运行严格按照创建规则来执行；").setUnderline().append("任务执行的service需要在manifest中注册。").create());

        //获得所有的jobinfo,页面显示recyclerView
        List<JobInfo> jobInfos = scheduler.getAllPendingJobs();
        databinding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        databinding.recyclerView.addItemDecoration(new MyDividerItemDecoration());
        databinding.recyclerView.setAdapter(baseAdapter = new BaseAdapter<JobInfo>(this, R.layout.item_tv, jobInfos) {

            @Override
            public void convert(BaseViewHolder holder, JobInfo jobInfo, int position) {
                TextView textView = holder.getView(R.id.tv);
                String netInfo = "";
                if (jobInfo.getNetworkType() == JobInfo.NETWORK_TYPE_ANY) {//这项工作需要网络连接
                    netInfo = "需要网络连接";
                } else if (jobInfo.getNetworkType() == JobInfo.NETWORK_TYPE_NONE) {//任意网络都可以
                    netInfo = "任意网络都可以";
                } else if (jobInfo.getNetworkType() == JobInfo.NETWORK_TYPE_UNMETERED) {//无线网络接入
                    netInfo = "无线网络接入";
                } else if (jobInfo.getNetworkType() == JobInfo.NETWORK_TYPE_CELLULAR) {// 移动数据网络
                    netInfo = "移动数据网络";
                }
                textView.setText(new TextSpanUtils.Builder("任务ID：").setBold()
                        .append(jobInfo.getId() + "").appendNewLine()
                        .append("任务需要网络状态:").setBold()
                        .append(netInfo).appendNewLine()
                        .append("是否循环:").setBold()
                        .append(jobInfo.isPeriodic() + "").appendNewLine()
                        .append("重启是否继续:").setBold()
                        .append(jobInfo.isPersisted() + "")
                        .create());
            }
        });

    }


    /**
     * 选择网络类型
     *
     * @param view
     */
    public void selectNetType(View view) {
        ArrayList<DialogMenuItem> dialogMenuItems = new ArrayList<>();
        dialogMenuItems.add(new DialogMenuItem("需要网络连接", 0));
        dialogMenuItems.add(new DialogMenuItem("任意网络都可以", 0));
        dialogMenuItems.add(new DialogMenuItem("无线网络接入", 0));
        dialogMenuItems.add(new DialogMenuItem("移动数据网络", 0));
        ActionSheetDialog ActionSheetDialog = new ActionSheetDialog(activity, dialogMenuItems, null);
        ActionSheetDialog.setTitle("选择网络条件");
        ActionSheetDialog.show();
        ActionSheetDialog.itemTextColor(ResUtils.getColor(R.color.black));
        ActionSheetDialog.mCancelTextColor(ResUtils.getColor(R.color.black));
        ActionSheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogMenuItem dialogMenuItem = dialogMenuItems.get(position);
                databinding.btnNetInfo.setText(dialogMenuItem.mOperName);
                ActionSheetDialog.dismiss();
            }
        });
    }


    /**
     * 开启任务
     *
     * @param view
     */
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


        getJobList();
    }


    public void getJobList() {
        List<JobInfo> jobInfos = scheduler.getAllPendingJobs();
        baseAdapter.setNewData(jobInfos);
    }


    public void stopJob(View view) {
        D.showShort("停止任务");
        scheduler.cancelAll();

    }
}