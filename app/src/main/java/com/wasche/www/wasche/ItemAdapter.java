package com.wasche.www.wasche;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    private List<ItemDetails> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView itemName, quantity, price;

        public MyViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.tv_itemName);
            quantity = (TextView) view.findViewById(R.id.tv_quanity);
            price = (TextView) view.findViewById(R.id.tv_price);
        }
    }


    @Override
    public int getItemCount() {
            return itemList.size();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ItemDetails item = itemList.get(position);
        holder.itemName.setText(item.getItem_name());
        holder.quantity.setText(item.getQuantity());
        holder.price.setText(item.getPrice());

    }

    public ItemAdapter(List<ItemDetails> itemList){
        this.itemList = itemList;
    }

}
