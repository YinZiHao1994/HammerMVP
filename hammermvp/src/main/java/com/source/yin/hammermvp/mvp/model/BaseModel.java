package com.source.yin.hammermvp.mvp.model;

public class BaseModel implements IModel {

    protected Repository mRepository;

    public BaseModel() {
        mRepository = new Repository();
    }
}
