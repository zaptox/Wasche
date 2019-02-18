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
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.activites.HomeActivity;
import com.wasche.www.wasche.dbtables.DeliveryDetailTable;
import com.wasche.www.wasche.util.CONST;

import java.util.List;



public class BucketAdapter extends RecyclerView.Adapter<BucketAdapter.MyViewHolder>{

    private List<DeliveryDetailTable> deliveryDetailItems;
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bucket_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final DeliveryDetailTable ddt = deliveryDetailItems.get(position);
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Delete().from(DeliveryDetailTable.class).where("dDetailId=?",ddt.getdDetailId()).execute();
                  deliveryDetailItems.remove(position);
                 notifyDataSetChanged();
                Intent intent=new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra("flag", CONST.BUCKET_FRAGMENT);
                context.startActivity(intent);

            }
        });


        if(urgentDelivery==true) {
            holder.textViewPerPieceCost.setText("Rs: " + ddt.getUrgentCost());
            holder.tvTotalCost.setText("Rs: " + ddt.getTotalUrgentCost());
            holder.tvQty.setText("QTY: " + ddt.getQuantity());
            holder.itemName.setText(ddt.getItemName()+"(Urgent)");



        }
        else{
            holder.textViewPerPieceCost.setText("Rs: " + ddt.getCost());
            holder.tvTotalCost.setText("Rs: " + ddt.getTotalCost());
            holder.tvQty.setText("QTY: " + ddt.getQuantity());
            holder.itemName.setText(ddt.getItemName());



        }
        holder.textViewServiceTitle.setText(ddt.getServiceName());
        Picasso.get().load(ddt.getItemImage()).placeholder(R.drawable.loading).into(holder.imageViewItemImage);


    }

    public BucketAdapter(Context context, List<DeliveryDetailTable> deliveryDetailItems, boolean urgentDelivery){
        this.deliveryDetailItems = deliveryDetailItems;
        this.urgentDelivery=urgentDelivery;
        this.context=context;

    }


}
