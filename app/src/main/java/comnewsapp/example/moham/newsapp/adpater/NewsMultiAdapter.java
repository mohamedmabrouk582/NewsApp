package comnewsapp.example.moham.newsapp.adpater;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import comnewsapp.example.moham.newsapp.R;
import comnewsapp.example.moham.newsapp.base.BaseHolder;
import comnewsapp.example.moham.newsapp.base.BaseModel;
import comnewsapp.example.moham.newsapp.data.model.Day;
import comnewsapp.example.moham.newsapp.data.model.News;
import comnewsapp.example.moham.newsapp.databinding.DayItemViewBinding;
import comnewsapp.example.moham.newsapp.databinding.NewsItemViewBinding;
import comnewsapp.example.moham.newsapp.BR;
import comnewsapp.example.moham.newsapp.viewModels.base.BaseVmodel;

public class NewsMultiAdapter extends RecyclerView.Adapter<BaseHolder>{
    public interface newsListener{
        void NewsClick(News news,int pos);
        void likeClick(News news);
    }
    private newsListener listener;
    private List<? extends BaseModel> data;

    public NewsMultiAdapter(newsListener listener) {
        data=new ArrayList<>();
        this.listener=listener;
    }

    public List<? extends BaseModel> getData(){
        return  data;
    }

    public <t extends BaseModel>void addItems(List<t> data){
        data.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(List<? extends BaseModel> data){
        this.data=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case 0:
                DayItemViewBinding itemViewBinding=DataBindingUtil.inflate(inflater,R.layout.day_item_view,parent,false);
                return new DayHolder(itemViewBinding);
            case 1:
                NewsItemViewBinding viewBinding=DataBindingUtil.inflate(inflater,R.layout.news_item_view,parent,false);
                return new NewsHolder(viewBinding);
                default:
                    return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
       holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getViewType();
    }

    public class DayHolder extends BaseHolder<Day>{
     private DayItemViewBinding viewBinding;
     public DayHolder(DayItemViewBinding itemView) {
         super(itemView.getRoot());
         viewBinding=itemView;
     }
     @Override
     public void bind(Day model) {
      viewBinding.setVariable(BR.header,model);
      viewBinding.executePendingBindings();
     }
 }

 public class NewsHolder extends BaseHolder<News>{
     private NewsItemViewBinding viewBinding;
     public NewsHolder(NewsItemViewBinding itemView) {
         super(itemView.getRoot());
         viewBinding=itemView;
     }

     @Override
     public void bind(News model) {
         viewBinding.setVariable(BR.news,model);
         viewBinding.executePendingBindings();
         viewBinding.like.setOnClickListener(e ->{
             listener.likeClick(model);
         });
         viewBinding.getRoot().setOnClickListener(e ->{
            //   model.setFav(!model.isFav());
               listener.NewsClick(model,getAdapterPosition());
         });
     }
 }
}
