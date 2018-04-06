package com.example.popularmoviesudacitynd.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.popularmoviesudacitynd.BaseMvpActivity;
import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.network.ResultsItem;
import com.example.popularmoviesudacitynd.recycler.PosterRecyclerAdapter;

import java.util.List;

public class HomeActivity extends BaseMvpActivity<HomeView, HomePresenter> implements HomeView{

    private RecyclerView recyclerView;

    @Override
    public String getToolbarTitle() {
        return getString(R.string.app_name);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupRecyclerView();
        presenter.loadData();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.home_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void onStartLoading() {

    }

    @Override
    public void onLoadCompleted(List<ResultsItem> data) {
        PosterRecyclerAdapter adapter = new PosterRecyclerAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadError() {

    }
}
