package com.source.yin.hammermvp.mvp.presenter;

import com.source.yin.hammermvp.mvp.view.IView;

public interface IPresenter<V extends IView> {

    void attach(V view);

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onRestart();
}
