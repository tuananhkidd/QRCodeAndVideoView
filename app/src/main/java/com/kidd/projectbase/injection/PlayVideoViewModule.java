package com.kidd.projectbase.injection;

import androidx.annotation.NonNull;

import com.kidd.projectbase.interactor.PlayVideoInteractor;
import com.kidd.projectbase.interactor.impl.PlayVideoInteractorImpl;
import com.kidd.projectbase.network.request.Apis;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.PlayVideoPresenter;
import com.kidd.projectbase.presenter.impl.PlayVideoPresenterImpl;
import com.kidd.projectbase.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public final class PlayVideoViewModule {
    @Provides
    public PlayVideoInteractor provideInteractor(Apis api, RxSchedulers rxSchedulers) {
        return new PlayVideoInteractorImpl(api, rxSchedulers);
    }

    @Provides
    public PresenterFactory<PlayVideoPresenter> providePresenterFactory(@NonNull final PlayVideoInteractor interactor) {
        return () -> new PlayVideoPresenterImpl(interactor);
    }
}
