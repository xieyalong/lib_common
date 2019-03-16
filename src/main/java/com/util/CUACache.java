package com.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.CAPP;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

public class CUACache {
    private CUACache(){}
    private  static  ACache instance;
    private  static  CUACache cUACache;
    public  static  CUACache getInstance(){
        if (null==instance||null==cUACache){
            cUACache=new CUACache();
            instance=ACache.get(CAPP.getInstanc().getApplicationContext());
        }
        return  cUACache;
    }
    public void put(String key, String value) {
        instance.put(key,value);
    }
    public void put(String key, String value, int saveTime) {
        instance.put(key,value,saveTime);
    }
    public void put(String key, Bitmap value) {
        instance.put(key,value);
    }
    public void put(String key, Bitmap value, int saveTime) {
        instance.put(key,value,saveTime);
    }
    public void put(String key, Drawable value) {
        instance.put(key,value);
    }
    public void put(String key, Drawable value, int saveTime) {
        instance.put(key,value,saveTime);
    }
    public void put(String key, byte[] value) {
        instance.put(key,value);
    }
    public void put(String key, byte[] value, int saveTime) {
        instance.put(key,value,saveTime);
    }

    public void put(String key, Serializable value) {
        instance.put(key,value);
    }
    public void put(String key, Serializable value, int saveTime) {
        instance.put(key,value,saveTime);
    }

    public String getAsString(String key) {
        return  instance.getAsString(key);
    }
    public byte[] getAsBinary(String key) {
        return  instance.getAsBinary(key);
    }
    public Bitmap getAsBitmap(String key) {
        return  instance.getAsBitmap(key);
    }
    public Drawable getAsDrawable(String key) {
        return  instance.getAsDrawable(key);
    }
    public Object getAsObject(String key) {
        return  instance.getAsObject(key);
    }
    public InputStream get(String key) throws FileNotFoundException {
        return  instance.get(key);
    }

    public boolean remove(String key) {
        return instance.remove(key);
    }
    public void clear() {
        instance.clear();
    }

}
