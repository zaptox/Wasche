package com.wasche.www.wasche.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.morphingbutton.MorphingButton;
import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.activites.HomeActivity;
import com.wasche.www.wasche.dbtables.ServiceTable;
import com.wasche.www.wasche.util.CONST;

import java.util.List;

public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.ViewHolder> {
    public List<ServiceTable> services;
    public Context context;
    public ServicesListAdapter(Context context, List<ServiceTable> services){
        this.services = services;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.services_list_item,parent,false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvService.setText(services.get(position).getName());

    if(services.get(position).getImage()==null || services.get(position).getImage().equals("")){
        holder.linearLayoutBg.setBackgroundResource(R.drawable.blank);
    }
        else {


        final ImageView img = new ImageView(context);
        Picasso.get()
                .load(services.get(position).getImage())
                .error(R.drawable.blank)
                .placeholder(R.drawable.blank)
                .into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        holder.linearLayoutBg.setBackgroundDrawable(img.getDrawable());
                    }

                    @Override
                    public void onError(Exception e) {

                    }

                });

    }


//
        final int service_id= services.get(position).getServiceId();
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra("flag", CONST.ORDER_DETAILS_FRAGMENT);
                intent.putExtra("service_id",service_id);
                intent.putExtra("service_img", services.get(position).getImage());
                intent.putExtra("service_name", services.get(position).getName());

                context.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount() {
        return services.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        MorphingButton btnMorph;
        public TextView tvService;
        LinearLayout linearLayoutBg;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;


            tvService=(TextView)mView.findViewById(R.id.textViewServiceTitle);
            linearLayoutBg=(LinearLayout) mView.findViewById(R .id.linearLayoutImageBg);

        }
    }
}
