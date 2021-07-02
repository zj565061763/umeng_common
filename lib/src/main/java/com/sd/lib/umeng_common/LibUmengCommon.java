package com.sd.lib.umeng_common;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class LibUmengCommon {
    private LibUmengCommon() {
    }

    /**
     * 预初始化
     */
    public static void preInit(Context context) {
        final String appKey = context.getResources().getString(R.string.lib_umeng_common_app_key);
        final String channel = context.getResources().getString(R.string.lib_umeng_common_channel);
        UMConfigure.preInit(context, appKey, channel);
    }

    /**
     * 初始化
     */
    public static void init(Context context) {
        final String appKey = context.getResources().getString(R.string.lib_umeng_common_app_key);
        final String channel = context.getResources().getString(R.string.lib_umeng_common_channel);
        final String pushMessageSecret = context.getResources().getString(R.string.lib_umeng_common_push_mssage_secret);

        UMConfigure.init(context,
                appKey,
                channel,
                UMConfigure.DEVICE_TYPE_PHONE,
                pushMessageSecret);

        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }
}
