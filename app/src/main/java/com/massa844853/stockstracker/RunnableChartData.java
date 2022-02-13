package com.massa844853.stockstracker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.massa844853.stockstracker.models.StockPrice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;

public class RunnableChartData {
/*
    private String period;
    private CandleStickChart chartPrices;
    private ArrayList<StockPrice> listPrices;

    private String interval;
    private String asset;
    private Context context;
    private Float returnValue;
    private Activity activity;
    private TextView stockReturn;
    private TextView stockName;
    private TextView actualStockPrice;

    public RunnableChartData( String period, String interval, CandleStickChart chartPrices, ArrayList<StockPrice> listPrices,
                              ArrayList<CandleEntry> candleEntryArrayList, String asset, Context context,
                              Activity activity, TextView stockReturn, TextView stockName, TextView actualStockPrice) {
        super();
        this.period = period;
        this.chartPrices = chartPrices;
        this.listPrices = listPrices;
        this.candleEntryArrayList = candleEntryArrayList;
        this.interval = interval;
        this.asset = asset;
        this.context = context;
        this.activity = activity;
        this.stockReturn = stockReturn;
        this.stockName = stockName;
        this.actualStockPrice = actualStockPrice;
        returnValue = 0F;
    }

    @Override
    public void run() {
        int scarto = 0;
        float adjPrice = 0;

        String json = "";
        String line = "";

        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        URL url = null;

        chartPrices.clear();

        try {
            //richiesta alla web api
            url = new URL("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v3/get-chart?symbol=" + asset + "&interval=" + interval + "&range="+ period + "&region=US");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("x-rapidapi-key", "cb164af635msh6360057f9396698p136748jsn615ba66383ec");
            urlConnection.setRequestProperty("x-rapidapi-host", "yh-finance.p.rapidapi.com");

            //inputstreamreader mi permette di convertire da byte a caratteri
            //con bufferedreader salvo i caratteri convertiti in un buffer
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line = in.readLine()) != null) {
                    json = json + line;
                }
                //recupero il vettore dei prezzi e delle date
            JSONObject datesPrices = new JSONObject(json).getJSONObject("chart").getJSONArray("result").getJSONObject(0);
            JSONArray dates = datesPrices.getJSONArray("timestamp");
            JSONArray adjClosingPrice = null;
            try {
                adjClosingPrice = datesPrices.getJSONObject("indicators").getJSONArray("adjclose").getJSONObject(0).getJSONArray("adjclose");
            }
            catch (Exception e)
            {
                adjClosingPrice = null;
            }

            JSONArray closePrice = datesPrices.getJSONObject("indicators").getJSONArray("quote").getJSONObject(0).getJSONArray("close");
            JSONArray highPrice = datesPrices.getJSONObject("indicators").getJSONArray("quote").getJSONObject(0).getJSONArray("high");
            JSONArray openPrice = datesPrices.getJSONObject("indicators").getJSONArray("quote").getJSONObject(0).getJSONArray("open");
            JSONArray volume = datesPrices.getJSONObject("indicators").getJSONArray("quote").getJSONObject(0).getJSONArray("volume");
            JSONArray lowPrice = datesPrices.getJSONObject("indicators").getJSONArray("quote").getJSONObject(0).getJSONArray("low");
            Date date = null;
            StockPrice stockPrice = null;

            //recupero la data e il prezzi e li salvo in una lista
            for (int i = 0; i < closePrice.length(); i++) {
                date = new Date(dates.getLong(i) * 1000);
                try {
                    if(adjClosingPrice != null)
                    {

                        adjPrice = Float.parseFloat(adjClosingPrice.get(i).toString());
                    }
                    else
                    {
                        adjPrice = -1;
                    }
                    stockPrice = new StockPrice(date, adjPrice,volume.get(i).toString(),
                            Float.parseFloat(highPrice.get(i).toString()), Float.parseFloat(lowPrice.get(i).toString()),
                            Float.parseFloat(openPrice.get(i).toString()),Float.parseFloat(closePrice.get(i).toString()));
                    listPrices.add(stockPrice);

                    candleEntryArrayList.add(new CandleEntry(i - scarto, stockPrice.getMaxPrice(), stockPrice.getMinPrice(),
                            stockPrice.getOpenPrice(),stockPrice.getClosePrice()));
                }
                catch (Exception e)
                {
                    scarto = scarto + 1;
                }

            }



            CandleDataSet set1 = new CandleDataSet(candleEntryArrayList, "DataSet 1");

            set1.setShadowColor(ResourcesCompat.getColor(context.getResources(), R.color.grey, null));
            set1.setShadowWidth(0.8f);
            set1.setDecreasingColor(ResourcesCompat.getColor(context.getResources(), R.color.red, null));
            set1.setDecreasingPaintStyle(Paint.Style.FILL);
            set1.setIncreasingColor(ResourcesCompat.getColor(context.getResources(), R.color.lightgreen, null));
            set1.setIncreasingPaintStyle(Paint.Style.FILL);
            set1.setNeutralColor(ResourcesCompat.getColor(context.getResources(), R.color.grey, null));
            set1.setDrawValues(false);

            CandleData data = new CandleData(set1);

            chartPrices.setData(data);
            chartPrices.invalidate();


            activity.runOnUiThread(new Runnable() {
            @Override

            public void run() {
                try {

                }
                catch (Exception e)
                {

                }
            }
        });

            } catch (Exception e) {

            chartPrices.setNoDataText(context.getResources().getString(R.string.error_data));
            chartPrices.invalidate();

            e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

    }
*/


}
