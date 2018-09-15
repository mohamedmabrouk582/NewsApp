package comnewsapp.example.moham.newsapp.data.model;

import android.arch.persistence.room.Ignore;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import comnewsapp.example.moham.newsapp.BR;

public class Source extends BaseObservable{
    @NonNull
    private String id;
    @NonNull
    private String name;

    @Ignore
    @Override
    public String toString() {
        return "Source{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
