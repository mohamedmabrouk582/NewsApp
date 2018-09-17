package comnewsapp.example.moham.newsapp.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import comnewsapp.example.moham.newsapp.BR;
import comnewsapp.example.moham.newsapp.base.BaseModel;

public class Day extends BaseObservable implements BaseModel {
    private String header;

    public Day(String header) {
        this.header = header;
    }

    @Bindable
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
        notifyPropertyChanged(BR.header);
    }

    @Override
    public int getViewType() {
        return 0;
    }
}
