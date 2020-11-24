package com.example.cen.owocomic;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.cen.owocomic.greendao.gen.DaoMaster;
import com.example.cen.owocomic.greendao.gen.DaoSession;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.greenrobot.greendao.AbstractDaoMaster;

import java.io.File;

import okhttp3.internal.cache.DiskLruCache;

public class OwoApplication extends Application {

    public static boolean sEverLaunch = false;
    public static Context CTX;


    @Override
    public void onCreate() {
        super.onCreate();
        ;
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "/comics");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
//                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3) // 线程池数量
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiskCache(cacheDir)) // 内存卡缓存
                .diskCacheSize(500 * 1024 * 1024)        // 内存卡缓存大小
                .diskCacheFileCount(1000)        // 缓存文件数量
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                //.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .defaultDisplayImageOptions(createDisplayOption()) // default
                .writeDebugLogs().build();


        ImageLoader.getInstance().init(config);
        initGreenDao();
        CTX = getApplicationContext();
    }

    private DisplayImageOptions createDisplayOption() {
        // DON'T COPY THIS CODE TO YOUR PROJECT! This is just example of ALL options using.
// See the sample project how to use ImageLoader correctly.
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher_background) // resource or drawable
                .showImageForEmptyUri(R.drawable.ic_launcher_background) // resource or drawable
                .showImageOnFail(R.drawable.ic_launcher_background) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(true) // 是否开启内存缓存
                .cacheOnDisk(true) // 是否开启SD卡缓存
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                //.displayer(new RoundedBitmapDisplayer(40)) // default
                //.displayer(new CircleBitmapDisplayer()) // default
                .build();
        return options;
    }


    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aserbao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
