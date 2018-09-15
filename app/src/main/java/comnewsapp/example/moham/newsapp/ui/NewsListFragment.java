package comnewsapp.example.moham.newsapp.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import comnewsapp.example.moham.newsapp.R;
import comnewsapp.example.moham.newsapp.adpater.NewsAdapter;
import comnewsapp.example.moham.newsapp.api.NewsApi;
import comnewsapp.example.moham.newsapp.app.MyApp;
import comnewsapp.example.moham.newsapp.base.fragment.RequestFragment;
import comnewsapp.example.moham.newsapp.data.db.NewsDao;
import comnewsapp.example.moham.newsapp.data.model.News;
import comnewsapp.example.moham.newsapp.databinding.NewsListsLayoutBinding;
import comnewsapp.example.moham.newsapp.di.comonents.DaggerNewsListComponent;
import comnewsapp.example.moham.newsapp.di.modules.NewsListModule;
import comnewsapp.example.moham.newsapp.viewModels.newsList.NewsListViewModel;
import comnewsapp.example.moham.newsapp.views.NewsListView;

public class NewsListFragment extends RequestFragment implements NewsListView, NewsAdapter.NewsListener {
    private NewsListsLayoutBinding layoutBinding;
    @Inject public NewsApi api;
    @Inject public NewsDao dao;
    @Inject public ViewModelProvider.Factory factory;
    private NewsListViewModel viewModel;
    private NewsAdapter adapter;
    @Override
    public int SetContentView() {
        return R.layout.news_lists_layout;
    }

    @Override
    public Themes LoaderThemes() {
        return Themes.ChromeFloatingCirclesDrawable;
    }

    @Override
    public void iniViews() {
        setHasOptionsMenu(true);
     layoutBinding= DataBindingUtil.bind(content);
     layoutBinding.newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DaggerNewsListComponent.builder()
                .appComponent(MyApp.get(getActivity()).getAppComponent())
                .newsListModule(new NewsListModule(this))
                .build().inject(this);
        viewModel= ViewModelProviders.of(this,factory).get(NewsListViewModel.class);
        viewModel.attachView(this);
        adapter=new NewsAdapter();
        adapter.setListener(this);
       // showLoader();
        reqData();
        viewModel.layoutManager= (LinearLayoutManager) layoutBinding.newsRecyclerView.getLayoutManager();
        layoutBinding.newsRecyclerView.setAdapter(adapter);
        layoutBinding.setNewsList(viewModel);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.allNews:
                reqData();
                return true;
            case R.id.makeFavorites:
                viewModel.insertAllNews(adapter.getData());
                return true;
            case R.id.Favorites:
                viewModel.favoritesNews();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRetry() {
           reqData();
    }

    private void reqData(){
     showLoader();
     viewModel.reqAllNews(1);
    }

    @Override
    public void error(String msg) {
      showErrorView(msg);
    }

    @Override
    public void loadData(List<News> news) {
        Log.d("news",news.toString());
       showContent();
      adapter.setData(news);
    }

    @Override
    public void pagingData(List<News> news) {
        adapter.addItems(news);
    }

    @Override
    public void onClick(News item, int pos) {
        Uri uri = Uri.parse(item.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
