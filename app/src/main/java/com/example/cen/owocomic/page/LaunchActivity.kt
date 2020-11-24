package com.example.cen.owocomic.page;

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.View
import com.example.cen.owocomic.OwoApplication
import com.example.cen.owocomic.R
import com.example.cen.owocomic.utils.PermissionHelper
import kotlinx.android.synthetic.main.activity_launch.*


class LaunchActivity : BaseActivity() {

    var MSG_SKIP_COUNT_DOWN = 1;
    var mCountDown = 3;

    override fun getLayoutId(): Int {
        return R.layout.activity_launch;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

        if (PermissionHelper.checkPermission(this@LaunchActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            countDown()
        }
    }

    fun initView() {
        btn_skip.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                OwoApplication.sEverLaunch = true
                var intent = Intent()
                intent.setClass(this@LaunchActivity, MainActivity::class.java)
                startActivity(intent)
                this@LaunchActivity.finish()
                mHandler.removeCallbacksAndMessages(null)
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults, object : PermissionHelper.OnPermissionHandleOverListener {
            override fun onHandleOver(isOkExactly: Boolean, result: MutableMap<String, Int>?) {
                if (isOkExactly) {
                    countDown()
                }
            }
        })
    }

    override fun handleMessage(msg: Message?) {
        when (msg?.what) {
            MSG_SKIP_COUNT_DOWN -> {
                countDown()

            }
        }
    }

    //广告倒计时
    fun countDown() {
        if (mCountDown < 0 || OwoApplication.sEverLaunch) {
            OwoApplication.sEverLaunch = true
            var intent = Intent().setComponent(ComponentName(this@LaunchActivity, MainActivity::class.java))
            startActivity(intent)
            this@LaunchActivity.finish()
            return
        }
        btn_skip.setText(getString(R.string.skip) + mCountDown--)
        mHandler.sendEmptyMessageDelayed(MSG_SKIP_COUNT_DOWN, 1000)
    }

}
