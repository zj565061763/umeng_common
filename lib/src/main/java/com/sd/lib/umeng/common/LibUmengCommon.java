package com.sd.lib.umeng.common;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class LibUmengCommon {
    private static String sAppKey = "";
    private static String sChannel = "";

    private LibUmengCommon() {
    }

    /**
     * {@link #preInit(Context, String, String)}
     */
    public static void preInit(Context context, String appKey) {
        preInit(context, appKey, "umeng");
    }

    /**
     * 预初始化
     */
    public static void preInit(Context context, String appKey, String channel) {
        if (TextUtils.isEmpty(appKey)) {
            throw new IllegalArgumentException("appKey is empty");
        }
        if (TextUtils.isEmpty(channel)) {
            throw new IllegalArgumentException("channel is empty");
        }

        sAppKey = appKey;
        sChannel = channel;

        UMConfigure.preInit(context, appKey, channel);
    }

    /**
     * {@link #init(Context, String, int)}
     */
    public static void init(Context context, String pushMessageSecret) {
        init(context, pushMessageSecret, UMConfigure.DEVICE_TYPE_PHONE);
    }

    /**
     * 初始化
     *
     * @param pushMessageSecret 推送MessageSecret
     * @param type              {@link UMConfigure#DEVICE_TYPE_PHONE}
     */
    public static void init(Context context, String pushMessageSecret, int type) {
        final String appKey = sAppKey;
        if (TextUtils.isEmpty(appKey)) {
            throw new IllegalStateException("You should call preInit before this");
        }

        final String channel = sChannel;
        if (TextUtils.isEmpty(channel)) {
            throw new IllegalStateException("You should call preInit before this");
        }

        UMConfigure.init(context, appKey, channel, type, pushMessageSecret);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }
}
