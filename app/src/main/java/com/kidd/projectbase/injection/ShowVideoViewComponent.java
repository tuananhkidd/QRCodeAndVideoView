package com.kidd.projectbase.injection;

import com.kidd.projectbase.view.impl.ShowVideoFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ShowVideoViewModule.class)
public interface ShowVideoViewComponent {
    void inject(ShowVideoFragment fragment);
}