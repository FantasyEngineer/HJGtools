package com.hjg.hjgtools.activity.encrytion;

import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

import com.hjg.base.util.AESUtil;
import com.hjg.base.util.D;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class EncryptionActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected CharSequence setDesString() {
        return new TextSpanUtils.Builder("这里是需要加密的数据").create();
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "AES对称加密算法"));
        recyclerListBeans.add(new RecyclerListBean("AES加密"));
        recyclerListBeans.add(new RecyclerListBean("AES解密"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "RSA非对称加密算法"));
        recyclerListBeans.add(new RecyclerListBean("RSA加密"));
        recyclerListBeans.add(new RecyclerListBean("RSA解密"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "中文UTF加码与解码"));
        recyclerListBeans.add(new RecyclerListBean("加码"));
        recyclerListBeans.add(new RecyclerListBean("解码"));


        return recyclerListBeans;
    }

    String pwd = "password";

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);

        switch (recyclerListBean.getTitle()) {
            case "AES加密":
                try {
                    String encryptData = AESUtil.encrypt(pwd, getDesString());
                    SpannableStringBuilder spannableStringBuilder = getSpannableStringBuilder(encryptData);
                    tvDes.setText(spannableStringBuilder);
                } catch (Exception e) {
                    D.showShort(e.getMessage());
                }
                break;
            case "AES解密":
                try {
                    String encryptData = getDesString().replace("重置", "");
                    String decryptData = AESUtil.decrypt(pwd, encryptData);
                    tvDes.setText(decryptData);
                } catch (Exception e) {
                    D.showShort(e.getMessage());
                }
                break;
        }
    }

    /**
     * 构造重置，以及点击事件
     *
     * @param encryptData 加密好的数据
     * @return
     */
    private SpannableStringBuilder getSpannableStringBuilder(String encryptData) {
        return new TextSpanUtils.Builder(encryptData).appendNewLine().append("重置").setClickSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                tvDes.setText("这里是需要加密的数据");
            }
        }).create();
    }

    /**
     * 被加了SpannableStringBuilder加密数据，需要去掉重置两个字
     *
     * @param data 加了重置二字的数据
     * @return
     */
    private String getOriginEncryptData(String data) {
        return data.replace("重置", "");
    }
}