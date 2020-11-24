package com.example.cen.owocomic.mvp;

import com.example.cen.owocomic.OwoApplication;
import com.example.cen.owocomic.greendao.bean.Chapter;
import com.example.cen.owocomic.greendao.bean.Comic;
import com.example.cen.owocomic.greendao.gen.DaoSession;
import com.example.cen.owocomic.http.HttpClient;
import com.example.cen.owocomic.http.IComicApiService;
import com.example.cen.owocomic.http.bean.ComicChapterHttpBean;
import com.example.cen.owocomic.mvp.base.MvpPresenter;
import com.example.cen.owocomic.mvp.contract.ComicChapterContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComicDetailInfoPresenter extends MvpPresenter<ComicChapterContract.View> implements ComicChapterContract.Presenter {

    protected ComicChapterContract.Model model;
    protected DaoSession daoSession = ((OwoApplication) OwoApplication.CTX).getDaoSession();

    public ComicDetailInfoPresenter(ComicChapterContract.View view) {
        super(view);
        model = new ComicChapterContract.Model() {
            @Override
            public Call<ComicChapterHttpBean> fetchComicDetailInfo(String name) {
                return HttpClient.instance().getApiService(IComicApiService.class).fetchComicChapters(name);
            }
        };

    }

    private ComicChapterHttpBean getComicChapter(){
        List<Chapter> chapterList = daoSession.getChapterDao().loadAll();
        ComicChapterHttpBean bean = new ComicChapterHttpBean();
        List<String> chapterStrList = new ArrayList<>();
        for (Chapter chapter : chapterList) {
            chapterStrList.add(chapter.getName());
        }
        bean.chapters = chapterStrList;
        return bean;
    }

    @Override
    public void fetchComicDetailInfo(String name) {
        view.showLoading();
        view.onSuccess(getComicChapter());
        model.fetchComicDetailInfo(name).enqueue(new Callback<ComicChapterHttpBean>() {
            @Override
            public void onResponse(Call<ComicChapterHttpBean> call, Response<ComicChapterHttpBean> response) {
                view.hideLoading();
                view.onSuccess(response.body());
                ComicChapterHttpBean bean = response.body();
                for (String chapterName : bean.chapters) {
                    Chapter chapter = new Chapter();
                    chapter.setComic(name);
                    chapter.setName(chapterName);
                    daoSession.getChapterDao().insertOrReplace(chapter);
                }


                for (Comic comic : daoSession.getComicDao().loadAll()) {
                    if (comic.getName().equals(name)) {
                        comic.setFaceUrl(bean.faceUrl);
                        daoSession.getComicDao().insertOrReplace(comic);
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<ComicChapterHttpBean> call, Throwable t) {
                view.hideLoading();
                view.onSuccess(getComicChapter());

            }
        });

    }
}
