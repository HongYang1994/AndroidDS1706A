package com.bawei.hongyang20191105.presenter;

import com.bawei.hongyang20191105.Contacts;
import com.bawei.hongyang20191105.model.ModelImpl;

public class PresenterImpl implements Contacts.Presenter {
    private Contacts.IView mIView;
    private Contacts.IModel mIModel;


    @Override
    public void attch(Contacts.IView iView) {
        this.mIView = iView;
        mIModel = new ModelImpl();
    }

    @Override
    public void startRequest(String url) {
        mIModel.getInfo(url, new Contacts.MyCallback() {
            @Override
            public void onSccuess(String jsonStr) {
                mIView.onSccuess(jsonStr);
            }

            @Override
            public void onFaild(String error) {
                mIView.onFaild(error);
            }
        });
    }

    @Override
    public void deAttch() {
        if (mIView != null) {
            mIView = null;
        }
        if (mIModel != null) {
            mIModel = null;
        }
    }
}
