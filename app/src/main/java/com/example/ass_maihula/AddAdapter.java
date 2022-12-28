package com.example.ass_maihula;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList id,name,goods,amount;

    AddAdapter(Activity activity,Context context,ArrayList id,ArrayList name,ArrayList goods,ArrayList amount ){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.goods = goods;
        this.amount = amount;

    }

    @NonNull
    @Override
    public AddAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.testing,parent,false);
        return new AddAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddAdapter.MyViewHolder holder, int position) {
       // holder.id.setText(String.valueOf(id.get(position)));
        holder.goodsName.setText(String.valueOf(name.get(position)));
        holder.qty.setText(String.valueOf(goods.get(position)));
        holder.amount.setText(String.valueOf(amount.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText id,goodsName,qty,amount;
        RelativeLayout mainLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
           // id= itemView.findViewById(R.id.Customer_id_txt);
            goodsName= itemView.findViewById(R.id.qty);
            qty= itemView.findViewById(R.id.c_goods_txt);
            amount= itemView.findViewById(R.id.amount);
            mainLayout = itemView.findViewById(R.id.mainLayout1);
        }
    }
}
