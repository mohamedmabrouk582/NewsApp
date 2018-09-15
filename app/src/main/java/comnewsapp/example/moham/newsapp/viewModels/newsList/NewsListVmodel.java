package comnewsapp.example.moham.newsapp.viewModels.newsList;

import comnewsapp.example.moham.newsapp.viewModels.base.BaseVmodel;
import comnewsapp.example.moham.newsapp.views.NewsListView;

public interface NewsListVmodel<v extends NewsListView> extends BaseVmodel<v> {
    void reqAllNews(int page);
}
