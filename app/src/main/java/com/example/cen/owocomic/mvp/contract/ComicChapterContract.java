package com.example.cen.owocomic.mvp.contract;

import com.example.cen.owocomic.http.bean.ComicChapterHttpBean;
import com.example.cen.owocomic.mvp.base.ICommonMvpView;

import retrofit2.Call;

public class ComicChapterContract {
    public interface Model{
        Call<ComicChapterHttpBean> fetchComicDetailInfo(String name);
    }

    public interface View extends ICommonMvpView<ComicChapterHttpBean> {

    }

    public interface Presenter{
        void fetchComicDetailInfo(String name);
    }
}
