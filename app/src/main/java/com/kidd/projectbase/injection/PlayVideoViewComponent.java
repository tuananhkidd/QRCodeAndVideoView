package com.kidd.projectbase.injection;

import com.kidd.projectbase.view.impl.PlayVideoFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = PlayVideoViewModule.class)
public interface PlayVideoViewComponent {
    void inject(PlayVideoFragment fragment);
}