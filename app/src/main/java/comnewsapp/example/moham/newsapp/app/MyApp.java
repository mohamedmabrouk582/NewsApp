package comnewsapp.example.moham.newsapp.app;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.FragmentActivity;

import comnewsapp.example.moham.newsapp.di.comonents.AppComponent;
import comnewsapp.example.moham.newsapp.di.comonents.DaggerAppComponent;
import comnewsapp.example.moham.newsapp.di.modules.ApiModule;
import comnewsapp.example.moham.newsapp.di.modules.AppModule;
import comnewsapp.example.moham.newsapp.di.modules.RoomModule;

public class MyApp extends Application {
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                 .roomModule(new RoomModule(this))
                .build();
        appComponent.inject(this);
    }

    public static MyApp get(Activity activity){
        return (MyApp) activity.getApplication();
    }

    public static MyApp get(FragmentActivity  activity){
        return (MyApp) activity.getApplication();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
