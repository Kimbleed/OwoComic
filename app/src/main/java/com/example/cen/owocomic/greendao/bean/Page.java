package com.example.cen.owocomic.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Page {


    String comic;


    String chapter;

    @Unique
    String url;


    int position;


    @Generated(hash = 1810016916)
    public Page(String comic, String chapter, String url, int position) {
        this.comic = comic;
        this.chapter = chapter;
        this.url = url;
        this.position = position;
    }


    @Generated(hash = 1618436667)
    public Page() {
    }


    public String getComic() {
        return this.comic;
    }


    public void setComic(String comic) {
        this.comic = comic;
    }


    public String getChapter() {
        return this.chapter;
    }


    public void setChapter(String chapter) {
        this.chapter = chapter;
    }


    public String getUrl() {
        return this.url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public int getPosition() {
        return this.position;
    }


    public void setPosition(int position) {
        this.position = position;
    }


}