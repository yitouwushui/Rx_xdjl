#---------------------------------1.实体类---------------------------------
-keep class cn.ecar.insurance.dao.bean.** { *; }
-keep class cn.ecar.insurance.dao.gson.** { *; }
-keep class cn.ecar.insurance.dao.base.** { *; }


#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule

-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.** { *; }

-keepnames class cn.ecar.insurance.config.GlideApplyModule

#zxing
-keep  class com.google.zxing.**{*;}

#retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-dontwarn org.robovm.**
-keep class org.robovm.** { *; }
-dontwarn okio.**
-dontwarn javax.annotation.**

#okhttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-keep class okhttp3.** { *;}
-keep class okio.** { *;}
-dontwarn sun.security.**
-keep class sun.security.** { *;}
-dontwarn okio.**
-dontwarn okhttp3.**

#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

#rxjava
-dontwarn rx.**
-keep class rx.** { *; }

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}


#gson
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.** {
    <fields>;
    <methods>;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-dontwarn com.google.gson.**


-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#pinyin4j
-dontwarn net.soureceforge.pinyin4j.**
-dontwarn demo.**
#-libraryjars libs/pinyin4j-2.5.0.jar
-keep class net.sourceforge.pinyin4j.** { *;}
-keep class demo.** { *;}
-keep class com.hp.** { *;}

#httpclient (org.apache.http.legacy.jar)
-dontwarn android.net.compatibility.**
-dontwarn android.net.http.**
-dontwarn com.android.internal.http.multipart.**
-dontwarn org.apache.commons.**
-dontwarn org.apache.http.**
-dontwarn org.apache.http.protocol.**
-keep class android.net.compatibility.**{*;}
-keep class android.net.http.**{*;}
-keep class com.android.internal.http.multipart.**{*;}
-keep class org.apache.commons.**{*;}
-keep class org.apache.org.**{*;}
-keep class org.apache.harmony.**{*;}

#LRecyclerview_library
-dontwarn com.github.jdsjlzx.**
-keep class com.github.jdsjlzx.**{*;}

#rxlifecycle
-dontwarn com.trello.rxlifecycle.**
-keep class com.trello.rxlifecycle.**{*;}

#rxbinding
-dontwarn com.jakewharton.rxbinding.**
-keep class com.jakewharton.rxbinding.**{*;}

#rxpermissions
-dontwarn com.tbruyelle.rxpermissions.**
-keep class com.tbruyelle.rxpermissions.**{*;}

#convertgson
-dontwarn retrofit.converter.**
-keep class retrofit.converter.**{*;}

#adapter-rxjava
-dontwarn retrofit.adapter.**
-keep class retrofit.adapter.**{*;}

#circleimageview
-dontwarn de.hdodenhof.circleimageview.**
-keep class de.hdodenhof.circleimageview.**{*;}

#base64decoder
-dontwarn Decoder.**
-keep class Decoder.**{*;}

#verticaltablayout
-dontwarn q.rorbin.verticaltablayout.**
-keep class q.rorbin.verticaltablayout.**{*;}

#materialdialog
-dontwarn me.drakeet.materialdialog.**
-keep class me.drakeet.materialdialog.**{*;}

#shortcutbadge
-dontwarn me.leolin.shortcutbadger.**
-keep class me.leolin.shortcutbadger.**{*;}

#dialogplugs
-dontwarn com.orhanobut.dialogplus.**
-keep class com.orhanobut.dialogplus.**{*;}

#lambda
-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*

#litepal
-dontwarn org.litepal.**
-keep class org.litepal.**{*;}
-keep class * extends org.litepal.crud.DataSupport { *;}

#walle
-dontwarn com.meituan.android.walle.**
-keep class com.meituan.android.walle.**{*;}

#arch mvvm
-dontwarn android.arch.**
-keep class android.arch.** { *; }

#logger
-dontwarn com.orhanobut.logger.**
-keep class com.orhanobut.logger.**{*;}

#百度
-dontwarn com.baidu.**
-keep class com.baidu.**{*;}

#个推
-dontwarn com.igexin.**
-keep class com.igexin.**{*;}

#环信
-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**

#x5内核
-dontwarn com.tencent.**
-keep class com.tencent.**{*;}

#umeng
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**

-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**

-keep class com.facebook.**
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**

-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}

-keep public class cn.ecar.insurance.R$*{
    public static final int *;
}

#友盟统计
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep public class cn.ecar.insurance.R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#友盟分享
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}


