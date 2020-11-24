package com.example.cen.owocomic.mvp.base;

public abstract class MvpPresenter <T>{
    protected T view;
    protected boolean mReleased;

    public MvpPresenter(T t){
        this.view = t;

    }

    public void detachView(){

    }

    public boolean isViewAttached(){
        return !mReleased;
    }

    public boolean isViewDetached(){
        return mReleased;
    }

    public T getView(){
        return this.view;
    }

}
