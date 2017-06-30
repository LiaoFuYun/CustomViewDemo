package com.lfy.customviewdemo.databinding;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lfy.customviewdemo.R;

public class DataBindingActivity extends AppCompatActivity implements IDatabinding.View {
    private ProgressDialog dialog;
    private User user;
    private com.lfy.customviewdemo.databinding.ActivityDatabindingBinding binding;
    private DatabindingPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在登录中");

        presenter = new DatabindingPresenter(this);
        binding.setPresenter(presenter);

        user = new User("", "");
        binding.setUser(user);
    }

    @Override
    public String getUserName() {
        return user.getUserName();
    }

    @Override
    public String getPwd() {
        return user.getPwd();
    }

    @Override
    public void clear() {
        user.setUserName("");
        user.setPwd("");
        binding.setUser(user);
    }

    @Override
    public void showWaiting() {
        showWaiting(true);
    }

    @Override
    public void dismissWaiting() {
        showWaiting(false);
    }

    @Override
    public void loginSuccess() {
        showToast("登录成功");
    }

    @Override
    public void loginFail() {
        showToast("登录失败");
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void showWaiting(boolean isShow) {
        if (isShow) {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        } else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
