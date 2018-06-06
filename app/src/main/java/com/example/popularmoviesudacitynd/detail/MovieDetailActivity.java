package com.example.popularmoviesudacitynd.detail;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmoviesudacitynd.BaseMvpActivity;
import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.database.MoviesDatabaseHelper;
import com.example.popularmoviesudacitynd.detail.reviewsrecycler.ReviewRecyclerAdapter;
import com.example.popularmoviesudacitynd.detail.trailersrecycler.TrailerRecyclerAdapter;
import com.example.popularmoviesudacitynd.network.Movie;
import com.example.popularmoviesudacitynd.network.MovieTrailer;
import com.example.popularmoviesudacitynd.network.Review;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends BaseMvpActivity<DetailView, DetailPresenter> implements DetailView {

    private static final String BASE_URL = "http://image.tmdb.org/t/p/w342/";

    @BindView(R.id.detail_overview_tv)
    TextView overviewTv;
    @BindView(R.id.detail_title_tv)
    TextView titleTv;
    @BindView(R.id.detail_rating_tv)
    TextView ratingTv;
    @BindView(R.id.detail_release_date_tv)
    TextView releaseDateTv;
    @BindView(R.id.backdrop_iv)
    ImageView backdropIv;
    @BindView(R.id.detail_poster_iv)
    ImageView posterIv;
    @BindView(R.id.detail_toolbar)
    Toolbar detailToolbar;
    @BindView(R.id.trailers_recycler_view)
    RecyclerView trailersRecyclerView;
    @BindView(R.id.reviews_recycler_view)
    RecyclerView reviewsRecyclerView;
    private Movie movie;
    private SQLiteDatabase sqLiteDatabase;

    @OnClick(R.id.floatingActionButton)
    public void favouriteClicked() {
        presenter.handleFavourite(movie, sqLiteDatabase);
    }

    private TrailerRecyclerAdapter trailerRecyclerAdapter;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        movie = Objects.requireNonNull(getIntent().getExtras()).getParcelable(Movie.KEY_MOVIE_DATA);
        assert movie != null;
        MoviesDatabaseHelper dbHelper = new MoviesDatabaseHelper(this);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        initViews(movie);
        initRecyclerViews();
        presenter.loadData(movie.getId(), DetailPresenter.DetailDataType.TRAILER);
        presenter.loadData(movie.getId(), DetailPresenter.DetailDataType.REVIEW);
    }

    private void initRecyclerViews() {
        initTrailersRV();
        initReviewsRV();
    }

    private void initReviewsRV() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        reviewsRecyclerView.setLayoutManager(layoutManager);
        reviewRecyclerAdapter = new ReviewRecyclerAdapter(this);
    }

    private void initTrailersRV() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        trailersRecyclerView.setLayoutManager(layoutManager);
        trailerRecyclerAdapter = new TrailerRecyclerAdapter(this);
    }

    private void initViews(Movie movie) {
        overviewTv.setText(movie.getOverview());
        titleTv.setText(movie.getTitle());
        initAppBar(movie.getTitle());
        ratingTv.setText(String.valueOf(movie.getVoteAverage()));
        releaseDateTv.setText(movie.getReleaseDate());
        Picasso.get().load(BASE_URL + movie.getPosterPath()).into(posterIv);
        Picasso.get().load(BASE_URL + movie.getBackdropPath()).into(backdropIv);
    }

    private void initAppBar(String title) {
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @NonNull
    @Override
    public DetailPresenter createPresenter() {
        return new DetailPresenter();
    }

    @Override
    public void onStartLoading() {
        //TODO: init progressBar
    }

    @Override
    public void onTrailersLoadCompleted(List<MovieTrailer> data) {
        //TODO: Introduce the progressbar here
        trailerRecyclerAdapter.setData(data);
        trailersRecyclerView.setAdapter(trailerRecyclerAdapter);
        trailerRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onReviewsLoadCompleted(List<Review> data) {
        //TODO: Introduce the progressbar here
        reviewRecyclerAdapter.setData(data);
        reviewsRecyclerView.setAdapter(reviewRecyclerAdapter);
        reviewRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadError() {
        //TODO: Add toast here
    }

    @Override
    public void onFavouriteAdded() {

    }

    @Override
    public void onFavouriteRemoved() {

    }
}
