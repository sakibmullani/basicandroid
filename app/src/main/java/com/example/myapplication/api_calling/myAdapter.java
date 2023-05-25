package com.example.myapplication.api_calling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<responseModel> dataList;
    private OnDeleteClickListener onDeleteClickListener;

    public myAdapter(List<responseModel> dataList, OnDeleteClickListener onDeleteClickListener) {
        this.dataList = dataList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_single_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleId.setText(dataList.get(position).getTitle());
        holder.idNumber.setText(String.valueOf(dataList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView idNumber;
        private TextView titleId;
        private ImageView deleteIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idNumber = itemView.findViewById(R.id.idNumber);
            titleId = itemView.findViewById(R.id.titleId);
            deleteIcon = itemView.findViewById(R.id.delete_API);

            deleteIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.delete_API) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClickListener.onDeleteClick(position);
                }
            }
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}