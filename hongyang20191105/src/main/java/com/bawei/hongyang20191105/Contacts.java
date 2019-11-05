package com.bawei.hongyang20191105;


public interface Contacts {
    interface IModel {
        void getInfo(String url, MyCallback callback);

        void postInfo(String url, MyCallback callback);
    }

    interface IView {
        void onSccuess(String jsonStr);

        void onFaild(String error);
    }

    interface Presenter {
        void attch(IView iView);
        void startRequest(String url);
        void deAttch();
    }

    interface MyCallback {
        void onSccuess(String jsonStr);

        void onFaild(String error);
    }
}
