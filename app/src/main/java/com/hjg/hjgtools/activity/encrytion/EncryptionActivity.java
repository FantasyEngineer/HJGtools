package com.hjg.hjgtools.activity.encrytion;

import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.AESUtil;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityEncryptionBinding;

public class EncryptionActivity extends HJGDatabindingBaseActivity<ActivityEncryptionBinding> {


    @Override
    protected int getContentID() {
        return R.layout.activity_encryption;
    }

    @Override
    protected void initViewAction() {


    }


    /**
     * AES解密加密
     *
     * @param view
     * @throws Exception
     */
    public void AES(View view) throws Exception {
        String data = databinding.etInput.getText().toString();
        String pwd = "password";
        switch (view.getId()) {
            case R.id.btnAESEncryption://AEs加密
                data = AESUtil.encrypt(pwd, data);
                databinding.tvContentEn.setText(data);
                break;
            case R.id.btnAESDecryption://aes解密
                data = AESUtil.decrypt(pwd, databinding.tvContentEn.getText().toString());
                databinding.tvContentDe.setText("解密之后：" + data);
                break;
        }
    }
}