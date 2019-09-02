package com.kidd.projectbase.injection;

import androidx.annotation.NonNull;

import com.kidd.projectbase.interactor.ShowVideoInteractor;
import com.kidd.projectbase.interactor.impl.ShowVideoInteractorImpl;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.ShowVideoPresenter;
import com.kidd.projectbase.presenter.impl.ShowVideoPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class ShowVideoViewModule {
    @Provides
    public ShowVideoInteractor provideInteractor() {
        return new ShowVideoInteractorImpl();
    }

    @Provides
    public PresenterFactory<ShowVideoPresenter> providePresenterFactory(@NonNull final ShowVideoInteractor interactor) {
        return new PresenterFactory<ShowVideoPresenter>() {
            @NonNull
            @Override
            public ShowVideoPresenter create() {
                return new ShowVideoPresenterImpl(interactor);
            }
        };
    }
}
