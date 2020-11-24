package com.example.cen.owocomic.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Comic {

    @Unique
    String name;

    String faceUrl;

    int chapterCount;

    @Generated(hash = 1999445644)
    public Comic(String name, String faceUrl, int chapterCount) {
        this.name = name;
        this.faceUrl = faceUrl;
        this.chapterCount = chapterCount;
    }

    @Generated(hash = 1347984162)
    public Comic() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaceUrl() {
        return this.faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public int getChapterCount() {
        return this.chapterCount;
    }

    public void setChapterCount(int chapterCount) {
        this.chapterCount = chapterCount;
    }




}
