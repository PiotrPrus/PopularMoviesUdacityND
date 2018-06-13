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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.popularmoviesudacitynd.BaseMvpActivity;
import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.database.MoviesDatabaseManager;
import com.example.popularmoviesudacitynd.network.Movie;
import com.example.popularmoviesudacitynd.home.recycler.PosterRecyclerAdapter;

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
    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;

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
        initUI();
        presenter.loadData(HomePresenter.MovieListType.POPULAR);
    }

    private void initUI() {
        setSupportActionBar(homeToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24px);
        homeToolbar.setTitle(R.string.app_name);
        initMenu();
        initRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                    MoviesDatabaseManager dbManager = new MoviesDatabaseManager(getContentResolver());
                    presenter.loadFavourites(dbManager);
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
    public void onLoadCompleted(List<Movie> data) {
        homeProgressBar.setVisibility(View.GONE);
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
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
                .setPositiveButton(getString(R.string.home_screen_internet_connection_ok), (dialog, which) ->
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)))
                .setNegativeButton(getString(R.string.home_screen_internet_connection_no), (dialog, which) -> finish());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
