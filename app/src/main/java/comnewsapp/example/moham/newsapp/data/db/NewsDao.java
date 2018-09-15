package comnewsapp.example.moham.newsapp.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import comnewsapp.example.moham.newsapp.data.model.News;

@Dao
public interface NewsDao {
    @Query("select * from news")
    LiveData<List<News>> getNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertNews(News news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNews(List<News> news);

    @Delete
    int deleteNews(News news);

    @Query("DELETE from news")
    void deleteTable();
}
