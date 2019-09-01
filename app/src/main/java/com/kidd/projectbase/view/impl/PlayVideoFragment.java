package com.kidd.projectbase.view.impl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import tcking.github.com.giraffeplayer2.GiraffePlayer;
import tcking.github.com.giraffeplayer2.Option;
import tcking.github.com.giraffeplayer2.PlayerManager;
import tcking.github.com.giraffeplayer2.VideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.github.tcking.viewquery.ViewQuery;
import com.google.android.material.appbar.AppBarLayout;
import com.kidd.projectbase.R;
import com.kidd.projectbase.view.PlayVideoView;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.PlayVideoPresenter;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.PlayVideoViewModule;
import com.kidd.projectbase.injection.DaggerPlayVideoViewComponent;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import static tcking.github.com.giraffeplayer2.GiraffePlayer.DISPLAY_NORMAL;

public final class PlayVideoFragment extends BaseFragment<PlayVideoPresenter, PlayVideoView> implements PlayVideoView {
    private ViewQuery viewQuery;
    private VideoView videoView;
    int currentHeight = 0;
    //    @BindView(R.id.appBarLayout)
//    AppBarLayout appBarLayout;
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

//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
//                    // Collapsed
//                    videoView.getPlayer().setDisplayModel(GiraffePlayer.DISPLAY_FLOAT);
//                } else if (verticalOffset == 0) {
//                    // Expanded
//                    videoView.getPlayer().setDisplayModel(GiraffePlayer.DISPLAY_NORMAL);
//                } else {
//                    // Somewhere in between
//                }
//            }
//        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewQuery = new ViewQuery(view);
        String testUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        videoView = viewQuery.id(R.id.video_view).view();
        videoView.setVideoPath(testUrl);

        Log.v("ahihi", videoView.getVideoInfo().isFullScreenAnimation() + "  " + videoView.getVideoInfo().isPortraitWhenFullScreen());
        getHeightOfVideoView();
    }

    private void getHeightOfVideoView() {
        videoView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                currentHeight = videoView.getHeight();
                videoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
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
        Log.v("ahihi", "height" +currentHeight+"  "+ videoView.getHeight());
        if (currentHeight == videoView.getHeight()) {
            getViewController().backFromAddFragment(null);
        } else {
            videoView.getPlayer().setDisplayModel(DISPLAY_NORMAL);
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        videoView.getPlayer().release();
    }
}
