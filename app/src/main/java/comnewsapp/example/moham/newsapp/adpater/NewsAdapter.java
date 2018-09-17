package comnewsapp.example.moham.newsapp.adpater;

import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comnewsapp.example.moham.newsapp.R;
import comnewsapp.example.moham.newsapp.databinding.NewsItemViewBinding;
import comnewsapp.example.moham.newsapp.BR;
import comnewsapp.example.moham.newsapp.data.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {
    int pos;

    private List<News> data;

    public interface NewsListener {
        void onClick(News item, int pos);
    }

    private NewsListener listener;

    public NewsAdapter() {
        data = new ArrayList<>();
    }

    public void setListener(NewsListener listener) {
        this.listener = listener;
    }

    public void setData(List<News> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addItem(News item) {
        data.add(item);
        // notifyDataSetChanged();
        notifyItemInserted(data.size() - 1);
    }

    public void addItems(List<News> news){
        data.addAll(news);
        notifyDataSetChanged();
    }

    public List<News> getData() {
        return data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item_view, parent, false);
        return new Holder(binding);
    }

    public int currentPos() {
        return pos;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        pos = position;
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        NewsItemViewBinding itemViewBinding;

        public Holder(NewsItemViewBinding itemViewBinding) {
            super(itemViewBinding.getRoot());
            this.itemViewBinding = itemViewBinding;

        }

        public void bind(News product) {
            itemViewBinding.setVariable(BR.news, product);
            itemViewBinding.executePendingBindings();
            itemViewBinding.getRoot().setOnClickListener((v) ->
                    listener.onClick(product, getAdapterPosition())
            );
        }
    }
}