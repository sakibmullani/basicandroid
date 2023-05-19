package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler_horizontal extends RecyclerView.Adapter<Recycler_horizontal.ViewHolder2> {

    ArrayList<ContactModel>arrContact2;
    Context context;

    Recycler_horizontal (Context context,ArrayList<ContactModel>arrContact2){
        this.context=context;
        this.arrContact2=arrContact2;
    }

    @NonNull
    @Override
    public Recycler_horizontal.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view2= LayoutInflater.from(context).inflate(R.layout.contact_row_recycler_2,parent,false);
        ViewHolder2 viewHolder2=new ViewHolder2(view2);

        return viewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_horizontal.ViewHolder2 holder, int position) {

        final ContactModel alldata = arrContact2.get(position);
        //horizontal view
        holder.imageContact2.setImageResource(arrContact2.get(position).image);
        holder.textName2.setText(arrContact2.get(position).name);
        holder.contact_number2.setText(arrContact2.get(position).number);

        holder.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2= new Intent(view.getContext(), RecyclerShowALLData.class);
                intent2.putExtra("name",alldata.getName());
                intent2.putExtra("image",alldata.getImage());
                intent2.putExtra("number",alldata.getNumber());

                view.getContext().startActivity(intent2);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrContact2.size();
    }


    public class ViewHolder2 extends RecyclerView.ViewHolder {

        TextView textName2,contact_number2;
        ImageView imageContact2;
        CardView cardView2;


        public ViewHolder2(@NonNull View itemView) {
            super(itemView);

            textName2=itemView.findViewById(R.id.textName2);
            contact_number2=itemView.findViewById(R.id.contact_number2);
            imageContact2=itemView.findViewById(R.id.imageContact2);
            cardView2=itemView.findViewById(R.id.MaincardView2);
        }
    }
}