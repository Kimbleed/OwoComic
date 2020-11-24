package com.example.cen.owocomic.mvp.base;

public interface ICommonMvpView<T> {
    void showLoading();
    void hideLoading();
    void onError();
    void onCache(T data);
    void onSuccess(T data);
}
