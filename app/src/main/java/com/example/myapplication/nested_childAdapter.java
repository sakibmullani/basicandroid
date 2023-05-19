package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class nested_childAdapter extends RecyclerView.Adapter<nested_childAdapter.ViewHolder> {

    List<nestedRV_childModelClass>nestedRV_childModelClasses;
    Context context;
    Boolean isMovie=false;
    Boolean isLatedMovie2=false;


    public nested_childAdapter(List<nestedRV_childModelClass> nestedRV_childModelClasses, Context context, Boolean isMovie, Boolean isLatedMovie2) {
        this.nestedRV_childModelClasses = nestedRV_childModelClasses;
        this.context = context;
        this.isMovie=isMovie;

    }

    @NonNull
    @Override
    public nested_childAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.nested_child_poster_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull nested_childAdapter.ViewHolder holder, int position) {

        if (isMovie==true || isLatedMovie2==true) {

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(720, 470);
//            FrameLayout.LayoutParams layoutParamsImage = new FrameLayout.LayoutParams(1200, 470);
//            layoutParams.setMarginStart(100);
//            holder.iv_child_image.setLayoutParams(layoutParams);
            holder.cardView.setLayoutParams(layoutParams);
//            holder.iv_child_image.setLayoutParams(layoutParamsImage);
            holder.iv_child_image.setImageResource(nestedRV_childModelClasses.get(position).getImage());

        }
        else {
            holder.iv_child_image.setImageResource(nestedRV_childModelClasses.get(position).getImage());
        }

    }


    @Override
    public int getItemCount() {
        return nestedRV_childModelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView iv_child_image;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_child_image=itemView.findViewById(R.id.iv_child_item);
            cardView=itemView.findViewById(R.id.cv_child_item);


        }
    }
}
