package comnewsapp.example.moham.newsapp.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseHolder<t> extends RecyclerView.ViewHolder{

    public BaseHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(t model);
}
