package com.example.cen.owocomic;

import android.graphics.Bitmap;
import android.view.View;

import com.example.cen.owocomic.greendao.bean.Chapter;
import com.example.cen.owocomic.http.HttpClient;
import com.example.cen.owocomic.http.IComicApiService;
import com.example.cen.owocomic.http.bean.ComicPageBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadManager {

    private List<String> mPages = new ArrayList<>();

    private DownloadObserver mObserver;

    private static class DownloadManagerHolder {
        private static DownloadManager sInstance = new DownloadManager();
    }

    public static DownloadManager instance() {
        return DownloadManagerHolder.sInstance;
    }

    public void downloadChapter(Chapter chapter) {

        HttpClient.instance().getApiService(IComicApiService.class)
                .fetchComicPages(chapter.getComic(), chapter.getName())
                .enqueue(new Callback<ComicPageBean>() {
                    @Override
                    public void onResponse(Call<ComicPageBean> call, Response<ComicPageBean> response) {
                        mPages.addAll(response.body().pages);
                        for (String url : mPages) {
                            ImageLoader.getInstance().loadImage(url, new ImageLoadingListener() {
                                @Override
                                public void onLoadingStarted(String imageUri, View view) {

                                }

                                @Override
                                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                                }

                                @Override
                                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                    if (mObserver != null)
                                        mObserver.onProgress(chapter, 0);
                                }

                                @Override
                                public void onLoadingCancelled(String imageUri, View view) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<ComicPageBean> call, Throwable t) {

                    }
                });


    }


    public void registerObserver(DownloadObserver observer) {
        mObserver = observer;
    }

    public interface DownloadObserver {
        void onProgress(Chapter chapter, int count);

        void onCompleted();

        void onError();
    }
}
