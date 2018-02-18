package com.example.popularmoviesudacitynd;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {

    @BindView(R.id.base_toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_left_button)
    ImageView toolbarLeftButton;

    @BindView(R.id.toolbar_right_button)
    ImageView toolbarRightButton;

    @BindView(R.id.toolbar_main_title)
    TextView toolbarMainTitle;

    abstract public String getToolbarTitle();

    abstract public int getLayoutId();

    protected void onLeftButtonClicked() {
        onBackPressed();
    }

    protected void onRightButtonClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setActionBar(toolbar);
        toolbarMainTitle.setText(getToolbarTitle());
        toolbarLeftButton.setOnClickListener(v -> onLeftButtonClicked());
        toolbarRightButton.setOnClickListener(v -> onRightButtonClicked());
    }
}
