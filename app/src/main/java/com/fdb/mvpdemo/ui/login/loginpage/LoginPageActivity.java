package com.fdb.mvpdemo.ui.login.loginpage;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fdb.baselibrary.base.BaseActivity;
import com.fdb.mvpdemo.R;
import com.fdb.mvpdemo.ui.mine.collect.CollectActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginPageActivity extends BaseActivity<LoginPageContract.Presenter> implements LoginPageContract.View {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_psw)
    EditText mEtPsw;
    @BindView(R.id.tv_tips)
    TextView mTvTips;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected LoginPageContract.Presenter createPresenter() {
        return new LoginPagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initialize() {
        ButterKnife.bind(this);
    }

    @Override
    public void showError(String msg) {
        mTvTips.setText(msg);
    }

    @Override
    public void enterHome() {
        Intent intent = new Intent(getActivity(), CollectActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String userName = mEtUsername.getText().toString();
        String psw = mEtPsw.getText().toString();
        getPresenter().login(userName, psw);
    }
}









