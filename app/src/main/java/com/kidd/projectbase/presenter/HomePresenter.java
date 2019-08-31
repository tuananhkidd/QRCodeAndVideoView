package com.kidd.projectbase.presenter;

import com.kidd.projectbase.view.HomeView;

public interface HomePresenter extends BasePresenter<HomeView> {
    boolean getFlashStatus();
    void setFlashStatus(boolean isOn);
}