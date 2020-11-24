package com.example.cen.owocomic.page;

import android.content.Context;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cen.owocomic.R;
import com.example.cen.owocomic.http.HttpUrl;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ComicPagerAdapter extends PagerAdapter {

    private Context mContext;

    private List<String> data;

    DisplayImageOptions displayImageOptions= new DisplayImageOptions.Builder().cacheOnDisk(true).build();
    public ComicPagerAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        Log.i("CenDebug","ComicPageAdapter"+data.size());
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_comic_page,container,false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_comic_page,null);
        ImageView imageView = view.findViewById(R.id.iv_page);
        ((TextView)view.findViewById(R.id.tv_page)).setText(position+"");
        Log.i("instan",HttpUrl.baseUrl+"/"+data.get(position));
        ImageLoader.getInstance().displayImage(HttpUrl.baseUrl+"/"+data.get(position),imageView,displayImageOptions);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
