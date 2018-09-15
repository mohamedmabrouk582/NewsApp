package comnewsapp.example.moham.newsapp.ui;

import comnewsapp.example.moham.newsapp.R;
import comnewsapp.example.moham.newsapp.base.activity.BaseActivity;

public class NewsListActivity extends BaseActivity {
    @Override
    public int setContentView() {
        return R.layout.container;
    }

    @Override
    public void iniViews() {
     getSupportFragmentManager().beginTransaction().add(R.id.Fragment_Container,new NewsListFragment()).commit();
    }
}
