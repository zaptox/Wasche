package com.wasche.www.wasche.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.dao.UrgentDao;
import com.wasche.www.wasche.daoImp.UrgentDaoImpl;
import com.wasche.www.wasche.dbtables.DeliveryDetailTable;
import com.wasche.www.wasche.dbtables.ItemTable;
import com.wasche.www.wasche.util.calculatePercentCost;


import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    private List<ItemTable> itemList;
    private boolean urgentDelivery;
    Context context;
    public String serviceName="";
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView itemName, quantity, price;
        public ImageView imgBtnPlus,imgBtnMinus;
        public ImageView imageViewItemImage;
        public ImageView buttonAddToCart;

        public MyViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.tv_itemName);
            quantity = (TextView) view.findViewById(R.id.tv_quanity);
            price = (TextView) view.findViewById(R.id.tv_price);
            imgBtnPlus=(ImageView) view.findViewById(R.id.imageViewPlus);
            imgBtnMinus=(ImageView) view.findViewById(R.id.imageViewMinus);
            buttonAddToCart=(ImageView) view.findViewById(R.id.buttonAddToCart);

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
        holder.price.setText("Rs: " + item.getCost());
        holder.quantity.setText("0");

        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UrgentDao urgentDao = new UrgentDaoImpl();
                double totalCost= calculateTotalAmount(itemList.get(position).getCost(), Integer.parseInt("" + holder.quantity.getText()));
                double urgentCost= calculatePercentCost.getUrgentCost(item.getCost(), urgentDao.getUrgentPercent().getUrgentPercent());
                double totalUrgentCost=calculateTotalAmount(calculatePercentCost.getUrgentCost(item.getCost(), urgentDao.getUrgentPercent().getUrgentPercent()), Integer.parseInt("" + holder.quantity.getText()));
                int qty=Integer.parseInt(""+holder.quantity.getText());
                double urgentPercent=urgentDao.getUrgentPercent().getUrgentPercent();
                DeliveryDetailTable ddtable=new Select().all().from(DeliveryDetailTable.class).where("itemId = ?",item.getItemId()).executeSingle();

//                Toast.makeText(context,ddtable.toString(),Toast.LENGTH_SHORT).show();

                if(ddtable==null) {
                    DeliveryDetailTable ddt = new DeliveryDetailTable();
                    ddt.setItemId(itemList.get(position).getItemId());
                    ddt.setItemName(itemList.get(position).getName());
                    ddt.setItemImage(itemList.get(position).getImage());
                    ddt.setCost(itemList.get(position).getCost());
                    ddt.setQuantity(qty);
                    ddt.setServiceName(serviceName);
                    ddt.setServiceId(itemList.get(position).getServiceId());
                    ddt.setUrgentCost(urgentCost);
                    ddt.setTotalCost(totalCost);
                    ddt.setTotalUrgentCost(totalUrgentCost);
                    ddt.setUrgentPercent(urgentPercent);
                    ddt.setUrgentStatus(0);

                    ddt.setdDetailId(new Select().all().from(DeliveryDetailTable.class).execute().size());
                    ddt.save();

                }

//
                else{

                    totalCost+=ddtable.getTotalCost();
                    qty+=ddtable.getQuantity();
                    totalUrgentCost+=ddtable.getTotalUrgentCost();
                    new Update(DeliveryDetailTable.class).set("quantity=?,totalCost=?,totalUrgentCost=?",qty, totalCost,totalUrgentCost).where("itemId = ?", item.getItemId()).execute();

                    Toast.makeText(context, "Added to Bucket.", Toast.LENGTH_SHORT).show();
                    holder.buttonAddToCart.setVisibility(View.GONE);
                    holder.quantity.setText("0");
                    holder.price.setText("Rs: " + item.getCost());
                }
                Toast.makeText(context, "Added to Bucket.", Toast.LENGTH_SHORT).show();
                holder.buttonAddToCart.setVisibility(View.GONE);
                holder.quantity.setText("0");
                holder.price.setText("Rs: " + item.getCost());
//
            }
        });


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
                    qty--;
                    if (qty == 0){
                        holder.buttonAddToCart.setVisibility(View.GONE);
                    }}

                holder.quantity.setText(""+qty);
            }
        });
    }

    public ItemAdapter(Context context, List<ItemTable> itemList, boolean urgentDelivery,String serviceName){
        this.itemList = itemList;
        this.context=context;
        this.urgentDelivery=urgentDelivery;
        this.serviceName=serviceName;
    }

    public  Double calculateTotalAmount(double cost, int qty){
        return cost*qty;
    }


}
