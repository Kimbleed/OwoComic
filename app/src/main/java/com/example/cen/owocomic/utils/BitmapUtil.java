package com.example.cen.owocomic.utils;

import android.graphics.Bitmap;
import android.util.Log;

public class BitmapUtil {

    public static final int CUT_FIT_START = 0;
    public static final int CUT_FIT_CENTER = 1;
    public static final int CUT_FIT_END = 2;

    public enum CUT_FIT{
        START,CENTER,END
    }

    /**
     * 按比例裁剪
     * @param source
     * @param targetScaleWH 目标宽高比
     * @param fitType 裁剪的图像位置：起始、居中、末尾，根据裁剪的方向
     * @return
     */
    public static Bitmap cut(Bitmap source, float targetScaleWH, CUT_FIT fitType) {
        float sHeight = (float)source.getHeight();
        float sWidth = (float)source.getWidth();

        float cutRest = 0;
        float realScaleWH = ((float) sWidth / sHeight);
        boolean cutWidth = true;

        //实际宽高比  大于  目标宽高比 ，裁宽
        if (realScaleWH > targetScaleWH) {
            Log.i("BitmapUtil","裁宽");
            cutWidth = true;
            cutRest = targetScaleWH * sHeight;
        }
        //实际宽高比  小于 目标宽高比 裁高
        else {
            Log.i("BitmapUtil","裁高");
            cutWidth = false;
            cutRest = sWidth/targetScaleWH;
        }

        //裁宽
        if(cutWidth){
            int offset = adaptCutTypeOffset(fitType,(int)sWidth,(int)cutRest );
            return Bitmap.createBitmap(source,offset,0,(int)cutRest,(int)sHeight);
        }
        //裁高
        else{
            int offset = adaptCutTypeOffset(fitType,(int)sHeight,(int)cutRest );
            return Bitmap.createBitmap(source,0,offset,(int)sWidth,(int)cutRest);
        }

    }

    private static int adaptCutTypeOffset(CUT_FIT fitType,int source,int target){
        int offset = 0;
        if(fitType == CUT_FIT.CENTER) {
            offset =  (source - target)/ 2;
        }
        else if(fitType == CUT_FIT.START){
            offset = 0;
        }
        else if(fitType == CUT_FIT.END){
            offset = source - target;
        }
        return offset;
    }
}
