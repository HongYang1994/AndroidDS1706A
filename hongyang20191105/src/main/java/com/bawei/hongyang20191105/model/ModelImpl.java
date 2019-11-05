package com.bawei.hongyang20191105.model;

import com.bawei.hongyang20191105.Contacts;
import com.bawei.hongyang20191105.utils.NetWorkUtils;

public class ModelImpl implements Contacts.IModel {
    @Override
    public void getInfo(String url, final Contacts.MyCallback callback) {
        NetWorkUtils.getInstance().getInfo(url, new NetWorkUtils.NetCallback() {
            @Override
            public void onSuccess(String json) {
                callback.onSccuess(json);
            }

            @Override
            public void onError(String error) {
                callback.onSccuess(error);
            }
        });
    }

    @Override
    public void postInfo(String url, Contacts.MyCallback callback) {

    }
}
