package comnewsapp.example.moham.newsapp.base.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import comnewsapp.example.moham.newsapp.R;


public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    public View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(setContentView(),container,false);
        iniLoad();
        viewReady(savedInstanceState);
        iniViews();
        return view;
    }

    public void replaceFragment (Fragment fragment, @IdRes int id){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(id, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    public <t extends View> t bind(@IdRes int id){
        return view.findViewById(id);
    }

    public void click(@IdRes int ... ids){
        for (int id:ids) {
            bind(id).setOnClickListener(this);
        }
    }

    public void click(@Nullable View ... views){
        for (View view:views) {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) { }

    public  void viewReady(Bundle bundle){}
    public abstract int setContentView();
    public abstract void iniViews();

    public void iniLoad(){}

   public void showSnackBar(String msg,int image){
        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

        Snackbar.SnackbarLayout layout= (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView =layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View snackView = LayoutInflater.from(getActivity()).inflate(R.layout.snackbar_view, null,false);
        ImageView imageView =  snackView.findViewById(R.id.imageView);
        imageView.setImageResource(image);
        TextView textViewTop = snackView.findViewById(R.id.txt);
        textViewTop.setText(msg);
        textViewTop.setTextColor(Color.WHITE);
        layout.setPadding(0,0,0,0);
        layout.addView(snackView, 0);

        snackbar.show();
    }
}
