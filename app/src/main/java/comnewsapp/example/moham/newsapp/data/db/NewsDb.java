package comnewsapp.example.moham.newsapp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import comnewsapp.example.moham.newsapp.data.model.News;

@Database(entities = News.class,version = 1,exportSchema = false)
public abstract class NewsDb extends RoomDatabase{
    public abstract NewsDao getNewsDao();
}
