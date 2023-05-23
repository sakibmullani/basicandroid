package com.example.myapplication.api_calling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    List<responseModel> data;

    public myAdapter(List<responseModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.show_single_data,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.titleId.setText(data.get(position).getTitle());
        holder.idNumber.setText(data.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView idNumber, titleId;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idNumber=itemView.findViewById(R.id.idNumber);
            titleId=itemView.findViewById(R.id.titleId);
        }
    }
}
