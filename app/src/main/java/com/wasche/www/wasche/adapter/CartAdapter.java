package com.wasche.www.wasche.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.dbtables.DeliveryDetailTable;
import com.wasche.www.wasche.dbtables.ServiceTable;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    private List<DeliveryDetailTable> deliveryDetailItems;
    private boolean urgentDelivery;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView itemName, tvTotalCost,tvQty;
        public ImageButton imgBtnMinus;
        public ImageView imageViewItemImage;
        public Button buttonAddToCart;

        public MyViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.tv_itemName);
            tvTotalCost = (TextView) view.findViewById(R.id.tvTotalCost);
            tvQty = (TextView) view.findViewById(R.id.tvQty);
            imgBtnMinus=(ImageButton)view.findViewById(R.id.imageViewMinus);
            imageViewItemImage=(ImageView)view.findViewById(R.id.imageViewItemImage);


        }
    }


    @Override
    public int getItemCount() {
            return deliveryDetailItems.size();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        DeliveryDetailTable ddt = deliveryDetailItems.get(position);
        holder.itemName.setText(ddt.getItemName());
//        if(urgentDelivery==true) {
//            holder.price.setText("Rs: " + item.getUrgentCost());
//
//        }
//        else{
        holder.tvTotalCost.setText("Rs: " + ddt.getTotalCost());
        holder.tvQty.setText("QTY: " + ddt.getQuantity());
//
//        }
        Picasso.get().load(ddt.getItemImage()).placeholder(R.drawable.loading).into(holder.imageViewItemImage);


//        holder.imgBtnMinus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int qty=Integer.parseInt(""+holder.quantity.getText());
//                qty--;
//                holder.quantity.setText(""+qty);
//            }
//        });
//    }
    }

    public CartAdapter(List<DeliveryDetailTable> deliveryDetailItems){
        this.deliveryDetailItems = deliveryDetailItems;

    }

}
