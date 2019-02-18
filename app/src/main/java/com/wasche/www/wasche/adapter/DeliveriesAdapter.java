package com.wasche.www.wasche.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wasche.www.wasche.R;
import com.wasche.www.wasche.activites.HomeActivity;
import com.wasche.www.wasche.beans.DeliveryBean;
import com.wasche.www.wasche.util.CONST;
import com.wasche.www.wasche.util.TimeConvert;

import java.util.List;

public class DeliveriesAdapter extends RecyclerView.Adapter<DeliveriesAdapter.ViewHolder> {
    public List<DeliveryBean> deliveries;
    public Context context;
    public DeliveriesAdapter(Context context, List<DeliveryBean> deliveries){
        this.deliveries = deliveries;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item,parent,false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.textViewTotalBill.setText("Total bill: Rs "+deliveries.get(position).getTotalDeliveryCost());
        holder.textViewQty.setText("QTY: "+deliveries.get(position).getQuantity());
        if(deliveries.get(position).getDeliveryStatus().equalsIgnoreCase("0")){
            holder.textViewDeliveryStatus.setText("Not Delivered");
        }
        else{
            holder.textViewDeliveryStatus.setText("Delivered");
        }

        if(deliveries.get(position).getUrgent_status()==0){
            holder.textViewUrgentStatus.setVisibility(View.GONE);
        }
        else{
            holder.textViewUrgentStatus.setVisibility(View.VISIBLE);

        }
        holder.textViewPlacedAt.setText("Placed at: "+ TimeConvert.getDate(Integer.parseInt(deliveries.get(position).getCreatedAt())));
        holder.textviewDeliveriedAt.setText("Deliveried at: "+deliveries.get(position).getDeliveryDate());
        holder.textviewAddress.setText(" "+deliveries.get(position).getAddress());




//
        final int deliveryId= deliveries.get(position).getId();
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra("total_bill",deliveries.get(position).getTotalDeliveryCost());
                intent.putExtra("flag", CONST.DELIVERY_DETAILS_FRAGMENT);
                intent.putExtra("delivery_id",deliveryId);
                intent.putExtra("total_qty",deliveries.get(position).getQuantity());

                context.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount() {
        return deliveries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView textViewTotalBill,textViewQty,textViewDeliveryStatus,textViewUrgentStatus,textViewPlacedAt,textviewDeliveriedAt,textviewAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;


            textViewTotalBill=(TextView)mView.findViewById(R.id.textViewTotalBill);
            textViewQty=(TextView)mView.findViewById(R.id.textViewQty);
            textViewDeliveryStatus=(TextView)mView.findViewById(R.id.textViewDeliveryStatus);
            textViewUrgentStatus=(TextView)mView.findViewById(R.id.textViewUrgentStatus);
            textViewPlacedAt=(TextView)mView.findViewById(R.id.textViewPlacedAt);
            textviewDeliveriedAt=(TextView)mView.findViewById(R.id.textviewDeliveriedAt);
            textviewAddress=(TextView)mView.findViewById(R.id.textviewAddress);

        }
    }
}
