package com.kidd.projectbase.interactor.impl;

import javax.inject.Inject;

import com.kidd.projectbase.interactor.PlayVideoInteractor;
import com.kidd.projectbase.network.request.Apis;
import com.kidd.projectbase.network.response.BaseResponse;
import com.kidd.projectbase.network.response.CarResponse;
import com.kidd.projectbase.utils.rx.RxSchedulers;

import java.util.List;

import io.reactivex.Single;

public final class PlayVideoInteractorImpl implements PlayVideoInteractor {
    private Apis api;
    private RxSchedulers rxSchedulers;
    @Inject
    public PlayVideoInteractorImpl(Apis api,RxSchedulers rxSchedulers) {
        this.api = api;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public Single<BaseResponse<List<CarResponse>>> testApi() {
        return api.getCar()
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.compute());
    }
}