package com.example.cen.owocomic.http;

import com.example.cen.owocomic.http.bean.ComicChapterHttpBean;
import com.example.cen.owocomic.http.bean.ComicFaceHttpBean;
import com.example.cen.owocomic.http.bean.ComicPageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IComicApiService {
    @GET("get_comic_list_new")
    Call<ComicFaceHttpBean> fetchComicList();

    @GET("get_comic_detail")
    Call<ComicChapterHttpBean> fetchComicChapters(@Query("comicName") String comicName);

    @GET("get_chapter_detail")
    Call<ComicPageBean> fetchComicPages(@Query("comicName") String comicName, @Query("chapter") String chapter);
}
