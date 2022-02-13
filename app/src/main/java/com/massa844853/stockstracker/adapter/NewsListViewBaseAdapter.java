package com.massa844853.stockstracker.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.models.News;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;

public class NewsListViewBaseAdapter extends BaseAdapter {

    private List<News> newsList;
    private Activity activity;

    public NewsListViewBaseAdapter(List<News> newsList, Activity activity) {
        this.newsList = newsList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if ( newsList != null) {
            return newsList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.news_list_item, parent, false);
        }

        TextView textViewNewsTitle = convertView.findViewById(R.id.news_title);
        TextView textViewPublisher = convertView.findViewById(R.id.news_publisher);
        ImageView imageViewMainImage = convertView.findViewById(R.id.main_image);

        textViewNewsTitle.setText(newsList.get(position).getTitle().toUpperCase());
        textViewPublisher.setText(newsList.get(position).getPublisher());


        DisplayMetrics displayMetrics = new DisplayMetrics();

        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        imageViewMainImage.setMinimumHeight(height/3);
        imageViewMainImage.setMaxHeight(height/3);

        try {
            Picasso.get().load(Uri.parse(newsList.get(position).getMain_image())).resize(width, height/3).into(imageViewMainImage);
        }
        catch (Exception ignored)
        {
            imageViewMainImage.setMinimumWidth(width);
            imageViewMainImage.setMaxWidth(width);
            imageViewMainImage.setMinimumHeight(height/3);
            imageViewMainImage.setMaxHeight(height/3);
        }


        return convertView;
    }
}