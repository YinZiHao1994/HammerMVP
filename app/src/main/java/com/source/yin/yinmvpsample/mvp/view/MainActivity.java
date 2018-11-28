package com.source.yin.yinmvpsample.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.source.yin.hammermvp.mvp.view.BaseMVPActivity;
import com.source.yin.yinmvpsample.mvp.model.MyModel;
import com.source.yin.yinmvpsample.mvp.presenter.MyPresenter;
import com.source.yin.yinmvpsample.R;

public class MainActivity extends BaseMVPActivity<MyPresenter> implements MyView {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        mPresenter.getUserInfo();
        mPresenter.getUserRepo();

    }

    @NonNull
    @Override
    public MyPresenter createPresenter() {
        return new MyPresenter(new MyModel(), this);
    }


    @Override
    public void showTextInfo(String s) {
        textView.setText(s);
    }

    @Override
    public void showLoadingProgress() {

    }

    @Override
    public void hindLoadingProgress() {

    }
}
