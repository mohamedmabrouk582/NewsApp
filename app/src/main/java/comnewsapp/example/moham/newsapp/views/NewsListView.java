package comnewsapp.example.moham.newsapp.views;

import java.util.List;

import comnewsapp.example.moham.newsapp.data.model.News;

public interface NewsListView extends BaseView {
    void error(String msg);
    void loadData(List<News> news);
    void pagingData(List<News> news);
}
