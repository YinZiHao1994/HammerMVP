package com.source.yin.yinmvpsample.mvp.presenter;

import android.util.Log;

import com.source.yin.hammermvp.mvp.presenter.BasePresenter;
import com.source.yin.yinmvpsample.mvp.model.bean.GetHubUserInfoBean;
import com.source.yin.yinmvpsample.mvp.model.bean.GitHubRepoBean;
import com.source.yin.yinmvpsample.mvp.model.MyModel;
import com.source.yin.yinmvpsample.mvp.view.MyView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyPresenter extends BasePresenter<MyModel, MyView> {

    public MyPresenter(MyModel model, MyView view) {
        super(model, view);
    }

    public void getUserInfo() {
        Call<GetHubUserInfoBean> repos = mModel.getGitHubUserInfo();
        repos.enqueue(new Callback<GetHubUserInfoBean>() {
            @Override
            public void onResponse(Call<GetHubUserInfoBean> call, Response<GetHubUserInfoBean> response) {
                GetHubUserInfoBean body = response.body();
                mView.showTextInfo(body.getLogin());
            }

            @Override
            public void onFailure(Call<GetHubUserInfoBean> call, Throwable t) {
                mView.showTextInfo(t.getLocalizedMessage());
            }
        });

    }

    public void getUserRepo() {
        Observable<List<GitHubRepoBean>> gitHubUserRepos = mModel.getGitHubUserRepos();
        gitHubUserRepos
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<List<GitHubRepoBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<GitHubRepoBean> gitHubRepoBeans) {
                        for (GitHubRepoBean gitHubRepoBean : gitHubRepoBeans) {
                            String name = gitHubRepoBean.getName();
                            String description = gitHubRepoBean.getDescription();
                            Log.e("yzh", name);
                        }
                    }
                });


    }
}
