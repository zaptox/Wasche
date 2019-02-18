package com.wasche.www.wasche.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wasche.www.wasche.R;
import com.wasche.www.wasche.adapter.DeliveriesAdapter;
import com.wasche.www.wasche.adapter.ServicesListAdapter;
import com.wasche.www.wasche.beans.DeliveriesList;
import com.wasche.www.wasche.beans.DeliveryBean;
import com.wasche.www.wasche.beans.DeliveryDetailsList;
import com.wasche.www.wasche.dbtables.ServiceTable;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.DeliveryApi;
import com.wasche.www.wasche.util.Prefrences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {
    RecyclerView recyclerViewDeliveries;
    DeliveriesAdapter deliveriesAdapter;
    List<DeliveryBean> deliveries;
    private static DeliveryApi deliveryApi;
    private ProgressDialog progressDialog;
    private String TAG="MyOrders";


    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        setUIToWait(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        loadDeliveries(view);



        return view;
    }

    private void generateDeliviresList(List<DeliveryBean> deliveries,View view) {

        recyclerViewDeliveries = (RecyclerView) view.findViewById(R.id.recyclerViewDeliveries);
        recyclerViewDeliveries.setHasFixedSize(true);
        recyclerViewDeliveries.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDeliveries.setLayoutManager(mLayoutManager);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        deliveriesAdapter = new DeliveriesAdapter(getContext(), deliveries);
        recyclerViewDeliveries.setAdapter(deliveriesAdapter);
    }

    private void setUIToWait(boolean wait) {

        if (wait) {
            progressDialog = ProgressDialog.show(getContext(), null, null, true, true);
//            progressDialog.setContentView(new ProgressBar(this));
            progressDialog.setContentView(R.layout.loader);

        } else {
            progressDialog.dismiss();
        }


    }
    private  void loadDeliveries(final View view){

        deliveries = new ArrayList<>();
        deliveryApi = ApiClient.getApiClient().create(DeliveryApi.class);
        Prefrences prefrences = new Prefrences();
        Call<DeliveriesList> callD = MyOrdersFragment.deliveryApi.getdeliveriesByCustomerId(prefrences.getCustomerBean(getContext()).getId());
        callD.enqueue(new Callback<DeliveriesList>() {
            @Override
            public void onResponse(Call<DeliveriesList> call, Response<DeliveriesList> response) {
                generateDeliviresList(response.body().getDeliveriesList(),view);
                setUIToWait(false);

            }

            @Override
            public void onFailure(Call<DeliveriesList> call, Throwable t) {

            }
        });
    }
}