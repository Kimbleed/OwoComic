package com.example.cen.owocomic.http.bean;

import com.example.cen.owocomic.greendao.bean.Chapter;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

public class ComicChapterHttpBean {
    public String faceUrl;
    public List<String> chapters;
}
