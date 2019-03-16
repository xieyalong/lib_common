package com.bean;

import com.contrarywind.interfaces.IPickerViewData;

public class KVBean extends BaseBean   implements IPickerViewData {
    public KVBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KVBean() {
    }

    private  String key;
    private  String value;
    private  String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    //这个用来显示在PickerView上面的字符串,PickerView会通过getPickerViewText方法获取字符串显示出来。
    @Override
    public String getPickerViewText() {
        return value;
    }
}
