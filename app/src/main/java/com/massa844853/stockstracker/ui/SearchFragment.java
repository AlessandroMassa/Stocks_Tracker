package com.massa844853.stockstracker.ui;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.google.android.material.tabs.TabLayout;
import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.adapter.StatisticsListViewBaseAdapter;
import com.massa844853.stockstracker.models.Statistic;
import com.massa844853.stockstracker.models.StockPrice;
import com.massa844853.stockstracker.repository.ChartDataRepository;
import com.massa844853.stockstracker.repository.StatisticsRepository;
import com.massa844853.stockstracker.utils.ChartDataResponseCallback;
import com.massa844853.stockstracker.utils.StatisticsResponseCallback;


public class SearchFragment extends Fragment implements StatisticsResponseCallback, ChartDataResponseCallback {

    private CandleStickChart chartPrices;
    private ArrayList<CandleEntry> candleEntryArrayList;

    private TabLayout tabLayout;
    private final String[] intervals = {"5m", "60m", "1d", "1d", "1wk", "1mo"};
    private final String[] periods = {"1d", "5d", "1mo", "3mo", "1y", "5y"};
    private SearchView searchView;
    private String asset = "";

    private EditText editText;
    private int position = 0;
    private TextView stockName;
    private TextView stockReturn;
    private TextView actualStockPrice;
    private ProgressBar progressBarSearch;
    private ListView listViewStatistics;
    private MainActivity mainActivity;

    private StatisticsRepository statisticsRepository;
    private StatisticsListViewBaseAdapter statisticsListViewAdapter;

