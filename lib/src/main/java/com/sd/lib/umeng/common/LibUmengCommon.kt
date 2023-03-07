package com.sd.lib.umeng.common

import android.annotation.SuppressLint
import android.content.Context
import com.umeng.commonsdk.UMConfigure

@SuppressLint("StaticFieldLeak")
object LibUmengCommon {
    private var _context: Context? = null
    private var _appKey = ""
    private var _channel = ""

    /**
     * 预初始化
     */
    @JvmOverloads
    fun preInit(
        context: Context,
        appKey: String,
        channel: String = "umeng",
    ) {
        require(appKey.isNotEmpty()) { "appKey is empty" }
        require(channel.isNotEmpty()) { "channel is empty" }
        _context = context.applicationContext
        _appKey = appKey
        _channel = channel
        UMConfigure.preInit(context, appKey, channel)
    }

    /**
     * 初始化
     *
     * @param pushMessageSecret 推送MessageSecret
     */
    @JvmOverloads
    fun init(
        pushMessageSecret: String = "",
        type: Int = UMConfigure.DEVICE_TYPE_PHONE,
    ) {
        val context = _context ?: error("You should call preInit before this")

        val appKey = _appKey
        check(appKey.isNotEmpty()) { "You should call preInit before this" }

        val channel = _channel
        check(channel.isNotEmpty()) { "You should call preInit before this" }

        UMConfigure.init(context, appKey, channel, type, pushMessageSecret)
    }
}