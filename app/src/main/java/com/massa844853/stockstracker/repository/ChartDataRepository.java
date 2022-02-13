package com.massa844853.stockstracker.repository;

import android.app.Application;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.models.ChartDataResponse;
import com.massa844853.stockstracker.models.NewsResponse;
import com.massa844853.stockstracker.models.StockPrice;
import com.massa844853.stockstracker.utils.ChartDataResponseCallback;
import com.massa844853.stockstracker.utils.Constants;
import com.massa844853.stockstracker.utils.NewsResponseCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.ChartDataApiService;
import service.NewsApiService;

public class ChartDataRepository {
    private final Application application;
    private final ChartDataResponseCallback chartDataResponseCallback;

    public  ChartDataRepository(Application application, ChartDataResponseCallback chartDataResponseCallback)
    {
        this.chartDataResponseCallback = chartDataResponseCallback;
        this.application = application;
    }

    public void start(String symbol, String interval, String range)
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ChartDataApiService chartDataApiService = retrofit.create(ChartDataApiService.class);

        Call<ChartDataResponse> jsonCall = chartDataApiService.getChartData(interval, symbol, range);

        jsonCall.enqueue(new Callback<ChartDataResponse>() {
            @Override
            public void onResponse(Call<ChartDataResponse> call, Response<ChartDataResponse> response) {
                ChartDataResponse chartDataResponse = response.body();
                if (response.body() != null && response.isSuccessful() && chartDataResponse.getChart().getResult() != null) {
                    List<StockPrice> stockPrices = new ArrayList<>();

                    for (int i = 0; i < chartDataResponse.getChart().getResult()[0].getTimestamp().length; i++)
                    {
                        if(chartDataResponse.getChart().getResult()[0].getIndicators().getAdjclose() != null) {
                            stockPrices.add(new StockPrice(new Date(chartDataResponse.getChart().getResult()[0].getTimestamp()[i] * 1000),
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getAdjclose()[0].getAdjclose()[i],
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getVolume()[i],
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getHigh()[i],
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getLow()[i],
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getOpen()[i],
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getClose()[i]));
                        }
                        else
                        {
                            stockPrices.add(new StockPrice(new Date(chartDataResponse.getChart().getResult()[0].getTimestamp()[i] * 1000),
                                    -1,
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getVolume()[i],
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getHigh()[i],
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getLow()[i],
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getOpen()[i],
                                    chartDataResponse.getChart().getResult()[0].getIndicators().getQuote()[0].getClose()[i]));
                        }
                    }



                    chartDataResponse = null;

                    chartDataResponseCallback.onResponsePrices(stockPrices);
                }
                else {
                    chartDataResponseCallback.onFailure(application.getString(R.string.error_data));
                }
            }

            @Override
            public void onFailure(Call<ChartDataResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Error. "+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}