package com.example.cen.owocomic.mvp;

import android.util.Log;

import com.example.cen.owocomic.OwoApplication;
import com.example.cen.owocomic.greendao.bean.Page;
import com.example.cen.owocomic.greendao.gen.DaoSession;
import com.example.cen.owocomic.http.HttpClient;
import com.example.cen.owocomic.http.IComicApiService;
import com.example.cen.owocomic.http.bean.ComicPageBean;
import com.example.cen.owocomic.mvp.base.MvpPresenter;
import com.example.cen.owocomic.mvp.contract.ChapterPagesContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterPagesPresenter extends MvpPresenter<ChapterPagesContract.View> implements ChapterPagesContract.Presenter {

    protected ChapterPagesContract.Model model;
    protected DaoSession daoSession = ((OwoApplication) OwoApplication.CTX).getDaoSession();

    public ChapterPagesPresenter(ChapterPagesContract.View view) {
        super(view);
        model = new ChapterPagesContract.Model() {
            @Override
            public Call<ComicPageBean> fetchChapterPages(String name, String chapter) {
                return HttpClient.instance().getApiService(IComicApiService.class).fetchComicPages(name, chapter);
            }
        };

    }

    @Override
    public void fetchChapterPages(String name, String chapter) {
        view.showLoading();
        view.onCache(getChapterPagesDB());
        model.fetchChapterPages(name, chapter).enqueue(new Callback<ComicPageBean>() {
            @Override
            public void onResponse(Call<ComicPageBean> call, Response<ComicPageBean> response) {
                view.hideLoading();

                List<String> pageUrls = response.body().pages;
                for (int i = 0; i < pageUrls.size(); i++) {
                    Page page = new Page(name, chapter, pageUrls.get(i), i);
                    daoSession.getPageDao().insertOrReplace(page);
                }

                view.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ComicPageBean> call, Throwable t) {
                view.hideLoading();
                view.onSuccess(getChapterPagesDB());
            }
        });

    }

    private ComicPageBean getChapterPagesDB() {
        //                List<Page> pages = daoSession.getPageDao().queryRaw("where comic = ? & chapter =?", new String[]{name, chapter});
        List<Page> pages = daoSession.getPageDao().loadAll();
        List<String> pageUrls = new ArrayList<>();
        for (Page page : pages) {
            pageUrls.add(page.getUrl());
        }
        ComicPageBean pageBean = new ComicPageBean();
        pageBean.pages = pageUrls;
        return pageBean;
    }
}
