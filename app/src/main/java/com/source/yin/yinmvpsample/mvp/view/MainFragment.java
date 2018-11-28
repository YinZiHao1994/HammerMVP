package com.source.yin.yinmvpsample.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.source.yin.hammermvp.mvp.view.BaseMVPFragment;
import com.source.yin.yinmvpsample.R;
import com.source.yin.yinmvpsample.mvp.model.MyModel;
import com.source.yin.yinmvpsample.mvp.presenter.MyPresenter;

public class MainFragment extends BaseMVPFragment<MyPresenter> implements MyView {


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public MyPresenter createPresenter() {
        return new MyPresenter(new MyModel(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void showTextInfo(String s) {

    }

    @Override
    public void showLoadingProgress() {

    }

    @Override
    public void hindLoadingProgress() {

    }
}
