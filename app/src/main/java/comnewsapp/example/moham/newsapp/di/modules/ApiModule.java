package comnewsapp.example.moham.newsapp.di.modules;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import comnewsapp.example.moham.newsapp.api.NewsApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    private String baseUrl="https://newsapi.org/v2/";


    @Singleton
    @Provides
    public NewsApi getApi(Retrofit retrofit){
        return retrofit.create(NewsApi.class);
    }

    @Singleton
    @Provides
    Retrofit getRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient getClient(HttpLoggingInterceptor interceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor getInterceptor(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
