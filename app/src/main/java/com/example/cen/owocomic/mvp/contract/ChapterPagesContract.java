package com.example.cen.owocomic.mvp.contract;

import com.example.cen.owocomic.http.bean.ComicPageBean;
import com.example.cen.owocomic.mvp.base.ICommonMvpView;

import retrofit2.Call;

public class ChapterPagesContract {
    public interface Model{
        Call<ComicPageBean> fetchChapterPages(String name,String chapter);
    }

    public interface View extends ICommonMvpView<ComicPageBean> {

    }

    public interface Presenter{
        void fetchChapterPages(String name,String chapter);
    }
}
