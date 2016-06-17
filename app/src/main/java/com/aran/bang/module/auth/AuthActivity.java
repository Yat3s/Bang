package com.aran.bang.module.auth;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.aran.bang.R;
import com.aran.bang.base.BaseActivity;
import com.aran.bang.module.MainActivity;
import com.aran.bang.module.UserModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Yat3s on 6/17/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class AuthActivity extends BaseActivity {
    private static final String TAG = "AuthActivity";
    @Bind(R.id.phone_number_et)
    EditText phoneNumberEt;
    @Bind(R.id.verify_code_et)
    EditText verifyCodeEt;

    @Override
    protected int getRootViewId() {
        return R.layout.activity_auth;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    private void signUp(String phoneNumber, String verifyCode) {
        if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(verifyCode)) {
            showToast("Phone number or verify code can not is empty!");
            return;
        }
        UserModel userModel = new UserModel();
        userModel.setMobilePhoneNumber(phoneNumber);
        userModel.setUsername(phoneNumber);
        userModel.setPassword("administrator");
        userModel.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                showToast("Auth success!" + BmobUser.getCurrentUser(AuthActivity.this)
                        .getMobilePhoneNumber());
                startActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                showToast("Auth Failure! " + s);
            }
        });
    }

    private void signIn(String phoneNumber, String verifyCode) {
        if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(verifyCode)) {
            showToast("Phone number or verify code can not is empty!");
            return;
        }

        BmobUser.loginBySMSCode(this, phoneNumber, verifyCode, new LogInListener<UserModel>() {
            @Override
            public void done(UserModel userModel, BmobException e) {
                Log.d(TAG, "done: " + e.toString());
                showToast("login success!" + BmobUser.getCurrentUser(AuthActivity.this));
                startActivity(MainActivity.class);
            }
        });
    }

    @OnClick({R.id.send_verify_code_btn, R.id.sign_in_btn, R.id.sign_up_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_verify_code_btn:
                String phoneNumber = phoneNumberEt.getText().toString();
                if (phoneNumber.length() != 11) {
                    showToast("Your are drunk! check your phone number.");
                    return;
                }
                BmobSMS.requestSMSCode(AuthActivity.this, phoneNumber, "Aran is pretty girl!",
                        new RequestSMSCodeListener() {
                            @Override
                            public void done(Integer integer, BmobException e) {
                                showToast("Send success! ");
                            }
                        });
                break;
            case R.id.sign_up_btn:
                signUp(phoneNumberEt.getText().toString(), verifyCodeEt.getText().toString());
                break;

            case R.id.sign_in_btn:
                signUp(phoneNumberEt.getText().toString(), verifyCodeEt.getText().toString());
                break;
        }
    }
}
