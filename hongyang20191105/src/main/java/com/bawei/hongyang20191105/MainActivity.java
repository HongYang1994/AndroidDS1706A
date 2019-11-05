package com.bawei.hongyang20191105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bawei.hongyang20191105.adapter.MyAdapter;
import com.bawei.hongyang20191105.bean.MyData;
import com.bawei.hongyang20191105.presenter.PresenterImpl;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contacts.IView {
    private String url = "http://blog.zhaoliang5156.cn/api/shop/jingdong.json";
    private XBanner X_Banner;
    private GridView Grid_View;
    private Contacts.Presenter mPresenter;
    private List<MyData.GriddataBean> mList = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        myAdapter = new MyAdapter(MainActivity.this, mList);
        Grid_View.setAdapter(myAdapter);
        mPresenter = new PresenterImpl();
        mPresenter.attch(this);
        mPresenter.startRequest(url);
    }

    private void initView() {
        X_Banner = (XBanner) findViewById(R.id.X_Banner);
        Grid_View = (GridView) findViewById(R.id.Grid_View);
    }

    @Override
    public void onSccuess(String jsonStr) {
        Gson gson = new Gson();
        final MyData data = gson.fromJson(jsonStr, MyData.class);

        X_Banner.setBannerData(data.getBannerdata());
        X_Banner.loadImage(new XBanner.XBannerAdapter() {
           @Override
           public void loadBanner(XBanner banner, Object model, View view, int position) {
               Glide.with(MainActivity.this).load(data.getBannerdata().get(position).getImageurl()).into((ImageView) view);
            }
        });
        //打印
        Log.d("101", "onSccuess: json"+jsonStr);

        mList.addAll(data.getGriddata());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFaild(String error) {

    }
}
