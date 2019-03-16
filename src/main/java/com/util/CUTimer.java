package com.util;

/**
 * 定时器
 */
public class CUTimer {
//    private static   CommonUtilTimer instance;
//    public  static CommonUtilTimer  getInstance(){
//        if (instance==null){
//            instance=new CommonUtilTimer();
//        }
//        return  instance;
//    }
//    Handler handler;
//    Runnable runnable;
//    int size=0;
//    //视频开始和时间到结束
//    public  void start(final  int seconds,final TextView tv_timer){
//        size=seconds;
//        if (null==handler){
//            handler=new Handler(Looper.myLooper()){
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                    size--;
//                    System.out.println(">] size="+size);
//                    if (0==size){
//                        System.out.println(">] 时间到");
//                        CommonUtilTimer.getInstance().stop();
//                    }else{
//                      start(size,tv_timer);
//                    }
//                }
//            };
//        }
//        if (null==runnable){
//            runnable=new Runnable() {
//                @Override
//                public void run() {
//                    handler.sendEmptyMessage(0);
//                }
//            };
//        }
//        handler.postDelayed(runnable,1000);
//    }
//    public  void stop(){
//        if (null!=handler&&null!=runnable){
//            handler.removeCallbacks(runnable);
//        }
//    }

}
