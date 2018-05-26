package com.example.popularmoviesudacitynd.detail.reviewsrecycler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.popularmoviesudacitynd.R;
import com.example.popularmoviesudacitynd.network.Review;

import java.util.List;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private List<Review> data;
    private Review review;
    private Activity activity;

    public void setData(List<Review> data) {
        this.data = data;
    }

    public ReviewRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_review_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        review = data.get(position);
        onBindReviewItemViewOnPosition(holder);
        holder.itemView.setOnClickListener(view -> {
            Review currentReview = data.get(position);
            showDialog(currentReview.getContent(), currentReview.getAuthor());
        });
    }

    private void showDialog(String content, String author) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(author + " says:")
                .setMessage(content)
                .setPositiveButton(R.string.generic_ok,
                        (dialogInterface, i) -> dialogInterface.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void onBindReviewItemViewOnPosition(ReviewViewHolder itemView) {
        itemView.setContent(review.getContent());
        itemView.setAuthor(review.getAuthor());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
