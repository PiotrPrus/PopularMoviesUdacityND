package com.example.popularmoviesudacitynd.detail.reviewsrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.popularmoviesudacitynd.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder implements ReviewItemView {

    private TextView reviewContentTV;
    private TextView reviewAuthorTV;

    ReviewViewHolder(View itemView) {
        super(itemView);
        reviewContentTV = itemView.findViewById(R.id.review_item_content);
        reviewAuthorTV = itemView.findViewById(R.id.review_item_author);
    }

    @Override
    public void setContent(String content) {
        if (content.length() > 100){
            String shortContent = content.substring(0, 100) + " ...";
            reviewContentTV.setText(shortContent);
        } else {
            reviewContentTV.setText(content);
        }
    }

    @Override
    public void setAuthor(String author) {
        reviewAuthorTV.setText(author);
    }
}
