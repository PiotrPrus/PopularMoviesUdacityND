package com.example.popularmoviesudacitynd;

import android.os.Bundle;
import android.os.Debug;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {

    abstract public int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }
}
