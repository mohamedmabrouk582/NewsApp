package comnewsapp.example.moham.newsapp.di.modules;

import javax.inject.Singleton;

import comnewsapp.example.moham.newsapp.app.MyApp;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private MyApp myApp;

    public AppModule(MyApp myApp) {
        this.myApp = myApp;
    }

    @Singleton
    @Provides
    public MyApp getMyApp() {
        return myApp;
    }
}
