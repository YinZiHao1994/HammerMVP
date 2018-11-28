package com.source.yin.yinmvpsample.mvp.model;

import com.source.yin.hammermvp.mvp.model.BaseModel;
import com.source.yin.yinmvpsample.CommonService;
import com.source.yin.yinmvpsample.mvp.model.bean.GetHubUserInfoBean;
import com.source.yin.yinmvpsample.mvp.model.bean.GitHubRepoBean;

import java.util.List;

import retrofit2.Call;
import rx.Observable;

public class MyModel extends BaseModel {

    public Call<GetHubUserInfoBean> getGitHubUserInfo() {
        return mRepository.createRetrofitService(CommonService.class).getGitHubUserInfo("YinZiHao1994");
    }

    public Observable<List<GitHubRepoBean>> getGitHubUserRepos() {
        return mRepository.createRetrofitService(CommonService.class).getGitHubUserRepos("YinZiHao1994");
    }
}
