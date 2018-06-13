package com.example.popularmoviesudacitynd.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmoviesudacitynd.BaseMvpActivity;
import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.database.MoviesDatabaseManager;
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
    @BindView(R.id.floatingActionButton)
    FloatingActionButton favButton;
    @BindView(R.id.detail_reviews_progress_bar)
    ProgressBar reviewsProgressBar;
    @BindView(R.id.detail_trailers_progress_bar)
    ProgressBar trailersProgressBar;


    private Movie movie;

    @OnClick(R.id.floatingActionButton)
    public void favouriteClicked() {
        presenter.handleFavourite(movie);
    }

    private TrailerRecyclerAdapter trailerRecyclerAdapter;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        movie = Objects.requireNonNull(getIntent().getExtras()).getParcelable(Movie.KEY_MOVIE_DATA);
        assert movie != null;
        initViews(movie);
        presenter.loadFavouriteButton(String.valueOf(movie.getId()));
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
        MoviesDatabaseManager dbManager = new MoviesDatabaseManager(getContentResolver());
        return new DetailPresenter(dbManager);
    }

    @Override
    public void onStartLoading(DetailPresenter.DetailDataType dataType) {
        if (dataType == DetailPresenter.DetailDataType.REVIEW) {
            reviewsProgressBar.setVisibility(View.VISIBLE);
        } else if (dataType == DetailPresenter.DetailDataType.TRAILER) {
            trailersProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTrailersLoadCompleted(List<MovieTrailer> data) {
        trailersProgressBar.setVisibility(View.GONE);
        trailerRecyclerAdapter.setData(data);
        trailersRecyclerView.setAdapter(trailerRecyclerAdapter);
        trailerRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onReviewsLoadCompleted(List<Review> data) {
        reviewsProgressBar.setVisibility(View.GONE);
        reviewRecyclerAdapter.setData(data);
        reviewsRecyclerView.setAdapter(reviewRecyclerAdapter);
        reviewRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadError() {
        Toast.makeText(this, getString(R.string.detail_load_content_error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void checkFavourite() {
        favButton.setColorFilter(getResources().getColor(R.color.fav_color_red));
    }

    @Override
    public void uncheckFavourite() {
        favButton.setColorFilter(getResources().getColor(R.color.fav_color_white));
    }
}
