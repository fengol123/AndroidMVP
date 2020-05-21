package com.fdb.mvpdemo.ui.login.loginpage

import android.content.Intent
import android.view.View
import butterknife.ButterKnife
import com.fdb.baselibrary.base.BaseActivity
import com.fdb.mvpdemo.R
import com.fdb.mvpdemo.ui.mine.collect.CollectActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginPageActivity : BaseActivity<LoginPageContract.Presenter?>(), LoginPageContract.View {

    override fun createPresenter(): LoginPageContract.Presenter {
        return LoginPagePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initialize() {
        ButterKnife.bind(this)

        btn_login.setOnClickListener(View.OnClickListener {
            val userName = et_username!!.text.toString()
            val psw = et_psw!!.text.toString()
            presenter!!.login(userName, psw)
        })
    }

    override fun showError(msg: String) {
        tv_tips!!.text = msg
    }

    override fun enterHome() {
        val intent = Intent(activity, CollectActivity::class.java)
        startActivity(intent)
        finish()
    }

}