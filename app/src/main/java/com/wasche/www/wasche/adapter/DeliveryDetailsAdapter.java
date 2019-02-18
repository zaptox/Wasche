package com.wasche.www.wasche.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.activites.HomeActivity;
import com.wasche.www.wasche.beans.DeliveryDetailBean;
import com.wasche.www.wasche.dbtables.DeliveryDetailTable;
import com.wasche.www.wasche.util.CONST;

import java.util.List;


public class DeliveryDetailsAdapter extends RecyclerView.Adapter<DeliveryDetailsAdapter.MyViewHolder>{

    private List<DeliveryDetailBean> deliveryDetailItems;
    private boolean urgentDelivery;
    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView itemName, tvTotalCost,tvQty,textViewPerPieceCost,textViewServiceTitle;
        public ImageButton imgBtnMinus;
        public ImageView imageViewItemImage,imageViewDelete;
        public Button buttonAddToCart;

        public MyViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.tv_itemName);
            tvTotalCost = (TextView) view.findViewById(R.id.tvTotalCost);
            textViewServiceTitle = (TextView) view.findViewById(R.id.textViewServiceTitle);
            tvQty = (TextView) view.findViewById(R.id.tvQty);
            imgBtnMinus=(ImageButton)view.findViewById(R.id.imageViewMinus);
            imageViewItemImage=(ImageView)view.findViewById(R.id.imageViewItemImage);
            imageViewDelete=(ImageView)view.findViewById(R.id.imageViewDelete);
            textViewPerPieceCost=(TextView)view.findViewById(R.id.textViewPerPieceCost);


        }
    }


    @Override
    public int getItemCount() {
        return deliveryDetailItems.size();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dd_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        DeliveryDetailBean ddt=deliveryDetailItems.get(position);
        holder.textViewPerPieceCost.setText("Rs: " + ddt.getCost());
        holder.tvTotalCost.setText("Rs: " + ddt.getTotalCost());
        holder.tvQty.setText("QTY: " + ddt.getQunatity());
        holder.itemName.setText(ddt.getItemName());
        holder.textViewServiceTitle.setText(ddt.getServiceName());
        Picasso.get().load(ddt.getItemImageUrl()).placeholder(R.drawable.loading).into(holder.imageViewItemImage);



    }

    public DeliveryDetailsAdapter(Context context, List<DeliveryDetailBean> deliveryDetailItems){
        this.deliveryDetailItems = deliveryDetailItems;
        this.urgentDelivery=urgentDelivery;
        this.context=context;

    }


}
