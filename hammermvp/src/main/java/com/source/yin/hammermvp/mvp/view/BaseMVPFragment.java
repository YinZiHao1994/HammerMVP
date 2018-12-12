package com.source.yin.hammermvp.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.source.yin.hammermvp.mvp.presenter.BasePresenter;

/**
 * Fragment 基类，实现了 presenter 的初始化和生命周期处理
 * @param <P>
 */
public abstract class BaseMVPFragment<P extends BasePresenter> extends Fragment {

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.onCreate();
    }

    @NonNull
    public abstract P createPresenter();


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
