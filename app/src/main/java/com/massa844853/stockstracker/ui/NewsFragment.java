package com.massa844853.stockstracker.ui;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.utils.NewsResponseCallback;
import com.massa844853.stockstracker.adapter.NewsListViewBaseAdapter;
import com.massa844853.stockstracker.models.News;
import com.massa844853.stockstracker.repository.NewsRepository;
import com.massa844853.stockstracker.viewmodels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment implements NewsResponseCallback {


    private NewsRepository newsRepository;
    private ProgressBar progressBar;
    private NewsListViewBaseAdapter newsListViewBaseAdapter;
    private ListView listView;
    private List<News> newsList;
    private NewsViewModel newsViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        view.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.backgroundblue, null));

        progressBar = view.findViewById(R.id.progress_bar);
        listView = view.findViewById(R.id.listview_news);

        newsViewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        newsList = new ArrayList<>();
        newsListViewBaseAdapter = new NewsListViewBaseAdapter(newsList, getActivity());
        listView.setAdapter(newsListViewBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        progressBar.setVisibility(View.VISIBLE);
        newsRepository = new NewsRepository(requireActivity().getApplication(), this);
        newsRepository.start(newsViewModel.getLastUpdate());

        return view;
    }

    @Override
    public void onResponse(List<News> newsList, long lastUpdate) {
        this.newsList.addAll(newsList);
        if(this.newsList != null) {
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    newsViewModel.setLastUpdate(lastUpdate);
                    newsListViewBaseAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void onFailure(String errorMessage) {

    }
}