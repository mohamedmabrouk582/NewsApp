package comnewsapp.example.moham.newsapp.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import comnewsapp.example.moham.newsapp.BR;
import comnewsapp.example.moham.newsapp.R;
import comnewsapp.example.moham.newsapp.base.BaseModel;


@Entity(tableName = "news",primaryKeys = {"author","title"})
public class News extends BaseObservable implements BaseModel{
    @Embedded
    private Source source;
    @NonNull
    private String author;
    @NonNull
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    public String publishedAt;
    private String content;
    @Ignore
    private boolean isFav;

    @Bindable
    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
        notifyPropertyChanged(BR.fav);
    }

    @Ignore
    @Override
    public String toString() {
        return "News{" +
                "source=" + source +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Ignore
    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view,String urlToImage){
        Glide.with(view.getContext())
        .load(urlToImage)
        .apply(RequestOptions.placeholderOf(R.drawable.newsplaceholder))
        .into(view);
    }

//    @Ignore
//    @BindingAdapter("dateFormat")
//    public static void dateFormat(TextView textView,String publishedAt){
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
//        try {
//            Date date = format1.parse(publishedAt);
//            textView.setText("Time : "+format2.format(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    @Bindable
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
        notifyPropertyChanged(BR.source);
    }

    @Bindable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        notifyPropertyChanged(BR.author);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        notifyPropertyChanged(BR.urlToImage);
    }

    @Bindable
    public String getPublishedAt() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
        try {
            Date date = format1.parse(publishedAt);
            return format2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        notifyPropertyChanged(BR.publishedAt);
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }

    @Override
    public int getViewType() {
        return 1;
    }
}
