package com.example.popularmoviesudacitynd.home;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.popularmoviesudacitynd.BaseMvpActivity;
import com.example.popularmoviesudacitynd.R;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public class HomeActivity extends BaseMvpActivity {

    @Override
    public String getToolbarTitle() {
        //TODO: Set toolbar title
        return "test test";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new HomePresenter();
    }
}
