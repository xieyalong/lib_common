package com.util;

public class CUIO {
    private CUIO(){}
    private  static CUIO intance;
    public  static CUIO getInstance(){
        if (null==intance){
            intance=new CUIO();
        }
        return  intance;
    }
}
