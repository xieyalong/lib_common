package com;

import android.app.Application;
import android.content.Intent;
import android.os.StrictMode;

import com.apkfuns.logutils.LogUtils;
import com.msg.CUMsg;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.util.CUAPP;
import com.util.CUActivity;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePal;

import java.util.concurrent.TimeUnit;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
import okhttp3.OkHttpClient;

public class CAPP {

    /**
     * 正式发布时时记得把这里改成false，
     * true时接口连测试地址，数据不加密
     * false时接口连正式地址，数据加密
     */
    private boolean isDebug = false;


    private static Application app;
    private static CAPP commonAPP;

    private CAPP() {
    }

    public static Application getInstanc() {
        return app;
    }

    public static CAPP getCommonAPP() {
        if (null == commonAPP) {
            commonAPP = new CAPP();
        }
        return commonAPP;
    }

    public CAPP init(Application app) {
        this.app = app;
        commonAPP = new CAPP();
        CUMsg.init(app);
        cameraAction();
        CUActivity.getIntanc().registerActivity(app);
        initHttp();
        initFragment();
        initLogUtils();
        LitePal.initialize(app);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(app.getApplicationContext()));
        return commonAPP;
    }


    public boolean isDebug() {
        return isDebug;
    }

    /**
     * 解决com.github.lovetuzitong:MultiImageSelector:1.2报错问题
     */
    public void cameraAction() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    public Application getApp() {
        return app;
    }

    public void startActivity(Intent intent) {
        if (CUAPP.getInstance().getSystemSdkVersion() < 21) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        app.startActivity(intent);
    }

    public void initHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public void initFragment() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.NONE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    public void initLogUtils() {
        LogUtils.getLogConfig().configShowBorders(false).configAllowLog(isDebug());
    }
}
