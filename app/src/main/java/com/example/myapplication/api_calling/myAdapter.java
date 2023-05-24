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

    private List<responseModel> data;
    private OnDeleteClickListener onDeleteClickListener;

    public myAdapter(List<responseModel> data) {
        this.data = data;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
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
        responseModel item = data.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView idNumber, titleId;
        private ImageView deleteSymbol;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idNumber = itemView.findViewById(R.id.idNumber);
            titleId = itemView.findViewById(R.id.titleId);
            deleteSymbol = itemView.findViewById(R.id.delete_API);
        }

        public void bind(responseModel item) {
            idNumber.setText(item.getId());
            titleId.setText(item.getTitle());

            deleteSymbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onDeleteClickListener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}