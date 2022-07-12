package com.sd.lib.umeng_common

import android.content.Context
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

object LibUmengCommon {
    private var _appKey = ""
    private var _channel = ""

    /**
     * 预初始化
     */
    fun preInit(
        context: Context,
        appKey: String,
        channel: String = "umeng",
    ) {
        require(appKey.isNotEmpty()) { "appKey is empty" }
        require(channel.isNotEmpty()) { "channel is empty" }
        _appKey = appKey
        _channel = channel
        UMConfigure.preInit(context, appKey, channel)
    }

    /**
     * 初始化
     */
    fun init(
        context: Context,
        pushMessageSecret: String,
        type: Int = UMConfigure.DEVICE_TYPE_PHONE,
    ) {
        require(pushMessageSecret.isNotEmpty()) { "pushMessageSecret is empty" }
        check(_appKey.isNotEmpty()) { "You should call preInit before this" }
        check(_channel.isNotEmpty()) { "You should call preInit before this" }
        UMConfigure.init(context,
            _appKey,
            _channel,
            type,
            pushMessageSecret)
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }
}