package com.example.cen.owocomic.http.bean;

import com.example.cen.owocomic.greendao.bean.Comic;

import java.util.List;

public class ComicFaceHttpBean {
    private List<Comic> comics;

    public List<Comic> getComics() {
        return comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }
}
