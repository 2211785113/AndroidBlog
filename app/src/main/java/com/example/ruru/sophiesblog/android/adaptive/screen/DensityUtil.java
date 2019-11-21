package com.example.ruru.sophiesblog.android.adaptive.screen;

import android.content.Context;

/**
 * 基础：dp和px的转换
 */
public class DensityUtil {
    /**
     * dp转px
     */
    public static int dip2px(Context context, float dpVal) {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpVal * density + 0.5f);
    }

    /**
     * px转dp
     */
    public static int px2dip(Context context, float pxVal) {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxVal / density + 0.5f);
    }
}
