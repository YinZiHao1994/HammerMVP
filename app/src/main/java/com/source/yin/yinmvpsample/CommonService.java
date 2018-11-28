package com.source.yin.yinmvpsample;

import com.source.yin.yinmvpsample.mvp.model.bean.GetHubUserInfoBean;
import com.source.yin.yinmvpsample.mvp.model.bean.GitHubRepoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface CommonService {

    @GET("users/{userName}")
    Call<GetHubUserInfoBean> getGitHubUserInfo(@Path("userName") String user);

    @GET("users/{user}/repos")
    Observable<List<GitHubRepoBean>> getGitHubUserRepos(@Path("user") String user);
}
