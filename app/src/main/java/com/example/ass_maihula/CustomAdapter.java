package com.example.ass_maihula;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    Activity activity;
    ArrayList id,name,goods,amount;
   // int position;

    CustomAdapter(Activity activity,Context context,ArrayList id,ArrayList name,ArrayList goods,ArrayList amount ){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.goods = goods;
        this.amount = amount;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.goods.setText(String.valueOf(goods.get(position)));
        holder.amount.setText(String.valueOf(amount.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), New_item_list.class);
                intent.putExtra("cid",String.valueOf(id.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
               // intent.putExtra("goods",String.valueOf(goods.get(position)));
                intent.putExtra("amount",String.valueOf(amount.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,goods,amount;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            id= itemView.findViewById(R.id.Customer_id_txt);
            name= itemView.findViewById(R.id.Customer_name_txt);
            goods= itemView.findViewById(R.id.c_goods_txt);
            amount= itemView.findViewById(R.id.c_amount_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
