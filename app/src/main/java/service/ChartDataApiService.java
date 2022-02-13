package service;

import com.massa844853.stockstracker.models.ChartDataResponse;
import com.massa844853.stockstracker.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ChartDataApiService {
    @Headers({Constants.API_KEY,
            Constants.API_HOST})
    @GET(Constants.CHART_DATA_ENDPOINT)
    Call<ChartDataResponse> getChartData(
            @Query("interval") String interval,
            @Query("symbol") String symbol,
            @Query("range") String range);
}
