package com.kidd.projectbase.view.impl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tcking.github.com.giraffeplayer2.Option;
import tcking.github.com.giraffeplayer2.PlayerManager;
import tcking.github.com.giraffeplayer2.VideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.tcking.viewquery.ViewQuery;
import com.kidd.projectbase.R;
import com.kidd.projectbase.view.PlayVideoView;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.PlayVideoPresenter;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.PlayVideoViewModule;
import com.kidd.projectbase.injection.DaggerPlayVideoViewComponent;

import javax.inject.Inject;

public final class PlayVideoFragment extends BaseFragment<PlayVideoPresenter, PlayVideoView> implements PlayVideoView {
    private ViewQuery viewQuery;

    @Inject
    PresenterFactory<PlayVideoPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public PlayVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void initView() {
        super.initView();
        PlayerManager.getInstance().getDefaultVideoInfo().addOption(Option.create(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "multiple_requests", 1L));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewQuery = new ViewQuery(view);
        String testUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        final VideoView videoView = viewQuery.id(R.id.video_view).view();
        videoView.setVideoPath(testUrl);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerPlayVideoViewComponent.builder()
                .appComponent(parentComponent)
                .playVideoViewModule(new PlayVideoViewModule())
                .build()
                .inject(this);
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_play_video;
    }

    @Override
    void onRetry() {

    }

    @Override
    void onRefreshData() {

    }

    @NonNull
    @Override
    protected PresenterFactory<PlayVideoPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
