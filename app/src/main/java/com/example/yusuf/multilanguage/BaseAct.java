package com.example.yusuf.multilanguage;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.example.util.EventBusEv;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

/**
 * Created by Yusuf on 2016/9/29.
 */

public class BaseAct extends AppCompatActivity {
    public static final String UG = "UG";
    private static final String ZH = "ZH";
    public String lan = "";
    public static final String CHANGE_LANGUAGE = "CHANGE_LANGUAGE";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        SharedPreferences sharedPreferences = getSharedPreferences("multi", MODE_PRIVATE);
        Configuration configuration = getResources().getConfiguration();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        lan = sharedPreferences.getString("lan", UG);
        if (lan.equals(ZH)) {
            configuration.locale = (Locale.CHINESE);
            getResources().updateConfiguration(configuration, displayMetrics);
        } else {
            configuration.locale = (new Locale(UG));
            getResources().updateConfiguration(configuration, displayMetrics);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setPropery(EventBusEv ev) {
        if (EventBusEv.is(ev, CHANGE_LANGUAGE)) {
            Resources resources = getResources();
            Configuration configuration = resources.getConfiguration();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            resources.updateConfiguration(configuration, displayMetrics);
            SharedPreferences sharedPreferences = getSharedPreferences("multi", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();

            if (lan.equals(ZH)) {
                configuration.locale = new Locale(UG);
                edit.putString("lan", UG);
            } else {
                configuration.locale = (Locale.CHINESE);
                edit.putString("lan", ZH);
            }
            edit.commit();
            recreate();
        }
    }
}
