package comnewsapp.example.moham.newsapp.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import comnewsapp.example.moham.newsapp.data.db.NewsDao;
import comnewsapp.example.moham.newsapp.data.db.NewsDb;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private NewsDb mNewsDb;

    public RoomModule(Application application) {
     mNewsDb= Room.databaseBuilder(application.getApplicationContext(),NewsDb.class,"news").build();
    }

    @Singleton
    @Provides
    public NewsDb getmNewsDb() {
        return mNewsDb;
    }

    @Singleton
    @Provides
    public NewsDao getDao(NewsDb db){
        return db.getNewsDao();
    }
}
