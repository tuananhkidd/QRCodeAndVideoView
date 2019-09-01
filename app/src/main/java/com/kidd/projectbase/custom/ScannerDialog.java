package com.kidd.projectbase.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.kidd.projectbase.R;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ScannerDialog extends Dialog {
    @BindView(R.id.btn_play_video)
    TextView btnPlayVideo;
    @BindView(R.id.btn_scan)
    TextView btnScan;
    @BindView(R.id.tv_url)
    TextView tvInfo;

    private OnClickPlayVideoListener onClickPlayVideoListener;
    private OnClickReScanListener onClickReScanListener;

    public void setOnClickPlayVideoListener(OnClickPlayVideoListener onClickPlayVideoListener) {
        this.onClickPlayVideoListener = onClickPlayVideoListener;
    }

    public void setOnClickReScanListener(OnClickReScanListener onClickReScanListener) {
        this.onClickReScanListener = onClickReScanListener;
    }

    public ScannerDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.layout_scanner_dialog);
        ButterKnife.bind(this);

        btnScan.setOnClickListener(v -> {
            onClickReScanListener.onClickReScan();
        });
        btnPlayVideo.setOnClickListener(v -> {
            onClickPlayVideoListener.onClickPlayVideo(tvInfo.getText().toString());
        });
    }

    public interface OnClickPlayVideoListener {
        void onClickPlayVideo(String url);
    }

    public interface OnClickReScanListener {
        void onClickReScan();
    }

    public void displayInfo(String info) {
        tvInfo.setText(info);
    }
}
