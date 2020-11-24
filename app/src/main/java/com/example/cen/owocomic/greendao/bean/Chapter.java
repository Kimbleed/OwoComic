package com.example.cen.owocomic.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Chapter {

    String comic;

    @Unique
    String name;

    int pageCount;

    @Generated(hash = 1231593243)
    public Chapter(String comic, String name, int pageCount) {
        this.comic = comic;
        this.name = name;
        this.pageCount = pageCount;
    }

    public Chapter(String comic, String name) {
        this.comic = comic;
        this.name = name;
        this.pageCount = 0;
    }

    @Generated(hash = 393170288)
    public Chapter() {
    }

    public String getComic() {
        return this.comic;
    }

    public void setComic(String comic) {
        this.comic = comic;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }




}
