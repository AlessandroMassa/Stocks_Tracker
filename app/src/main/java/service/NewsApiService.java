package service;

import com.google.gson.JsonObject;
import com.massa844853.stockstracker.models.ChartDataResponse;
import com.massa844853.stockstracker.models.NewsResponse;
import com.massa844853.stockstracker.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

//interfaccia per ottenere le news di mercato dal servizio rapidapi

public interface NewsApiService {
   @Headers({Constants.API_KEY,
            Constants.API_HOST})
    @GET(Constants.NEWS_ENDPOINT)
    Call<NewsResponse> getNews(
        @Query("category") String category,
        @Query("region") String region);
}
