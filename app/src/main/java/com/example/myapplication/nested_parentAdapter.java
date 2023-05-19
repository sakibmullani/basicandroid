package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class nested_parentAdapter extends RecyclerView.Adapter<nested_parentAdapter.ViewHolder> {

    List<nestedRV_parentModelClass>nestedRV_parentModelClassList;
    Context context;
    Boolean isMovie=false;
    Boolean isLatedMovie2=false;

    public nested_parentAdapter(ArrayList<nestedRV_parentModelClass> nestedRV_parentModelClassList, Context context) {
        this.nestedRV_parentModelClassList = nestedRV_parentModelClassList;
        this.context = context;
        this.isMovie=isMovie;
        this.isLatedMovie2=isLatedMovie2;
    }

    @NonNull
    @Override
    public nested_parentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.nested_parent_rv_showlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull nested_parentAdapter.ViewHolder holder, int position) {

        holder.tv_parent_title.setText(nestedRV_parentModelClassList.get(position).getTitle());

        if(nestedRV_parentModelClassList.get(position).getTitle()=="Movies" || nestedRV_parentModelClassList.get(position).getTitle()=="Latest Movies2") {

            isMovie = true;
            isLatedMovie2=true;
        }
        else {
            isMovie = false;
            isLatedMovie2=false;

        }
        nested_childAdapter nested_childAdapters =new nested_childAdapter(nestedRV_parentModelClassList.get(position).nestedRV_childModelClasses,context,isMovie,isLatedMovie2);

        holder.rv_child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

         holder.rv_child.setAdapter(nested_childAdapters);
         nested_childAdapters.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return nestedRV_parentModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rv_child;
        TextView tv_parent_title;
        LinearLayout linearLayout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rv_child=itemView.findViewById(R.id.rv_child);
            tv_parent_title=itemView.findViewById(R.id.tv_parent_title);
            linearLayout=itemView.findViewById(R.id.linear_parentID);

        }
    }
}