-keep class com.facebook.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.mm.opensdk.** {
   *;
}
-keep class com.tencent.wxop.** {
   *;
}
-keep class com.tencent.mm.sdk.** {
   *;
}
-dontwarn twitter4j.**
-keep class twitter4j.** { *; }

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep class com.kakao.** {*;}
-dontwarn com.kakao.**
-keep public class com.umeng.com.umeng.soexample.R$*{
    public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
-keepattributes Signature


#平安i贷
# com.paem
-dontwarn com.paem.**
-keep class com.paem.**
-keepclassmembers class com.paem.** { *; }
-keep enum com.paem.**
-keepclassmembers enum com.paem.** { *; }
-keep interface  com.paem.**
-keepclassmembers interface  com.paem.** { *; }
# com.paem

# com.pingan
-dontwarn com.pingan.**
-keep class com.pingan.**
-keepclassmembers class com.pingan.** { *; }
-keep enum com.pingan.**
-keepclassmembers enum com.pingan.** { *; }
-keep interface  com.pingan.**
-keepclassmembers interface  com.pingan.** { *; }
# com.pingan

# cn.fraudmetrix.android.sdk.**
-dontwarn cn.fraudmetrix.android.sdk.**
-keep class cn.fraudmetrix.android.sdk.**
-keepclassmembers class cn.fraudmetrix.android.sdk.** { *; }
-keep enum cn.fraudmetrix.android.sdk.**
-keepclassmembers enum cn.fraudmetrix.android.sdk.** { *; }
-keep interface  cn.fraudmetrix.android.sdk.**
-keepclassmembers interface  cn.fraudmetrix.android.sdk.** { *; }
# cn.fraudmetrix.android.sdk.**

# com.tendcloud.tenddata.kpl.**
-dontwarn com.tendcloud.tenddata.kpl.**
-keep class com.tendcloud.tenddata.kpl.**
-keepclassmembers class com.tendcloud.tenddata.kpl.** { *; }
-keep enum com.tendcloud.tenddata.kpl.**
-keepclassmembers enum com.tendcloud.tenddata.kpl.** { *; }
-keep interface  com.tendcloud.tenddata.kpl.**
-keepclassmembers interface  com.tendcloud.tenddata.kpl.** { *; }
# com.tendcloud.tenddata.kpl.**

# net.sqlcipher.**
-dontwarn net.sqlcipher.**
-keep class net.sqlcipher.**
-keepclassmembers class net.sqlcipher.** { *; }
-keep enum net.sqlcipher.**
-keepclassmembers enum net.sqlcipher.** { *; }
-keep interface  net.sqlcipher.**
-keepclassmembers interface  net.sqlcipher.** { *; }
# net.sqlcipher.**

# com.jcraft.jzlib.**
-dontwarn com.jcraft.jzlib.**
-keep class com.jcraft.jzlib.**
-keepclassmembers class com.jcraft.jzlib.** { *; }
-keep enum com.jcraft.jzlib.**
-keepclassmembers enum com.jcraft.jzlib.** { *; }
-keep interface  com.jcraft.jzlib.**
-keepclassmembers interface  com.jcraft.jzlib.** { *; }
# com.jcraft.jzlib.**

# org.apache.commons.codec.**
-dontwarn org.apache.commons.codec.**
-keep class org.apache.commons.codec.**
-keepclassmembers class org.apache.commons.codec.** { *; }
-keep enum org.apache.commons.codec.**
-keepclassmembers enum org.apache.commons.codec.** { *; }
-keep interface  org.apache.commons.codec.**
-keepclassmembers interface  org.apache.commons.codec.** { *; }
# org.apache.commons.codec.**

# Decoder.**
-dontwarn Decoder.**
-keep class Decoder.**
-keepclassmembers class Decoder.** { *; }
-keep enum Decoder.**
-keepclassmembers enum Decoder.** { *; }
-keep interface Decoder.**
-keepclassmembers interface  Decoder.** { *; }
# Decoder.**

# org.apache.http
-dontwarn org.apache.http.**
-keep class org.apache.http.**
-keepclassmembers class org.apache.http.** { *; }
-keep enum org.apache.http.**
-keepclassmembers enum org.apache.http.** { *; }
-keep interface org.apache.http.**
-keepclassmembers interface  org.apache.http.** { *; }
# org.apache.http.**




#-------------------------------------------------------------------------

#---------------------------------3.与js互相调用的类------------------------



#-------------------------------------------------------------------------

#---------------------------------4.反射相关的类和方法-----------------------



#----------------------------------------------------------------------------

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontskipnonpubliclibraryclassmembers
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#-ignorewarnings
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
-keep public class * extends android.os.IInterface

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
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
-keepclassmembers class * {
    void *(**On*Event);
}

-keepclasseswithmembernames class * { # 保持 native 方法不被混淆
 native <methods>;
}

-keepclasseswithmembers class * { # 保持自定义控件类不被混淆
 public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
 public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
 public void *(android.view.View);
}

-keepclassmembers enum * { # 保持枚举 enum 类不被混淆
 public static **[] values();
 public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
 public static final android.os.Parcelable$Creator *;
}

#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt

#------------------  下方是共性的排除项目         ----------------
# 方法名中含有“JNI”字符的，认定是Java Native Interface方法，自动排除
# 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}

















