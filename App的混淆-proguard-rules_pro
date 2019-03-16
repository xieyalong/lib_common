# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#解除警告
#-keepattributes EnclosingMethod
#-keepattributes InnerClasses


#fafstjson或者logutils
-keepattributes Signature
-keepattributes *Annotation*

##okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}



##okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}



##okio
-dontwarn okio.**
-keep class okio.**{*;}


#7牛
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}
-ignorewarnings

##glide:glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

##com.github.CymChad:BaseRecyclerViewAdapterHelper
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}

#----------轮播图start--------------------------------
# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }
 #----------轮播图end--------------------------------
#----------极光start--------------------------------
# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontnote
-verbose

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.app.IntentService
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
#----------极光end--------------------------------

#----------云信start--------------------------------
#网易视频im
-dontwarn com.netease.**
-keep class com.netease.** {*;}
#如果你使用全文检索插件，需要加入
-dontwarn org.apache.lucene.**
-keep class org.apache.lucene.** {*;}
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify


# The remainder of this file is identical to the non-optimized version
# of the Proguard configuration file (except that the other file has
# flags to turn off optimization).
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
### Android support
-dontwarn org.apache.http.**
-dontwarn android.support.**
-keep class android.support.** {*;}
-keep class android.webkit.** {*;}

### keep options, system default, from android example
-keep public class * extends android.app.Activity
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

-keepattributes *Annotation*,InnerClasses
#-keepattributes SourceFile,LineNumberTable

### APP 3rd party jars
-dontwarn com.amap.**
-keep class com.amap.** {*;}
-dontwarn com.baidu.location.**
-keep class com.baidu.location.** {*;}
-dontwarn com.autonavi.amap.**
-keep class com.autonavi.amap.** {*;}
-dontwarn com.alibaba.**
-keep class com.alibaba.fastjson.** {*;}

### APP 3rd party jars(xiaomi push)
-dontwarn com.xiaomi.push.**
-keep class com.xiaomi.** {*;}
### APP 3rd party jars(huawei push)

-ignorewarning
-keepattributes Exceptions
-keepattributes Signature
# hmscore-support: remote transport
-keep class * extends com.huawei.hms.core.aidl.IMessageEntity { *; }
# hmscore-support: remote transport
-keepclasseswithmembers class * implements com.huawei.hms.support.api.transport.DatagramTransport {
<init>(...); }
# manifest: provider for updates
-keep public class com.huawei.hms.update.provider.UpdateProvider { public *; protected *; }

### APP 3rd party jars(meizu push)
-dontwarn com.meizu.cloud.**
-keep class com.meizu.cloud.** {*;}

# fcm
-dontwarn com.google.**
-keep class com.google.** {*;}

### APP 3rd party jars(glide)
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

### org.json xml
-dontwarn org.json.**
-keep class org.json.**{*;}

### face unity
-keep class com.faceunity.auth.AuthPack {*;}

### jrmf wx
-dontwarn com.jrmf360.**
-keep class com.jrmf360.** {*;}
#微信
-dontwarn com.switfpass.pay.**
-keep class com.switfpass.pay.**{*;}
-dontwarn com.tencent.mm.**
-keep class com.tencent.mm.**{*;}
#支付宝
-dontwarn com.alipay.**
-keep class com.alipay.**{*;}
-dontwarn org.json.alipay.**
-keep class org.json.alipay.**{*;}
#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
#okio
-dontwarn okio.**
-keep class okio.**{*;}

### glide 3
-keepnames class com.netease.nim.uikit.support.glide.NIMGlideModule

### glide 4
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

### fabric
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

### NIM SDK
-dontwarn com.netease.**
-keep class com.netease.** {*;}
-dontwarn org.apache.lucene.**
-keep class org.apache.lucene.** {*;}
-keep class net.sqlcipher.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep class **.R$* {
 *;

}
#----------云信end--------------------------------
#----------gsy视频start--------------------------------
-keep class com.shuyu.gsyvideoplayer.video.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.**
-keep class com.shuyu.gsyvideoplayer.video.base.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.base.**
-keep class com.shuyu.gsyvideoplayer.utils.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.utils.**
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#----------gsy视频end--------------------------------

#----------shartSDK分享start--------------------------------
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-keep class m.framework.**{*;}
-dontwarn cn.sharesdk.**
-dontwarn com.sina.**
-dontwarn com.mob.**
-dontwarn **.R$*
#----------分享end--------------------------------
#----------微信分享start--------------------------------
-keep class com.tencent.mm.opensdk.** {*;}
-keep class com.tencent.wxop.** {*;}
-keep class com.tencent.mm.sdk.** {*;}
#----------微信分享end--------------------------------
#----------gson staet--------------------------------
-keep class com.google.**{*;}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class com.widget.picker.bean.** { *; }
#----------gson end--------------------------------
#----------fastjson staet--------------------------------
#-keepattributes *Annotation*
-keepattributes Signature
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }
-keep class com.bean.**{*; }
-keep class com.http.bean.**{*; }
-keep class com.widget.picker.bean.**{*; }

-keep class com.yuanxinkangfu.omo2c.bean.**{*; }
-keep class com.yuanxinkangfu.omo2c.discover.bean.**{*; }
-keep class com.yuanxinkangfu.omo2c.exercise.bean.**{*; }
-keep class com.yuanxinkangfu.omo2c.ui.bean.**{*; }
-keep class com.yuanxinkangfu.omo2c.my.bean.**{*; }
-keep class com.yuanxinkangfu.omo2c.widget.**{*; }
#----------fastjson end--------------------------------
#----------eventbus start--------------------------------
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
#----------eventbus end--------------------------------
