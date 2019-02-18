package com.wasche.www.wasche.fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wasche.www.wasche.R;
import com.wasche.www.wasche.adapter.DeliveriesAdapter;
import com.wasche.www.wasche.adapter.DeliveryDetailsAdapter;
import com.wasche.www.wasche.beans.DeliveryBean;
import com.wasche.www.wasche.beans.DeliveryDetailBean;
import com.wasche.www.wasche.beans.DeliveryDetailsList;
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
public class DeliveryDetailsFragment extends Fragment {

    private RecyclerView recyclerView;
    private DeliveryDetailsAdapter deliveryDetailsAdapter;
    private List<DeliveryDetailBean> deliveryDetails;
    private TextView textViewQty,tvTotalBill;
    private  double  totalBill;
    private  Integer totalQty=0,deliveryId;
    private ImageView imageViewDate;
    private static DeliveryApi deliveryApi;
    private ProgressDialog progressDialog;


    public DeliveryDetailsFragment() {
        // Required empty public constructor
    }
    @SuppressLint("ValidFragment")
    public DeliveryDetailsFragment(int deliveryId, double totalBill, int totalQty) {
        this.totalQty=totalQty;
        this.deliveryId=deliveryId;
        this.totalBill=totalBill;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_delivery_details, container, false);


        setUIToWait(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//
        tvTotalBill=(TextView)view.findViewById(R.id.tvTotalBill);
        textViewQty=(TextView)view.findViewById(R.id.textViewQty);
        deliveryDetails=new ArrayList<>();
        tvTotalBill.setText("Total Bill: Rs "+totalBill);
        textViewQty.setText("Total Quantity: "+totalQty);


        deliveryApi = ApiClient.getApiClient().create(DeliveryApi.class);
        Prefrences prefrences = new Prefrences();
        Call<DeliveryDetailsList> callD = DeliveryDetailsFragment.deliveryApi.getdeliveryDetailsById(deliveryId);
        callD.enqueue(new Callback<DeliveryDetailsList>() {
            @Override
            public void onResponse(Call<DeliveryDetailsList> call, Response<DeliveryDetailsList> response) {
                setUIToWait(false);
                deliveryDetails=response.body().getDeliveryDetails();
                generateDeliviresList(deliveryDetails,view);
            }

            @Override
            public void onFailure(Call<DeliveryDetailsList> call, Throwable t) {

            }
        });
//


        return view;  }

//    private  void generateRecyclerItems(List<DeliveryDetailBean> deliveryDetails){
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
////        deliveryDetailsAdapter = new DeliveryDetailsAdapter(getContext(),deliveryDetails);
////        recyclerView.setAdapter(deliveryDetailsAdapter);
//////        deliveryDetailsAdapter.notifyDataSetChanged();
//    }

    private void generateDeliviresList(List<DeliveryDetailBean> deliveries, View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDD);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        deliveryDetailsAdapter = new DeliveryDetailsAdapter(getContext(), deliveries);
        recyclerView.setAdapter(deliveryDetailsAdapter);
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
}
