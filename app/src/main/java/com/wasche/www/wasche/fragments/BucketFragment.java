package com.wasche.www.wasche.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.activites.HomeActivity;
import com.wasche.www.wasche.adapter.BucketAdapter;
import com.wasche.www.wasche.dbtables.DeliveryDetailTable;
import com.wasche.www.wasche.util.CONST;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BucketFragment extends Fragment {
    private RecyclerView recyclerView;
   private BucketAdapter bucketAdapter;
   private List<DeliveryDetailTable> deliveryDetailItems;
   private Button btnOrderDone;
    private Switch switchUrgentDelivery;
    private TextView textViewQty,tvTotalBill,textViewDate;
    private  double  totalBill=0.0;
    private  Integer totalQty=0;
    private  boolean urgent=false;
    private ImageView imageViewDate;


    public BucketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bucket, container, false);


        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerViewCart);
        recyclerView.setHasFixedSize(true);
        btnOrderDone=(Button)view.findViewById(R.id.btnSaveUpdates);
        tvTotalBill=(TextView)view.findViewById(R.id.tvTotalBill);
        textViewQty=(TextView)view.findViewById(R.id.textViewQty);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Context context = inflater.getContext();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);


        switchUrgentDelivery=view.findViewById(R.id.switchUrgentDelivery);
        deliveryDetailItems=new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        bucketAdapter = new BucketAdapter(getContext(),deliveryDetailItems,false);
        recyclerView.setAdapter(bucketAdapter);
        bucketAdapter.notifyDataSetChanged();

        switchUrgentDelivery.setText("Switch to Urgent delivery");
        urgent=false;


         generateRecyclerItems(urgent);
        switchUrgentDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    new Update(DeliveryDetailTable.class).set("urgentStatus=?",1).execute();
                    urgent=true;
                    generateRecyclerItems(urgent);
                    switchUrgentDelivery.setText("Switch to  Normal delivery");

                }
                else{
                    new Update(DeliveryDetailTable.class).set("urgentStatus=?",0).execute();

                    urgent=false;
                    generateRecyclerItems(urgent);
                    switchUrgentDelivery.setText("Switch to Urgent delivery");







                }
            }
        });



        btnOrderDone.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(deliveryDetailItems.size()!=0){
                      Intent intent=new Intent(view.getContext(), HomeActivity.class);
                      intent.putExtra("flag", CONST.ORDER_FRAGMENT);
                      startActivity(intent);
                  }
                  else{
                      Toast.makeText(getContext(),"No items in bucket",Toast.LENGTH_SHORT).show();
                  }


              }
          });



        return view; }


        public void showTotalCost(boolean urgent){
            deliveryDetailItems=new Select().all().from(DeliveryDetailTable.class).execute();

            totalQty=0;
               totalBill=0;
               if(urgent) {
                   for (DeliveryDetailTable ddt : deliveryDetailItems) {
                       totalBill += ddt.getTotalUrgentCost();
                       totalQty += ddt.getQuantity();
                   }
               }
               else{
                   for (DeliveryDetailTable ddt : deliveryDetailItems) {
                       totalBill += ddt.getTotalCost();
                       totalQty += ddt.getQuantity();

                   }
               }
            tvTotalBill.setText("Total Bill: Rs "+totalBill);
            textViewQty.setText("Total Quantity: "+totalQty);
        }

        private  void generateRecyclerItems(boolean urgent){
            showTotalCost(urgent);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            bucketAdapter = new BucketAdapter(getContext(),deliveryDetailItems,urgent);
            recyclerView.setAdapter(bucketAdapter);
            bucketAdapter.notifyDataSetChanged();
        }

 }
