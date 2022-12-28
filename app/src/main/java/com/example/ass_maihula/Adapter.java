package com.example.ass_maihula;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {
    private List<ModelClass> itemsList;
    Context context;
    Activity activity;
    ArrayList id,iname,cid,qty,price,tprice,date;
    int idi;
    List<String> allName;
    OnItemClickListener mListener;

    public interface OnItemClickListener{
       // void onItemClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemListener(OnItemClickListener listener){
        mListener = listener;
    }

    Adapter(Activity activity,Context context,ArrayList id,ArrayList iname,ArrayList qty,ArrayList price,ArrayList tprice, ArrayList date){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.iname= iname;
        this.qty = qty;
        this.price = price;
        this.tprice = tprice;
        this.date= date;
        allName = new ArrayList<>(iname);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.items,viewGroup,false);
        return new ViewHolder(view,mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Intent intent = new Intent(context, EditRecord.class);
      //  viewHolder.id.setText(String.valueOf(id.get(i)));
        viewHolder.iname.setText(String.valueOf(iname.get(i)));
        viewHolder.qty.setText(String.valueOf(qty.get(i)));
        viewHolder.price.setText(String.valueOf(price.get(i)));
        viewHolder.tprice.setText(String.valueOf(tprice.get(i)));
        viewHolder.date.setText(String.valueOf(date.get(i)));
        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent.putExtra("id",String.valueOf(id.get(i)));
                intent.putExtra("cid",String.valueOf(cid));
                intent.putExtra("iname",String.valueOf(iname.get(i)));
                intent.putExtra("qty",String.valueOf(qty.get(i)));
                intent.putExtra("price",String.valueOf(price.get(i)));
                intent.putExtra("tprice",String.valueOf(tprice.get(i)));
                activity.startActivityForResult(intent,0);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filteredList.addAll(allName);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (String item: allName){
                    if (item.toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                iname.clear();
                iname.addAll((Collection<? extends String>) filterResults.values);
                notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id,cid,iname,qty,price,tprice,date;
        ImageView mDeleteImage;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            //id = itemView.findViewById(R.id.idi20);
            mDeleteImage = itemView.findViewById(R.id.image_delete);
            cid = itemView.findViewById(R.id.cid1);
           // cid.setText(getIntent().getStringExtra("cid"));
            iname = itemView.findViewById(R.id.items1);
            qty =itemView.findViewById(R.id.qtty);
            price = itemView.findViewById(R.id.price);
            tprice = itemView.findViewById(R.id.Tprice);
            date = itemView.findViewById(R.id.date);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION);{
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }

    }
}
