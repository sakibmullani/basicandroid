package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Pagination_Adapter extends RecyclerView.Adapter<Pagination_Adapter.ViewHolder> {

    ArrayList<String>data;
    Context context;

    public Pagination_Adapter(ArrayList<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Pagination_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.pagination_item,parent,false);
        return new Pagination_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pagination_Adapter.ViewHolder holder, int position) {

        holder.textItem.setText(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textItem=itemView.findViewById(R.id.textItem);


        }
    }
}
