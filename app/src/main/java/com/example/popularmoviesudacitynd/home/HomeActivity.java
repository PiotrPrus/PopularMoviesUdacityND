package com.example.popularmoviesudacitynd.home;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.popularmoviesudacitynd.BaseMvpActivity;
import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.network.ResultsItem;
import com.example.popularmoviesudacitynd.recycler.PosterRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseMvpActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.home_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.home_progress_bar)
    ProgressBar homeProgressBar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private PosterRecyclerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        checkNetworkAvailability();
        initMenu();
        initRecyclerView();
        presenter.loadData(HomePresenter.MovieListType.POPULAR);
    }

    private void checkNetworkAvailability() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        presenter.isNetworkConnection(connectivityManager);
    }

    private void initMenu() {
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_popular: {
                    presenter.loadData(HomePresenter.MovieListType.POPULAR);
                    break;
                }
                case R.id.nav_top_rated: {
                    presenter.loadData(HomePresenter.MovieListType.TOP_RATED);
                    break;
                }
                case R.id.nav_favorite: {
                    //do something here
                    break;
                }
            }
            item.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
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
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        homeProgressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PosterRecyclerAdapter(this);
    }

    @Override
    public void onLoadError() {
        Toast.makeText(this, getString(R.string.home_unable_to_fetch_data), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInternetCheckFailed() {
        showNoNetworkConnectivityDialog();
    }

    private void showNoNetworkConnectivityDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.home_screen_internet_connection_message))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.home_screen_internet_connection_ok), (dialog, which) ->
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)))
                .setNegativeButton(getString(R.string.home_screen_internet_connection_no), (dialog, which) -> finish());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
