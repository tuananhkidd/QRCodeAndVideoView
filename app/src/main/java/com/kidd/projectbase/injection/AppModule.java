package com.kidd.projectbase.injection;

import android.content.Context;
import androidx.annotation.NonNull;

import com.kidd.projectbase.App;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    @NonNull
    private final App mApp;

    public AppModule(@NonNull App app) {
        mApp = app;
    }

    @ApplicationContext
    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public App provideApp() {
        return mApp;
    }
}
