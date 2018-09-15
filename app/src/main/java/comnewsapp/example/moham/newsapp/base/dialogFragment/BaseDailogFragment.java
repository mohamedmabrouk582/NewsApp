package comnewsapp.example.moham.newsapp.base.dialogFragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import comnewsapp.example.moham.newsapp.R;


public abstract class BaseDailogFragment extends DialogFragment implements View.OnClickListener {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    public View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view= LayoutInflater.from(getContext()).inflate(setContentView(),null,false);
        builder=new AlertDialog.Builder(getContext());
        builder.setView(view);
        dialog=builder.create();
        iniLoad();
        viewReady(savedInstanceState);
        iniViews();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

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
    public  void viewReady(Bundle bundle){}

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
    public void onClick(View v) {

    }

    @LayoutRes
    public abstract int setContentView();
    public abstract void iniViews();
    public void iniLoad(){}
}
