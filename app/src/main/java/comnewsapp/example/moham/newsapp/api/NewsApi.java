package comnewsapp.example.moham.newsapp.api;

import java.util.List;

import comnewsapp.example.moham.newsapp.data.model.News;
import comnewsapp.example.moham.newsapp.data.model.NewsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("everything?sources=usa-today&q=google&apiKey=91e82f0b9a834fc58c040b731536fe70&language=en")
    Observable<NewsResponse> getNews(@Query("page") int page);
}
