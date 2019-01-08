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
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.dbtables.DeliveryDetailTable;
import com.wasche.www.wasche.dbtables.ItemTable;

import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    private List<ItemTable> itemList;
    private boolean urgentDelivery;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView itemName, quantity, price;
        public ImageButton imgBtnPlus,imgBtnMinus;
        public ImageView imageViewItemImage;
        public Button buttonAddToCart;

        public MyViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.tv_itemName);
            quantity = (TextView) view.findViewById(R.id.tv_quanity);
            price = (TextView) view.findViewById(R.id.tv_price);
            imgBtnPlus=(ImageButton)view.findViewById(R.id.imageViewPlus);
            imgBtnMinus=(ImageButton)view.findViewById(R.id.imageViewMinus);
            buttonAddToCart=(Button) view.findViewById(R.id.buttonAddToCart);

            imageViewItemImage=(ImageView)view.findViewById(R.id.imageViewItemImage);
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

         final ItemTable item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.quantity.setText("0");

        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliveryDetailTable ddt=new  DeliveryDetailTable();
                ddt.setItemId(itemList.get(position).getItemId());
                ddt.setItemName(itemList.get(position).getName());
                ddt.setItemImage(itemList.get(position).getImage());
                ddt.setCost(itemList.get(position).getCost());
                ddt.setQuantity(Integer.parseInt(""+holder.quantity.getText()));
                ddt.setTotalCost(totalAmount(itemList.get(position).getCost(),Integer.parseInt(""+holder.quantity.getText() )));
                if(urgentDelivery==true){
                    ddt.setTotalUrgentCost(item.getUrgentCost());
                    ddt.setUrgentStatus(1);
                    ddt.setUrgentPercent(itemList.get(position).getUrgentPercent());
                }
                else{
                    ddt.setTotalUrgentCost(0.0);
                    ddt.setUrgentStatus(0);
                }
                ddt.setdDetailId(new Select().all().from(DeliveryDetailTable.class).execute().size());
                ddt.save();
                Toast.makeText(context,"Saved"+ddt.toString(),Toast.LENGTH_SHORT).show();
            }
        });


        if(urgentDelivery==true) {
            holder.price.setText("Rs: " + item.getUrgentCost());

        }
        else{
            holder.price.setText("Rs: " + item.getCost());

        }
        Picasso.get().load(item.getImage()).placeholder(R.drawable.loading).into(holder.imageViewItemImage);


        holder.imgBtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.buttonAddToCart.setVisibility(View.VISIBLE);
             int qty=Integer.parseInt(""+holder.quantity.getText());
             qty++;
            holder.quantity.setText(""+qty);
            }
        });
        holder.imgBtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty=Integer.parseInt(""+holder.quantity.getText());
                if(qty>0){
                qty--;}
                holder.quantity.setText(""+qty);
            }
        });
    }

    public ItemAdapter(Context context, List<ItemTable> itemList, boolean urgentDelivery){
        this.itemList = itemList;
        this.context=context;
        this.urgentDelivery=urgentDelivery;
    }

    public  Double totalAmount(double cost,int qty){
        return cost*qty;
    }

}
