package com.example.cen.owocomic.mvp;

import android.util.Log;

import com.example.cen.owocomic.OwoApplication;
import com.example.cen.owocomic.greendao.bean.Comic;
import com.example.cen.owocomic.greendao.gen.DaoSession;
import com.example.cen.owocomic.http.HttpClient;
import com.example.cen.owocomic.http.IComicApiService;
import com.example.cen.owocomic.http.bean.ComicFaceHttpBean;
import com.example.cen.owocomic.mvp.base.MvpPresenter;
import com.example.cen.owocomic.mvp.contract.MainContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends MvpPresenter<MainContract.View> implements MainContract.Presentter {

    protected MainContract.Model model;

    protected DaoSession daoSession = ((OwoApplication) OwoApplication.CTX).getDaoSession();


    public MainPresenter(MainContract.View view) {
        super(view);
        model = new MainContract.Model() {
            @Override
            public Call<ComicFaceHttpBean> getComicList() {
                return HttpClient.instance().getApiService(IComicApiService.class).fetchComicList();
            }
        };

    }

    @Override
    public void loadComicList() {
        view.showLoading();
        view.onCache(getComicListFromDB());
        model.getComicList().enqueue(new Callback<ComicFaceHttpBean>() {
            @Override
            public void onResponse(Call<ComicFaceHttpBean> call, Response<ComicFaceHttpBean> response) {
                view.hideLoading();
                //目前getComicList获取的数据中，不含faceUrl
                //取数据库的数据中的对应Comic 的faceUrl
                /**
                //服务器Comics
                List<Comic> comics = response.body().getComics();
                //数据库Comics
                List<Comic> comicsArrDB = daoSession.getComicDao().loadAll();

                //比较器
                Comparator comparator = new ComicComparator();

                //排序
                //网络数据
                Comic[] comicsArr = new Comic[comics.size()];
                comics.toArray(comicsArr);
                Arrays.sort(comicsArr, comparator);

                //DB数据
                Comic[] comicsArrDBArr = new Comic[comicsArrDB.size()];
                comicsArrDB.toArray(comicsArrDBArr);
                Arrays.sort(comicsArrDBArr, comparator);


                if (comicsArrDBArr.length == comicsArr.length) {
                    comics.clear();
                    for (int i = 0; i < comicsArr.length; i++) {
                        Comic c = comicsArr[i];
                        c.setFaceUrl(comicsArrDBArr[i].getFaceUrl());
                        comics.add(c);
                    }
                }



                //同步数据库（只增不删）
                for (int i = 0; i < comics.size(); i++) {
                    daoSession.insertOrReplace(comics.get(i));
                }
                 **/

                view.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ComicFaceHttpBean> call, Throwable t) {
                view.hideLoading();

                view.onSuccess(getComicListFromDB());

            }
        });
    }

    private ComicFaceHttpBean getComicListFromDB() {
        List<Comic> comicList = daoSession.getComicDao().loadAll();
        ComicFaceHttpBean bean = new ComicFaceHttpBean();
        bean.setComics(comicList);
        return bean;

    }

    class ComicComparator implements Comparator<Comic> {
        @Override
        public int compare(Comic o1, Comic o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
