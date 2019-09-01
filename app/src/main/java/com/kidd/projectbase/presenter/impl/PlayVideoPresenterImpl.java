package com.kidd.projectbase.presenter.impl;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.kidd.projectbase.App;
import com.kidd.projectbase.presenter.PlayVideoPresenter;
import com.kidd.projectbase.service.SaveStateService;
import com.kidd.projectbase.view.PlayVideoView;
import com.kidd.projectbase.interactor.PlayVideoInteractor;

import javax.inject.Inject;

public final class PlayVideoPresenterImpl extends BasePresenterImpl<PlayVideoView> implements PlayVideoPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final PlayVideoInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public PlayVideoPresenterImpl(@NonNull PlayVideoInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
        Intent intent = new Intent(App.getContext(),SaveStateService.class);
        intent.putExtra("ahihi","ahuhu");
        App.getContext().startService(intent);
    }

    @Override
    public void onPresenterDestroyed() {
        super.onPresenterDestroyed();



//        compositeDisposable.add(mInteractor.testApi()
//                .doOnSubscribe(disposable -> {
//
//                })
//                .doFinally(() -> {
//
//                })
//                .subscribe(
//                        response -> {
//                            Log.v("ahihi","success");
//                        },
//                        throwable -> {
//                            Log.v("ahihi","error"+throwable.getMessage());
//                        }
//                ));
    }
}