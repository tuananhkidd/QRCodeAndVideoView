package com.kidd.projectbase.presenter.impl;

import androidx.annotation.NonNull;

import com.kidd.projectbase.presenter.ShowVideoPresenter;
import com.kidd.projectbase.view.ShowVideoView;
import com.kidd.projectbase.interactor.ShowVideoInteractor;

import javax.inject.Inject;

public final class ShowVideoPresenterImpl extends BasePresenterImpl<ShowVideoView> implements ShowVideoPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ShowVideoInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public ShowVideoPresenterImpl(@NonNull ShowVideoInteractor interactor) {
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
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}