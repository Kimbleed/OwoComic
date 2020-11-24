package com.example.cen.owocomic.page;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;



import com.example.cen.owocomic.utils.ScreenUtil;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {

    // 弱引用BaseActivity对象，推荐使用该机制
    public Handler mHandler;

    private static class ActivityHandler extends Handler {

        WeakReference<BaseActivity> mRefActivity;

        private ActivityHandler(BaseActivity activity) {
            mRefActivity = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mRefActivity != null) {
                BaseActivity activity = mRefActivity.get();
                if (activity != null && !activity.isFinishing()) {
                    activity.handleMessage(msg);
                }
            }
        }
    }

    /**
     * 如果需要用到mHandler发送消息，需要子类重载该函数
     *
     * @param msg
     */
    public void handleMessage(Message msg) {
        mHandler.handleMessage(msg);
    }

    /**
     * 布局id
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置布局文件
        setContentView(getLayoutId());

        //activity 贴 系统状态栏
        ScreenUtil.setLayoutTopToBar(this);

        //
        mHandler = new ActivityHandler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
