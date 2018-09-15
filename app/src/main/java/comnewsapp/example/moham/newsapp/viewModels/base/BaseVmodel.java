package comnewsapp.example.moham.newsapp.viewModels.base;

import comnewsapp.example.moham.newsapp.views.BaseView;

public interface BaseVmodel<v extends BaseView>  {
    void attachView(v view);
}
