package com.kidd.projectbase.view.impl;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.zxing.Result;
import com.kidd.projectbase.R;
import com.kidd.projectbase.custom.ScannerDialog;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerHomeViewComponent;
import com.kidd.projectbase.injection.HomeViewModule;
import com.kidd.projectbase.presenter.HomePresenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.utils.ToastUtil;
import com.kidd.projectbase.view.HomeView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public final class HomeFragment extends BaseFragment<HomePresenter, HomeView>
        implements HomeView, ZXingScannerView.ResultHandler {
    public static final int REQUEST_CODE_CAMERA_PERMISSION = 1996;
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.iv_flash)
    ImageView ivFlash;
    private ZXingScannerView mScannerView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerHomeViewComponent.builder()
                .appComponent(parentComponent)
                .homeViewModule(new HomeViewModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        requestPermission();
        mScannerView = new ZXingScannerView(getContext());
        contentFrame.addView(mScannerView);
    }

    private void initScanner() {
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        initScanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    void onRetry() {

    }

    @Override
    void onRefreshData() {

    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void handleResult(Result result) {
        if (result != null) {
            ToastUtil.show(result.getText());
            ScannerDialog scannerDialog = new ScannerDialog(getContext());
            scannerDialog.displayInfo(result.getText());

            scannerDialog.setOnClickPlayVideoListener(url -> {
                scannerDialog.dismiss();
                getViewController().addFragment(ShowVideoFragment.class, null);
            });

            scannerDialog.setOnClickReScanListener(() -> {
                scannerDialog.dismiss();
                mScannerView.resumeCameraPreview(this);
            });

            scannerDialog.show();
        }
    }

    @Override
    public void backFromAddFragment() {
        super.backFromAddFragment();
        mScannerView.resumeCameraPreview(this);
    }

    private void requestPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA_PERMISSION);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initScanner();
                } else {
                    ToastUtil.show("Permission camera denied");
                }
            }
        }
    }

    @OnClick(R.id.iv_flash)
    void onFlashClick() {
        if (mPresenter != null) {
            ivFlash.setImageResource(mPresenter.getFlashStatus() ? R.drawable.ic_flash_off : R.drawable.ic_flash_on);
            mScannerView.setFlash(!mPresenter.getFlashStatus());
            mPresenter.setFlashStatus(!mPresenter.getFlashStatus());
        }
    }

}
