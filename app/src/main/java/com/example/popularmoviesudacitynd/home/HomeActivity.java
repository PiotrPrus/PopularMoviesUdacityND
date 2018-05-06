package com.example.popularmoviesudacitynd.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.popularmoviesudacitynd.BaseMvpActivity;
import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.network.ResultsItem;
import com.example.popularmoviesudacitynd.recycler.PosterRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseMvpActivity<HomeView, HomePresenter> implements HomeView{

    private RecyclerView recyclerView;
    @BindView(R.id.home_progress_bar)
    ProgressBar homeProgressBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
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
    homeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadCompleted(List<ResultsItem> data) {
        PosterRecyclerAdapter adapter = new PosterRecyclerAdapter(this, data);
        recyclerView.setAdapter(adapter);
        homeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoadError() {
        Toast.makeText(this, getString(R.string.home_unable_to_fetch_data), Toast.LENGTH_SHORT).show();
    }
}
