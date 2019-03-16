package com.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.lib_common.R;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class CUGlide {
    RequestOptions options; //占位图
    private CUGlide(){}
    private  static CUGlide intance;
    public  static CUGlide getInstance(){
        if (null==intance){
            intance=new CUGlide();
        }
        return  intance;
    }
    public  void  load(String url, ImageView img){
        if (null==options){
            options = new RequestOptions();
        }
        options.centerCrop()
                .placeholder(R.mipmap.position_img_min)
                .error(R.mipmap.position_img_min)
                .fallback(R.mipmap.position_img_min);
        Glide.with(CUActivity.getIntanc().getActivity()).load(url).apply(options).into(img);
    }
    public  void  loadFitCenter(String url, ImageView img,int defult){
        if (null==options){
            options = new RequestOptions();
        }
        if (0==defult){
            defult=R.mipmap.position_img_min;
        }
        options.fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(defult)
                .error(defult)
                .fallback(defult);
        Glide.with(CUActivity.getIntanc().getActivity()).load(url).apply(options).into(img);
    }
    public  void  loadCenterCrop(String url, ImageView img,int defult){
        if (null==options){
            options = new RequestOptions();
        }
        if (0==defult){
            defult=R.mipmap.position_img_min;
        }
        options.centerCrop()
                .placeholder(defult)
                .error(defult)
                .fallback(defult);
        Glide.with(CUActivity.getIntanc().getActivity()).load(url).apply(options).into(img);
    }
    public  void  loadImg(String url, ImageView img){
        Glide.with(CUActivity.getIntanc().getActivity()).load(url).into(img);
    }

    public String getImgCache(String url) {
        String path= null;
        try {
            FutureTarget<File> future=Glide.with(CUActivity.getIntanc().getActivity())
                    .load(url)
                    .downloadOnly(CUScreen.getInstance().getScreenWidth(),CUScreen.getInstance().getScreenHeight());
            File file=future.get();
            path = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  path;
    }
}











