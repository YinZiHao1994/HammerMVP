package com.source.yin.yinmvpsample;

import android.util.Log;

public class LogUtil {

    public static String sTag;

    public static void init(String tag) {
        sTag = tag;
    }

    public static void logD(String msg) {
        Log.d(sTag, msg);
    }
}
