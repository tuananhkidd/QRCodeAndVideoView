package com.kidd.projectbase.injection;

import androidx.annotation.NonNull;

import com.kidd.projectbase.interactor.PlayVideoInteractor;
import com.kidd.projectbase.interactor.impl.PlayVideoInteractorImpl;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.PlayVideoPresenter;
import com.kidd.projectbase.presenter.impl.PlayVideoPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class PlayVideoViewModule {
    @Provides
    public PlayVideoInteractor provideInteractor() {
        return new PlayVideoInteractorImpl();
    }

    @Provides
    public PresenterFactory<PlayVideoPresenter> providePresenterFactory(@NonNull final PlayVideoInteractor interactor) {
        return new PresenterFactory<PlayVideoPresenter>() {
            @NonNull
            @Override
            public PlayVideoPresenter create() {
                return new PlayVideoPresenterImpl(interactor);
            }
        };
    }
}
