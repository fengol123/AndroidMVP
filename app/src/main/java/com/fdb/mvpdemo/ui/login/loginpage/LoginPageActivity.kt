package com.fdb.mvpdemo.ui.login.loginpage

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.fdb.baselibrary.base.BaseActivity
import com.fdb.mvpdemo.R
import com.fdb.mvpdemo.ui.mine.collect.CollectActivity

class LoginPageActivity : BaseActivity<LoginPageContract.Presenter?>(), LoginPageContract.View {
    @JvmField
    @BindView(R.id.et_username)
    var mEtUsername: EditText? = null
    @JvmField
    @BindView(R.id.et_psw)
    var mEtPsw: EditText? = null
    @JvmField
    @BindView(R.id.tv_tips)
    var mTvTips: TextView? = null
    @JvmField
    @BindView(R.id.btn_login)
    var mBtnLogin: Button? = null

    override fun createPresenter(): LoginPageContract.Presenter {
        return LoginPagePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initialize() {
        ButterKnife.bind(this)
    }

    override fun showError(msg: String) {
        mTvTips!!.text = msg
    }

    override fun enterHome() {
        val intent = Intent(activity, CollectActivity::class.java)
        startActivity(intent)
        finish()
    }

    @OnClick(R.id.btn_login)
    fun onViewClicked() {
        val userName = mEtUsername!!.text.toString()
        val psw = mEtPsw!!.text.toString()
        presenter!!.login(userName, psw)
    }
}