    private ChartDataRepository chartDataRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        view.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.backgroundblue, null));

        stockName = (TextView) view.findViewById(R.id.stockName);
        stockReturn = (TextView) view.findViewById(R.id.stockReturn);
        actualStockPrice = (TextView) view.findViewById(R.id.actualStockPrice);
        progressBarSearch = (ProgressBar) view.findViewById(R.id.progressBarSearch);

        progressBarSearch.setVisibility(View.INVISIBLE);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        chartPrices = (CandleStickChart) view.findViewById(R.id.chartPrices);

        searchView = (SearchView) view.findViewById(R.id.searchView);
        listViewStatistics = (ListView)  view.findViewById(R.id.listViewStatistics);

        mainActivity = (MainActivity) getActivity();

        statisticsRepository = new StatisticsRepository(requireActivity().getApplication(), this);
        statisticsListViewAdapter = new StatisticsListViewBaseAdapter(mainActivity.newsPricesViewModel.getStatisticList(), getActivity());
        listViewStatistics.setAdapter(statisticsListViewAdapter);

        candleEntryArrayList = new ArrayList<>();
        chartDataRepository = new ChartDataRepository(requireActivity().getApplication(), this);

        setChartStyle();

        if(mainActivity.newsPricesViewModel.getPriceList().size() != 0)
             populateChart();
        if(mainActivity.newsPricesViewModel.getStatisticList().size() != 0)
             populateStatistics();

        tabLayout.getTabAt(mainActivity.newsPricesViewModel.getPosition()).select();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!query.isEmpty()) {
                    asset = query;

                    searchView.clearFocus();
                    editText.setText("");
                    progressBarSearch.setVisibility(View.VISIBLE);

                    candleEntryArrayList = new ArrayList<>();
                    statisticsRepository.start(asset);
                    chartDataRepository.start(asset, intervals[position] , periods[position]);

                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.clearFocus();
                return true;
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                mainActivity.newsPricesViewModel.setPosition(position);
               if(!mainActivity.newsPricesViewModel.getAsset().isEmpty()) {
                    progressBarSearch.setVisibility(View.VISIBLE);
                    candleEntryArrayList = new ArrayList<>();
                    chartDataRepository.start(mainActivity.newsPricesViewModel.getAsset(), intervals[position] , periods[position]);
               }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    public void setChartStyle()
    {

        //searchview
        int id = searchView.getContext()
                .getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        editText = (EditText) searchView.findViewById(id);
        editText.setTextSize(20);
        editText.setHintTextColor(ResourcesCompat.getColor(getResources(), R.color.grey, null));
        editText.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //chart
        YAxis yAxisRight = chartPrices.getAxisRight();
        YAxis yAxisLeft = chartPrices.getAxisLeft();
        XAxis xAxis = chartPrices.getXAxis();
        Legend l = chartPrices.getLegend();

        l.setEnabled(false);
        chartPrices.setDrawBorders(false);
        chartPrices.setDescription(null);
        chartPrices.setNoDataText("");
        chartPrices.setNoDataTextColor(ResourcesCompat.getColor(getResources(), R.color.red, null));

        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setDrawLabels(true);
        yAxisLeft.setTextColor(R.color.white);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setTextColor(ResourcesCompat.getColor(getResources(), R.color.grey, null));

        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawAxisLine(false);

        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        xAxis.setDrawAxisLine(false);

        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAvoidFirstLastClipping(true);

        chartPrices.setDragEnabled(true);
        chartPrices.setScaleEnabled(true);
        chartPrices.setNoDataTextColor(ResourcesCompat.getColor(getResources(), R.color.grey, null));
        

    }


    @Override
    public void onResponse(List<Statistic> eleStatistic) {

        if(eleStatistic != null) {
            mainActivity.newsPricesViewModel.getStatisticList().clear();
            mainActivity.newsPricesViewModel.getStatisticList().addAll(eleStatistic);
            populateStatistics();
        }
    }


    @Override
    public void onResponsePrices(List<StockPrice> pricesList) {

        if(pricesList != null) {
            mainActivity.newsPricesViewModel.getPriceList().clear();
            mainActivity.newsPricesViewModel.getPriceList().addAll(pricesList);
            if(!asset.isEmpty())
                mainActivity.newsPricesViewModel.setAsset(asset);
            populateChart();
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        asset = mainActivity.newsPricesViewModel.getAsset();
        progressBarSearch.setVisibility(View.INVISIBLE);
        Toast.makeText(getContext(), "Error. "+ errorMessage,Toast.LENGTH_LONG).show();
    }

    private double calculateReturn(double openPrice, double closePrice)
    {
        return closePrice/openPrice - 1;
    }


    private void populateChart()
    {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mainActivity.newsPricesViewModel.getPriceList().size(); i++)
                {
                    candleEntryArrayList.add(new CandleEntry(i, (float)mainActivity.newsPricesViewModel.getPriceList().get(i).getHigh(), (float)mainActivity.newsPricesViewModel.getPriceList().get(i).getLow(),
                            (float)mainActivity.newsPricesViewModel.getPriceList().get(i).getOpen(), (float)mainActivity.newsPricesViewModel.getPriceList().get(i).getClose()));
                }

                CandleDataSet set1 = new CandleDataSet(candleEntryArrayList, "DataSet 1");

                set1.setShadowColor(ResourcesCompat.getColor(getResources(), R.color.grey, null));
                set1.setShadowWidth(0.8f);
                set1.setDecreasingColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
                set1.setDecreasingPaintStyle(Paint.Style.FILL);
                set1.setIncreasingColor(ResourcesCompat.getColor(getResources(), R.color.lightgreen, null));
                set1.setIncreasingPaintStyle(Paint.Style.FILL);
                set1.setNeutralColor(ResourcesCompat.getColor(getResources(), R.color.grey, null));
                set1.setDrawValues(false);

                CandleData data = new CandleData(set1);

                chartPrices.setData(data);
                chartPrices.invalidate();

                float returnValue = Math.round(calculateReturn(mainActivity.newsPricesViewModel.getPriceList().get(0).getOpen(), mainActivity.newsPricesViewModel.getPriceList().get(mainActivity.newsPricesViewModel.getPriceList().size() - 1).getClose()) * 10000)/100F;

                String ret = "";
                if (returnValue > 0)
                {
                    stockReturn.setTextColor(ResourcesCompat.getColor(getResources(), R.color.lightgreen, null));
                    ret = "+" + returnValue + "%";
                }
                else if(returnValue < 0)
                {
                    stockReturn.setTextColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
                    ret = returnValue + "%";
                }
                else {
                    stockReturn.setTextColor(ResourcesCompat.getColor(getResources(), R.color.grey, null));
                    ret = returnValue + "%";
                }
                stockReturn.setText(ret);
                String actual = String.valueOf(Math.round(mainActivity.newsPricesViewModel.getPriceList().get(mainActivity.newsPricesViewModel.getPriceList().size() - 1).getClose()*100)/100F) + "$";
                actualStockPrice.setText(actual);
                stockName.setText(mainActivity.newsPricesViewModel.getAsset().toUpperCase());
                progressBarSearch.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void populateStatistics()
    {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statisticsListViewAdapter.notifyDataSetChanged();
            }
        });
    }
}