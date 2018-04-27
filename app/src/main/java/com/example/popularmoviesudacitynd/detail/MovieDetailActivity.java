package com.example.popularmoviesudacitynd.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.popularmoviesudacitynd.BaseMvpActivity;
import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.network.ResultsItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseMvpActivity<DetailView, DetailPresenter> implements DetailView {

    @BindView(R.id.detail_overview_tv)
    TextView overviewTv;
    @BindView(R.id.detail_title_tv)
    TextView titleTv;
    @BindView(R.id.detail_rating_tv)
    TextView ratingTv;
    @BindView(R.id.detail_release_date_tv)
    TextView releaseDateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ResultsItem movie = getIntent().getExtras().getParcelable(ResultsItem.KEY_MOVIE_DATA);
        overviewTv.setText(movie.getOverview());
        titleTv.setText(movie.getTitle());
        ratingTv.setText(String.valueOf(movie.getVoteAverage()));
        releaseDateTv.setText(movie.getReleaseDate());
    }

    @Override
    public String getToolbarTitle() {
        return "Detail view";
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
}
