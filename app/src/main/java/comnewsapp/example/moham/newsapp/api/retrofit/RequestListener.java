package comnewsapp.example.moham.newsapp.api.retrofit;

import android.arch.lifecycle.LiveData;

public interface RequestListener<data> {

    void onResponse(LiveData<data> data);
    void onEmptyData(String msg);

    void onSessionExpired(String msg);

    void onResponseError(String msg);

    void onNetWorkError(String msg);
}
