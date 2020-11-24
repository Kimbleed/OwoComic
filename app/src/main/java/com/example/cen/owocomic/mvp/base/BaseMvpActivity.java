package com.example.cen.owocomic.mvp.base;

import android.os.Bundle;


import com.example.cen.owocomic.page.BaseActivity;

import androidx.annotation.Nullable;

public abstract class BaseMvpActivity<T extends MvpPresenter> extends BaseActivity {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = generatePresenter();
    }

    protected abstract T generatePresenter();

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();

    }
}
