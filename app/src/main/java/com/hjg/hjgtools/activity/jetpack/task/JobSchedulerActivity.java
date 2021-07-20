package com.hjg.hjgtools.activity.jetpack.task;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.MapUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.base.view.MyDividerItemDecoration;
import com.hjg.base.view.flyco.dialog.listener.OnOperItemClickL;
import com.hjg.base.view.flyco.dialog.widget.ActionSheetDialog;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityJobSchedulerBinding;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobSchedulerActivity extends HJGDatabindingBaseActivity<ActivityJobSchedulerBinding> {


    private JobScheduler scheduler;
    private int jobId = -1;
    private BaseAdapter<JobInfo> baseAdapter;
    private Map<Integer, String> netMap;

    @Override
    protected int getContentID() {
        return R.layout.activity_job_scheduler;
    }

    @Override
    protected void initViewAction() {
        scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        databinding.tvAttation.setText(new TextSpanUtils.Builder("注意：任务执行期间，应用可以被杀死，不影响任务的执行;").append("任务运行严格按照创建规则来执行；").setUnderline().append("任务执行的service需要在manifest中注册。").create());


        netMap = new HashMap();
        netMap.put(JobInfo.NETWORK_TYPE_ANY, "需要网络连接");
        netMap.put(JobInfo.NETWORK_TYPE_NONE, "任意网络都可以");
        netMap.put(JobInfo.NETWORK_TYPE_UNMETERED, "无线网络接入");
        netMap.put(JobInfo.NETWORK_TYPE_CELLULAR, "移动数据网络");


        //获得所有的jobinfo,页面显示recyclerView
        List<JobInfo> jobInfos = scheduler.getAllPendingJobs();
        databinding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        databinding.recyclerView.addItemDecoration(new MyDividerItemDecoration());
        databinding.recyclerView.setAdapter(baseAdapter = new BaseAdapter<JobInfo>(this, R.layout.item_tv, jobInfos) {

            @Override
            public void convert(BaseViewHolder holder, JobInfo jobInfo, int position) {
                TextView textView = holder.getView(R.id.tv);
                textView.setText(new TextSpanUtils.Builder("任务ID：").setBold()
                        .append(jobInfo.getId() + "").appendNewLine()
                        .append("任务需要网络状态:").setBold()
                        .append(netMap.get(jobInfo.getNetworkType())).appendNewLine()
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
        Collection<String> allNetInfoList = MapUtils.getValue(netMap);
        String[] arrayNetString = ArrayListUtils.list2Array(allNetInfoList);
        ActionSheetDialog ActionSheetDialog = new ActionSheetDialog(activity, arrayNetString, null);
        ActionSheetDialog.setTitle("选择网络条件");
        ActionSheetDialog.show();
        ActionSheetDialog.itemTextColor(ResUtils.getColor(R.color.black));
        ActionSheetDialog.mCancelTextColor(ResUtils.getColor(R.color.black));
        ActionSheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                databinding.btnNetInfo.setText(arrayNetString[position]);
                ActionSheetDialog.dismiss();
            }
        });
    }


    /**
     * 开启任务
     *
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startJob(View view) {
        //根据用户选择来初始化job
        //第一次运行时延长时间
        String firstDelayTimeString = databinding.etFirstDelayTime.getText().toString();
        if (StrUtil.isEmpty(firstDelayTimeString)) {
            firstDelayTimeString = "0";
        }
        long firstDelayTimeLong = Long.parseLong(firstDelayTimeString) * 60 * 1000;

        //获取到用户选择网络条件中文
        String netInfo = databinding.btnNetInfo.getText().toString();
        //根据中文来获取到相应的netflag，用于设置job的执行网络条件
        Integer netFlag = (Integer) (MapUtils.getKey(netMap, netInfo).get(0));

        //设置循环时间(分钟)
        String periodicTimeString = databinding.etJobTime.getText().toString();
        if (Long.parseLong(periodicTimeString) < 15) {
            D.showShort("循环时间不能小于15分钟");
            return;
        }
        long periodicTimeLong = Long.parseLong(periodicTimeString) * 60 * 1000;


        jobId = jobId + 1;
        //开启job
        ComponentName jobService = new ComponentName(this, JobSchedulerService.class);

        //传输数据
        PersistableBundle extras = new PersistableBundle();
        extras.putInt("jobId", jobId);

        JobInfo jobInfo = new JobInfo.Builder(jobId, jobService)
                .setExtras(extras)
//                .setMinimumLatency(0)// 任务最少延迟时间,不能在定期作业上调用setMinimumLatency()
//                .setOverrideDeadline(60 * 000)// 任务deadline，设置最大的延迟时间，一旦设置了这个属性，不管其他条件怎么样，jobinfo到了时间就一定会执行。
                .setRequiredNetworkType(netFlag)// 设置在什么样的网络下启动jobinfo，有三种
//                .setRequiresCharging(false)// 设置需要在充电的情况下才会启动jobinfo
                .setPeriodic(periodicTimeLong)//循环周期，最小15分钟,这里不允许时间过于小
//                .setRequiresDeviceIdle(false)//设备需要在空闲的时候，才会启动job
                .setPersisted(true) //设备重启后是否继续执行
//                .setTriggerContentMaxDelay(firstDelayTimeLong)//首次执行前最大延迟时间，这里不生效
                .setBackoffCriteria(3000, JobInfo.BACKOFF_POLICY_LINEAR) //设置退避/重试策略
                .build();
        scheduler.schedule(jobInfo);


        getJobList();
    }


    /**
     * 获取当前app的任务列表
     */
    public void getJobList() {
        List<JobInfo> jobInfos = scheduler.getAllPendingJobs();
        baseAdapter.setNewData(jobInfos);
    }


    /**
     * 停止任务
     *
     * @param view
     */
    public void stopJob(View view) {
        D.showShort("停止任务");
        scheduler.cancelAll();
        getJobList();
    }
}