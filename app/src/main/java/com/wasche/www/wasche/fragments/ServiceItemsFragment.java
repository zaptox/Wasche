package com.wasche.www.wasche.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wasche.www.wasche.R;
import com.wasche.www.wasche.daoImp.ItemDaoImpl;
import com.wasche.www.wasche.adapter.ItemAdapter;
import com.wasche.www.wasche.dao.ItemDao;
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
        // load items from local db
        ItemDao itemDao=new ItemDaoImpl();
        itemList=itemDao.getItemsByServiceId(serviceId);

        iAdapter = new ItemAdapter(getContext(),itemList,false,serviceTitle);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iAdapter);

        switchUrgentDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    iAdapter = new ItemAdapter(getContext(),itemList,true,serviceTitle);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(iAdapter);
                    switchUrgentDelivery.setText("Switch to  Normal delivery");
                    textViewUrgent.setVisibility(View.VISIBLE);

                }
                else{
                    iAdapter = new ItemAdapter(getContext(),itemList,false,serviceTitle);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(iAdapter);
                    switchUrgentDelivery.setText("Switch to Urgent delivery");
                    textViewUrgent.setVisibility(View.GONE);


                }
            }
        });

        linearLayoutServiceBannner = (LinearLayout) view.findViewById(R.id.llServiceBannner);
        textViewServiceBannerTitle = (TextView) view.findViewById(R.id.textViewServiceBannerTitle);




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


}

