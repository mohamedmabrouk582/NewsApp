package comnewsapp.example.moham.newsapp.di.comonents;

import javax.inject.Singleton;

import comnewsapp.example.moham.newsapp.api.NewsApi;
import comnewsapp.example.moham.newsapp.app.MyApp;
import comnewsapp.example.moham.newsapp.data.db.NewsDao;
import comnewsapp.example.moham.newsapp.di.modules.ApiModule;
import comnewsapp.example.moham.newsapp.di.modules.AppModule;
import comnewsapp.example.moham.newsapp.di.modules.RoomModule;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, RoomModule.class})
public interface AppComponent {
    NewsApi getNewsApi();
    NewsDao getNewsDao();
    void inject(MyApp app);
}
