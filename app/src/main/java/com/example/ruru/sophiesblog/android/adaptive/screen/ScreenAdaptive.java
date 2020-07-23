package com.example.ruru.sophiesblog.android.adaptive.screen;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class ScreenAdaptive extends AppCompatActivity {

    private static Float sNoncompatDensity;
    private static Float sNoncompatScaledDensity;

    private static int targetDensity;
    private static int targetDensityDpi;
    private static float targetScaledDensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_adaptive);

        float density = getResources().getDisplayMetrics().density;
        Log.d(getClass().getName(), "onCreate:res= " + density);//3.0   //2.625

        setCustomDensity(this, null);
    }

    /**
     * 设计图屏幕宽度是360dp，以宽维度来适配。
     * setCustomDensity方法的目的是：固定density的值。
     */
    private static void setCustomDensity(Activity activity, Application application) {
        DisplayMetrics displayMetrics = null;

        /* application */
        if (application != null) {
            displayMetrics = application.getResources().getDisplayMetrics();
            sNoncompatDensity = displayMetrics.density;
            sNoncompatScaledDensity = displayMetrics.scaledDensity;

            //监听系统字体的变化
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });

            targetDensity = displayMetrics.widthPixels / 360;
            targetDensityDpi = targetDensity * 160;
            targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        }

        /* activity */
        if (activity != null) {
            displayMetrics = activity.getResources().getDisplayMetrics();

            targetDensity = displayMetrics.widthPixels / 360;
            targetDensityDpi = targetDensity * 160;
            targetScaledDensity = targetDensity * (displayMetrics.scaledDensity / displayMetrics.density);
        }

        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetScaledDensity;
        displayMetrics.densityDpi = targetDensityDpi;
    }
}
