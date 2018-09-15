package comnewsapp.example.moham.newsapp.viewModels.newsList;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import comnewsapp.example.moham.newsapp.data.model.NewsResponse;
import comnewsapp.example.moham.newsapp.ui.NewsListFragment;
import comnewsapp.example.moham.newsapp.R;
import comnewsapp.example.moham.newsapp.api.NewsApi;
import comnewsapp.example.moham.newsapp.api.retrofit.Request;
import comnewsapp.example.moham.newsapp.api.retrofit.RequestListener;
import comnewsapp.example.moham.newsapp.data.db.NewsDao;
import comnewsapp.example.moham.newsapp.data.model.News;
import comnewsapp.example.moham.newsapp.viewModels.base.BaseViewModel;
import comnewsapp.example.moham.newsapp.views.NewsListView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class NewsListViewModel<v extends NewsListView> extends BaseViewModel<v> implements NewsListVmodel<v> {
    private NewsListFragment activity;
    private NewsApi api;
    private NewsDao newsDao;
    private final int VISIBLE_THRESHOLD = 1;
    private int page=1;
    private ObservableBoolean loading=new ObservableBoolean(false);
    public ObservableBoolean isPaging=new ObservableBoolean(true);
    private PublishSubject<Integer> paginator = PublishSubject.create();
    private int lastVisibleItem, totalItemCount;
    public LinearLayoutManager layoutManager;

    public NewsListViewModel(@NonNull NewsListFragment activity, NewsApi api, NewsDao newsDao) {
        super(activity.getActivity().getApplication());
        this.activity=activity;
        this.api=api;
        this.newsDao=newsDao;
        subscribeData();
    }

    public ObservableBoolean getIsPaging() {
        return isPaging;
    }

    public ObservableBoolean getLoading() {
        return loading;
    }

    @BindingAdapter({"OnScrollListener","IsPaging"})
    public static void OnScrollListener(RecyclerView view,RecyclerView.OnScrollListener listener,boolean isPaging){
        if (isPaging){
            view.addOnScrollListener(listener);
        }else {
            view.removeOnScrollListener(listener);
        }
    }

    public RecyclerView.OnScrollListener listener=new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            totalItemCount=layoutManager.getItemCount();
            lastVisibleItem=layoutManager.findLastVisibleItemPosition();
            Log.d("totalItemCount",totalItemCount+" : "+lastVisibleItem);
            if (!loading.get() && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)){
                paginator.onNext(++page);
            }
    }};

    @SuppressLint("CheckResult")
    public void subscribeData(){
        paginator.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).
                subscribe(new io.reactivex.functions.Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        loading.set(true);
                        paging(integer);
                    }
                });
    }


    @Override
    public void reqAllNews(int page) {
        isPaging.set(true);
        api.getNews(page).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Request<>(activity.getContext(), new RequestListener<NewsResponse>() {
                    @Override
                    public void onResponse(LiveData<NewsResponse> data) {
                        data.observe(activity,e ->{
                            Log.d("aadad",e.toString());
                            if (e.getArticles()==null ||e.getArticles().size()==0){
                                onEmptyData(activity.getString(R.string.no_data));
                            }else {
                              getView().loadData(e.getArticles());
                            }
                        });
                    }

                    @Override
                    public void onEmptyData(String msg) {
                      getView().error(msg);
                    }

                    @Override
                    public void onSessionExpired(String msg) {
                        getView().error(msg);
                    }

                    @Override
                    public void onResponseError(String msg) {
                        getView().error(msg);
                    }

                    @Override
                    public void onNetWorkError(String msg) {
                        getView().error(msg);
                    }
                }));
    }
    public void insertAllNews(List<News> news){
        new InsertAllNews().execute(news);
    }

    public void favoritesNews(){
        isPaging.set(false);
        newsDao.getNews().observe(activity, e->{
                    Log.d("adadadad",e.toString());
                    getView().loadData(e);
                }
        );
    }
    private void paging(int page){
        api.getNews(page).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Request<>(activity.getContext(), new RequestListener<NewsResponse>() {
                    @Override
                    public void onResponse(LiveData<NewsResponse> data) {
                        data.observe(activity,e ->{
                            Log.d("aadad",e.toString());
                            if (e.getArticles()==null ||e.getArticles().size()==0){
                                onEmptyData(activity.getString(R.string.no_data));
                            }else {
                                loading.set(false);
                                getView().pagingData(e.getArticles());
                            }
                        });
                    }

                    @Override
                    public void onEmptyData(String msg) {
                        loading.set(false);
                       // getView().error(msg);
                    }

                    @Override
                    public void onSessionExpired(String msg) {
                        loading.set(false);
                       // getView().error(msg);
                    }

                    @Override
                    public void onResponseError(String msg) {
                        loading.set(false);
                      //  getView().error(msg);
                    }

                    @Override
                    public void onNetWorkError(String msg) {
                        loading.set(false);
                       // getView().error(msg);
                    }
                }));
    }

    public Long insertNews(News news) throws ExecutionException, InterruptedException {
        return new InsertNews().execute(news).get();
    }
    public class InsertNews extends AsyncTask<News,Void,Long>{
        @Override
        protected Long doInBackground(News... news) {
            return newsDao.insertNews(news[0]);
        }
    }

    public class InsertAllNews extends AsyncTask<List<News>,Void,Void>{

        @Override
        protected Void doInBackground(List<News>... lists) {
            newsDao.insertAllNews(lists[0]);
            return null;
        }
    }
    public static class NewsListViewModelFactory implements ViewModelProvider.Factory{
        private NewsListFragment activity;
        private NewsApi api;
        private NewsDao newsDao;

        public NewsListViewModelFactory(NewsListFragment activity, NewsApi api, NewsDao newsDao) {
            this.activity = activity;
            this.api = api;
            this.newsDao = newsDao;
        }

        @NonNull
        @Override
        public NewsListViewModel  create(@NonNull Class modelClass) {
            return new NewsListViewModel(activity,api,newsDao);
        }
    }
}
