package com.kidd.projectbase.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.kidd.projectbase.network.RetrofitClient;
import com.kidd.projectbase.utils.rx.AppRxSchedulers;
import com.kidd.projectbase.utils.rx.RxSchedulers;

import androidx.annotation.Nullable;

public class SaveStateService extends Service {
    public SaveStateService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String data = intent.getStringExtra("ahihi");
            Log.v("ahihi", "ahihi" + data);
            RxSchedulers rxSchedulers = new AppRxSchedulers();

            RetrofitClient.getApiInterface().getCarTest()
                    .subscribeOn(rxSchedulers.io())
                    .observeOn(rxSchedulers.androidThread())
                    .subscribe(
                            () -> {
                                stopSelf();
                                Log.v("ahihi", "ahihi success");
                            },
                            throwable -> {
                                stopSelf();
                                Log.v("ahihi", "ahihi error" + throwable.getMessage());
                            }
                    );
        }
        return START_NOT_STICKY;
    }
}
