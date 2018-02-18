package com.example.popularmoviesudacitynd;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public class MainActivity extends BaseMvpActivity {

    @Override
    public String getToolbarTitle() {
        //TODO: Set toolbar title
        return "test test";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        //TODO Make presenter
        return null;
    }
}
