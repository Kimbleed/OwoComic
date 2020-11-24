package com.example.cen.owocomic.mvp.contract;

import com.example.cen.owocomic.http.bean.ComicFaceHttpBean;
import com.example.cen.owocomic.mvp.base.ICommonMvpView;

import retrofit2.Call;

public class MainContract {
    public interface Model{
        Call<ComicFaceHttpBean> getComicList();
    }

    public interface View<T> extends ICommonMvpView<T> {

    }

    public interface Presentter{
        void loadComicList();
    }
}
