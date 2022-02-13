package com.massa844853.stockstracker.repository;

import android.app.Application;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.models.Statistic;
import com.massa844853.stockstracker.models.StatisticValue;
import com.massa844853.stockstracker.models.StatisticsResponse;
import com.massa844853.stockstracker.utils.Constants;
import com.massa844853.stockstracker.utils.StatisticsResponseCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.StatisticsApiService;

public class StatisticsRepository {

    private final Application application;
    private final StatisticsResponseCallback statisticsResponseCallback;

    public StatisticsRepository(Application context, StatisticsResponseCallback statisticsResponseCallback)
    {
        this.application = context;
        this.statisticsResponseCallback = statisticsResponseCallback;

    }

    public void start(String symbol) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        StatisticsApiService statisticsApiService = retrofit.create(StatisticsApiService.class);

        Call<StatisticsResponse> jsonCall = statisticsApiService.getStatistics(symbol);

        jsonCall.enqueue(new Callback<StatisticsResponse>() {
            @Override
            public void onResponse(Call<StatisticsResponse> call, Response<StatisticsResponse> response) {
                StatisticsResponse statisticsResponse = response.body();
                if (response.body() != null && response.isSuccessful()) {
                    List<Statistic> eleStatistic = new ArrayList<>();

                    eleStatistic.add(new Statistic("Short Name", statisticsResponse.getPrice().getShortName()));
                    eleStatistic.add(new Statistic("Market State", statisticsResponse.getPrice().getMarketState()));
                    eleStatistic.add(new Statistic("Quote Type", statisticsResponse.getPrice().getQuoteType()));
                    eleStatistic.add(new Statistic("Market Cap", statisticsResponse.getPrice().getMarketCap().getFmt()));
                    eleStatistic.add(new Statistic("Shares Outstanding", statisticsResponse.getDefaultKeyStatistics().getSharesOutstanding().getFmt()));
                    eleStatistic.add(new Statistic("Beta", statisticsResponse.getDefaultKeyStatistics().getBeta().getFmt()));
                    eleStatistic.add(new Statistic("Enterprise Value", statisticsResponse.getDefaultKeyStatistics().getEnterpriseValue().getFmt()));
                    eleStatistic.add(new Statistic("52 Week Change", statisticsResponse.getDefaultKeyStatistics().get_52WeekChange().getFmt()));
                    eleStatistic.add(new Statistic("Last Dividend Date", statisticsResponse.getDefaultKeyStatistics().getLastDividendDate().getFmt()));

                    statisticsResponseCallback.onResponse(eleStatistic);
                }
                else {
                    statisticsResponseCallback.onFailure(application.getString(R.string.error_search));
                }
            }

            @Override
            public void onFailure(Call<StatisticsResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Error. "+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
