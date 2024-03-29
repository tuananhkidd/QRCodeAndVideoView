package com.kidd.projectbase.interactor;

import com.kidd.projectbase.network.response.BaseResponse;
import com.kidd.projectbase.network.response.CarResponse;

import java.util.List;

import io.reactivex.Single;

public interface PlayVideoInteractor extends BaseInteractor {
    Single<BaseResponse<List<CarResponse>>> testApi();
}