package com.source.yin.hammermvp.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.source.yin.hammermvp.mvp.presenter.BasePresenter;

/**
 * Activity 基类，实现了 presenter 的初始化和生命周期处理
 * @param <P>
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.onCreate();
    }

    @NonNull
    public abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
