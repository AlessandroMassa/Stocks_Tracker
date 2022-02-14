package com.massa844853.stockstracker.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.models.Statistic;
import com.massa844853.stockstracker.models.StatisticValue;

import java.util.List;

public class StatisticsListViewBaseAdapter extends BaseAdapter {


    private List<Statistic> eleStatistic;
    private Activity activity;

    public StatisticsListViewBaseAdapter(List<Statistic> eleStatistic, Activity activity) {
        this.eleStatistic = eleStatistic;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if ( eleStatistic != null) {
            return eleStatistic.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return eleStatistic.get(position);
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
                    inflate(R.layout.statistics_list_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.textViewName)).setText(eleStatistic.get(position).getName());
        ((TextView) convertView.findViewById(R.id.textViewValue)).setText(eleStatistic.get(position).getValue());

        return convertView;
    }
}
