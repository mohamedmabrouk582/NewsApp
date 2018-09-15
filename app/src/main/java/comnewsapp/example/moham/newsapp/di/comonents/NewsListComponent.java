package comnewsapp.example.moham.newsapp.di.comonents;

import comnewsapp.example.moham.newsapp.ui.NewsListFragment;
import comnewsapp.example.moham.newsapp.di.modules.NewsListModule;
import comnewsapp.example.moham.newsapp.di.scopes.NewsScope;
import dagger.Component;

@NewsScope
@Component(dependencies = AppComponent.class,modules = NewsListModule.class)
public interface NewsListComponent {
    void inject(NewsListFragment activity);
}
