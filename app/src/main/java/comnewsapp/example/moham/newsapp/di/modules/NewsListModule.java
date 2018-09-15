package comnewsapp.example.moham.newsapp.di.modules;

import android.arch.lifecycle.ViewModelProvider;

import comnewsapp.example.moham.newsapp.ui.NewsListFragment;
import comnewsapp.example.moham.newsapp.api.NewsApi;
import comnewsapp.example.moham.newsapp.data.db.NewsDao;
import comnewsapp.example.moham.newsapp.di.scopes.NewsScope;
import comnewsapp.example.moham.newsapp.viewModels.newsList.NewsListViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class NewsListModule {
    private NewsListFragment activity;

    public NewsListModule(NewsListFragment activity) {
        this.activity = activity;
    }

    @NewsScope
    @Provides
    public ViewModelProvider.Factory getFactory(NewsApi api, NewsDao dao){
        return new NewsListViewModel.NewsListViewModelFactory(activity,api,dao);
    }

    @NewsScope
    @Provides
    public NewsListFragment getActivity() {
        return activity;
    }
}
