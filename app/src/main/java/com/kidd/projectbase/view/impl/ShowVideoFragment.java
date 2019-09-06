package com.kidd.projectbase.view.impl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import hb.xvideoplayer.MxMediaManager;
import hb.xvideoplayer.MxVideoPlayer;
import hb.xvideoplayer.MxVideoPlayerManager;
import hb.xvideoplayer.MxVideoPlayerWidget;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kidd.projectbase.R;
import com.kidd.projectbase.view.ShowVideoView;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.ShowVideoPresenter;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.ShowVideoViewModule;
import com.kidd.projectbase.injection.DaggerShowVideoViewComponent;

import javax.inject.Inject;

import static hb.xvideoplayer.MxVideoPlayer.CURRENT_STATE_PAUSE;

public final class ShowVideoFragment extends BaseFragment<ShowVideoPresenter, ShowVideoView> implements ShowVideoView {
    @Inject
    PresenterFactory<ShowVideoPresenter> mPresenterFactory;
    @BindView(R.id.video_view)
    MxVideoPlayerWidget videoPlayerWidget;
    // Your presenter is available using the mPresenter variable

    public ShowVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void initData() {
        super.initData();
        String testUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        videoPlayerWidget.autoStartPlay(testUrl, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "Demo Title");
        new Handler().postDelayed(() -> {
            if (MxMediaManager.getInstance().getPlayer() != null) {
                MxMediaManager.getInstance().getPlayer().pause();
                videoPlayerWidget.setUiPlayState(CURRENT_STATE_PAUSE);
            }
        }, 500);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MxVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (MxMediaManager.getInstance().getPlayer() != null || MxVideoPlayerManager.getCurrentListener() == null) {
            MxMediaManager.getInstance().getPlayer().pause();
            videoPlayerWidget.setUiPlayState(CURRENT_STATE_PAUSE);
        }
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerShowVideoViewComponent.builder()
                .appComponent(parentComponent)
                .showVideoViewModule(new ShowVideoViewModule())
                .build()
                .inject(this);
    }

    @Override
    public boolean backPressed() {
        if (!MxVideoPlayer.backPress() || MxVideoPlayerManager.getCurrentListener() == null) {
            getViewController().backFromAddFragment(null);
        }
        return false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_show_video;
    }

    @Override
    void onRetry() {

    }

    @Override
    void onRefreshData() {

    }

    @NonNull
    @Override
    protected PresenterFactory<ShowVideoPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
