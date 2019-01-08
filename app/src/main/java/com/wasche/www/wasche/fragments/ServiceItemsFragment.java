package com.wasche.www.wasche.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.adapter.ItemAdapter;
import com.wasche.www.wasche.dbtables.ItemTable;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceItemsFragment extends Fragment {
    private int serviceId;
    private String serviceTitle, serviceBannerImg;
    private Switch switchUrgentDelivery;

    public ServiceItemsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ServiceItemsFragment(int serviceId, String serviceTitle, String serviceBannerImg) {
        this.serviceId = serviceId;
        this.serviceTitle = serviceTitle;
        this.serviceBannerImg = serviceBannerImg;
    }

//
//    public void didTapButton(View view) {
//        Button button = (Button)view.findViewById(R.id.btnLogin);
//
//        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
//
//        // Use bounce interpolator with amplitude 0.1 and frequency 20
//        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 50);
//        myAnim.setInterpolator(interpolator);
//
//        button.startAnimation(myAnim);
//        Intent intent = new Intent(OrderDetails.this,OrderActivity.class);
//        startActivity(intent);
//    }

    private List<ItemTable> itemList;
    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;
    private LinearLayout linearLayoutServiceBannner;
    private TextView textViewServiceBannerTitle,textViewUrgent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_items, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        textViewUrgent=view.findViewById(R.id.textViewUrgent);
        switchUrgentDelivery=view.findViewById(R.id.switchUrgentDelivery);
        itemList = new ArrayList<>();
        loadServiceItem();

        iAdapter = new ItemAdapter(getContext(),itemList,false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iAdapter);

        switchUrgentDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    iAdapter = new ItemAdapter(getContext(),itemList,true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(iAdapter);
                    switchUrgentDelivery.setText("Switch to  Normal delivery");
                    textViewUrgent.setVisibility(View.VISIBLE);

                }
                else{
                    iAdapter = new ItemAdapter(getContext(),itemList,false);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(iAdapter);
                    switchUrgentDelivery.setText("Switch to Urgent delivery");
                    textViewUrgent.setVisibility(View.GONE);


                }
            }
        });

        Button btnOrderDone = (Button) view.findViewById(R.id.btnOrderDone);
        linearLayoutServiceBannner = (LinearLayout) view.findViewById(R.id.llServiceBannner);
        textViewServiceBannerTitle = (TextView) view.findViewById(R.id.textViewServiceBannerTitle);
        btnOrderDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        final ImageView img = new ImageView(getContext());
        Picasso.get()
                .load(serviceBannerImg)
                .error(R.drawable.blank)
                .placeholder(R.drawable.blank)
                .into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        linearLayoutServiceBannner.setBackgroundDrawable(img.getDrawable());
                    }

                    @Override
                    public void onError(Exception e) {

                    }

                });


        textViewServiceBannerTitle.setText(serviceTitle);

        return view;

    }

    //
    private void loadServiceItem() {
        itemList=new Select().all().from(ItemTable.class).where("serviceId=?",serviceId).execute();
    }
}

