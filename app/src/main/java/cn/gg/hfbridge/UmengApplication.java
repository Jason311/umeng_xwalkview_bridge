package cn.gg.hfbridge;

import android.app.Application;
import com.umeng.commonsdk.UMConfigure;

/**
 * Copyright © 2017 Umeng Inc. All rights reserved.
 * Description: TODO
 * Version: 1.0
 * Create:  17/4/25 16:34
 * Author: safei
 */
public class UmengApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //准实时发送
        //UMConfigure.init(this, "5ab24b918f4a9d18360002f8", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);

        //启动发送
        UMConfigure.init(this, "585f93dcf29d984aa60013f1", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        //间隔发送
        //UMConfigure.init(this, "57455f31e0f55a1d2900074b", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        //if (com.squareup.leakcanary.LeakCanary.isInAnalyzerProcess(this)) {
        //    // This process is dedicated to LeakCanary for heap analysis.
        //    // You should not init your app in this process.
        //    return;
        //}
        //系能测试
        //com.squareup.leakcanary.LeakCanary.install(this);
        UMConfigure.setProcessEvent(true);//支持多进程 打点.默认不支持
    }
}
