package com.source.yin.hammermvp.mvp.presenter;

import com.source.yin.hammermvp.mvp.model.IModel;
import com.source.yin.hammermvp.mvp.view.IView;

public class BasePresenter<M extends IModel, V extends IView> {

    protected V mView;
    protected M mModel;

    public BasePresenter(M model, V view) {
        attach(view);
        this.mModel = model;
    }

    public void attach(V view) {
        mView = view;
    }

    public void onCreate() {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {
        mView = null;
    }

    public void onRestart() {

    }


}
