package com.util;

import com.CAPP;
import com.lib_common.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class UCImageLoad {
    private UCImageLoad(){}
    private  static UCImageLoad intance;
    public  static UCImageLoad getInstance(){
        if (null==intance){
            intance=new UCImageLoad();
        }
        return  intance;
    }
    DisplayImageOptions options;
    public DisplayImageOptions getOptions(){
        try {
            if(null==options){
                options = new DisplayImageOptions.Builder()
                        .showImageOnLoading(R.mipmap.empty_img)//加载的时候显示的图片
                        .showImageForEmptyUri(R.mipmap.empty_img)//路径空的时候显示的图片
                        .showImageOnFail(R.mipmap.empty_img)//失败的时候显示的图片
                        .cacheInMemory(true)
                        .cacheOnDisk(true)//缓存
                        .considerExifParams(true)
                        // .displayer(new RoundedBitmapDisplayer(20))//图片样式100：圆形，20圆角
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return options;
    }
}
