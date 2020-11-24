package com.example.cen.owocomic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

public class ScreenUtil {

    /**
     * 设置Windows顶部状态栏颜色
     * @param context
     * @param window
     * @param color
     */
    public static void  setTopBarColor(Context context, Window window, int color){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
        else{
            //设置透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //取消透明状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 将布局设置延展至状态栏
     * //xml配置：values-v21/styles.xml中主题增加以下内容
     <style name="fullScreenTheme" parent="Theme.AppCompat.Light.DarkActionBar">
     <item name="android:windowTranslucentStatus">false</item>
     <item name="android:windowTranslucentNavigation">true</item>
     <item name="android:statusBarColor">@android:color/transparent</item>
     </style>
     */
    public static void setLayoutTopToBar(Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //代码配置
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

            );
        }
        else{
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    /**
     * 获取屏幕宽度
     * @param activity
     * @return
     */
    public static int getWindowWidth(Activity activity){
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度
     * @param activity
     * @return
     */
    public static int getWindowHeight(Activity activity){
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }

}
