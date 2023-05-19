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

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactModel>arrContact;


    RecyclerContactAdapter (Context context, ArrayList<ContactModel>arrContact){
        this.context=context;
        this.arrContact=arrContact;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(context).inflate(R.layout.contact_row_recycler,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);


//        viewHolder.setIsRecyclable(false);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ContactModel alldata = arrContact.get(position);

        holder.imageContact.setImageResource(arrContact.get(position).image);
        holder.textName.setText(arrContact.get(position).name);
        holder.contact_number.setText(arrContact.get(position).number);


//        holder.setIsRecyclable(false);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(view.getContext(), RecyclerShowALLData.class);
                intent.putExtra("name",alldata.getName());
                intent.putExtra("image",alldata.getImage());
                intent.putExtra("number",alldata.getNumber());

               view.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount()

    {
        return arrContact.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName,contact_number;
        ImageView imageContact;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName=itemView.findViewById(R.id.textName);
            contact_number=itemView.findViewById(R.id.contact_number);
            imageContact=itemView.findViewById(R.id.imageContact);
            cardView=itemView.findViewById(R.id.MaincardView);


        }
    }
}
