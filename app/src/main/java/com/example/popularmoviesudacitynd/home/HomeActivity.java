package com.example.popularmoviesudacitynd.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.popularmoviesudacitynd.BaseMvpActivity;
import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.recycler.PosterGridPresenter;
import com.example.popularmoviesudacitynd.recycler.PosterRecyclerAdapter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public class HomeActivity extends BaseMvpActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.home_recycler_view);
        layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        PosterRecyclerAdapter adapter = new PosterRecyclerAdapter(new PosterGridPresenter());
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new HomePresenter();
    }
}
